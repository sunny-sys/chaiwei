package com.bangdao.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bangdao.common.constant.Constants;
import com.bangdao.common.exception.base.BaseException;
import com.bangdao.common.utils.security.ShiroUtils;
import com.bangdao.domain.approval.Approval;
import com.bangdao.responseVo.approval.ApprovalResp;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Component
public class ActivitiUtil {

    private static RuntimeService runtimeService;

    private static TaskService taskService;

    private static HistoryService historyService;

    @Autowired
    public ActivitiUtil(RuntimeService runtimeService,TaskService taskService,
                        HistoryService historyService) {
        ActivitiUtil.runtimeService = runtimeService;
        ActivitiUtil.taskService = taskService;
        ActivitiUtil.historyService = historyService;
    }

    /**
     * 审批单据
     * @param approval 审批实体
     * return 流程实例
     */
    public static String approval(Approval approval) {
        //封装审批信息
        String message = setApprovalInfo(approval);
        //审批动作（-1驳回，1通过）
        String action = approval.getAction();
        //使用任务ID，查询任务对象，获取流程流程实例ID
        Task task = ActivitiUtil.getTask(approval.getBusinessKey());
        String taskId = task.getId();
        //获取流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        /**设置审批记录*/
        Authentication.setAuthenticatedUserId(ShiroUtils.getLoginName());
        taskService.addComment(taskId, processInstanceId, message);

        /**设置审批流转常量*/
        Map<String, Object> variables = new HashMap<String, Object>();
        if (action != null && action.equals("-1")) {
            variables.put("approvalAction", Constants.ApprovalAction.REJECT_INT);
        }
        else
        {
            variables.put("approvalAction", Constants.ApprovalAction.AGREE);
        }
        //3：使用任务ID，完成当前人的个人任务，同时流程变量
        taskService.complete(taskId, variables);
        //4：当任务完成之后，需要指定下一个任务的办理人--已经图中完成
        return processInstanceId;
    }

    /**
     * 判断流程是否结束（审批流是否走完）
     * @return true 审批流走完，false没有走完
     */
    public static boolean processInstanceEnd(String processInstanceId)
    {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();
        //流程结束了
        if (pi == null) {
            return true;
        }
        return false;
    }



    /**
     * 根据当前业务关联key 获取当前用户的任务
     * @param businessKey
     * @return
     */
    public static Task getTask(String businessKey)
    {
        String  loginName = ShiroUtils.getLoginName();
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey)
                .taskCandidateOrAssigned(loginName)
                .singleResult();
        return task;
    }


    /**
     * 完成任务（根据业务键key，和当前任务人）
     * @param businessKey
     */
    public static void finishTask(String businessKey)
    {
        Task task = ActivitiUtil.getTask(businessKey);
        if(null == task)
        {
            throw new RuntimeException("内部错误,请重试!");
        }
        taskService.complete(task.getId());
    }

    /**
     * 获取当前用户名的任务列表
     * @param key
     * @return
     */
    public static List<Integer> getTaskListByName(String key)
    {
        List<Integer> ids = new ArrayList<Integer>();
        String loginName = ShiroUtils.getLoginName();
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey(key)
                .taskAssignee(loginName).orderByTaskCreateTime().asc().list();
        for (Task task :tasks)
        {
            String processInstanceId = task.getProcessInstanceId();
            //3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
            ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                    .processInstanceId(processInstanceId)//使用流程实例ID查询
                    .singleResult();
            //4：使用流程实例对象获取BUSINESS_KEY
            String buniness_key = pi.getBusinessKey();

            ids.add(new Integer(buniness_key.split("\\.")[1]));
        }
        return ids;
    }

    /**
     * 根据key来启动流程实例并关联业务
     * @param key 流程实例key
     * @param businessId  业务id
     */
    public static void startProcessInstanceByKey(String key, Integer businessId)
    {
        //设置关联关系
        String businessKey = ActivitiUtil.setBusinessKey(key,businessId);
        HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()//对应历史的流程实例表
						.processInstanceBusinessKey(businessKey)//使用BusinessKey字段查询
						.singleResult();
        //判断该流程实例是否已经创建
        if (null == hpi)
        {
            //设置流程变量
            Map<String, Object> variables = ActivitiUtil.setVariables("loginName");
            //使用流程定义的key启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
            runtimeService.startProcessInstanceByKey(key,businessKey,variables);
        }
    }

    /**
     * 设置流程变量
     * @param transactorKey 办理人key
     * @return
     */
    public static Map<String, Object> setVariables(String transactorKey)
    {
        /**
         * 从Session中获取当前任务的办理人，使用流程变量设置下一个任务的办理人
         * transactorKey是流程变量的名称
         */
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put(transactorKey, ShiroUtils.getLoginName());//表示惟一用户
        return variables;
    }

    /**
     * 获取关联业务逻辑的 BusinessKey
     * @param key
     * @param businessId
     * @return
     */
    public static String setBusinessKey(String key, Integer businessId)
    {
        String businessKey = key+"."+businessId;
        return businessKey;
    }

    /**
     * 获取订单审批记录
     * @param businessKey
     * @return
     */
    public static List<ApprovalResp> getApprovalHistory(String businessKey) {
        HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()//对应历史的流程实例表
						.processInstanceBusinessKey(businessKey)//使用BusinessKey字段查询
						.singleResult();
        if(null == hpi)
        {
            return null;
        }
		//流程实例ID
		String processInstanceId = hpi.getId();
		//获取审批记录
        List<Comment> comments = taskService.getProcessInstanceComments(processInstanceId);
        //将审批记录转换成审批响应实体
        List<ApprovalResp> approvalHistory = new ArrayList<ApprovalResp>();
        for (Comment com : comments)
        {
            String message = com.getFullMessage();
            if (null == message || !"".equals(message))
            {
                ApprovalResp approvalResp = JSONObject.parseObject(message, ApprovalResp.class);
                approvalHistory.add(approvalResp);
            }
        }
        Collections.sort(approvalHistory);//按时间排序
        return approvalHistory;
    }

    /**
     * 将审批信息封装成一个json字符串，后期方便直接提取
     * @param approval 审批信息
     * @return
     */
    public static String setApprovalInfo(Approval approval)
    {
        ApprovalResp resp = new ApprovalResp();
        resp.setAction(approval.getAction());
        resp.setComment(approval.getComment());
        resp.setTime(new Date());
        resp.setApprover(ShiroUtils.getLoginName());
        return JSON.toJSONString(resp);
    }

    /**删除个人任务**/
    public static void deleteTask(String key,String[] ids) throws Exception
    {
        //删除个人任务
        for (String id: ids)
        {
            String businessKey = key+"."+id;
            Task task = getTask(businessKey);
            if(ObjectUtils.isEmpty(task))
            {
                throw new BaseException("该订单无法删除");
            }
            ActivitiUtil.deleteProcessInstance(task.getProcessInstanceId());
        }
    }


    /**删除流程实例**/
    public static void deleteProcessInstance(String processInstanceId)
    {
        if(null != processInstanceId)
        {
            runtimeService.deleteProcessInstance(processInstanceId,"订单被删除");
        }
    }
}
