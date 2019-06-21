package com.bangdao.framework.activiti.controller;

import com.bangdao.common.utils.StringUtils;
import com.bangdao.framework.web.domain.Result;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("activiti/processDef")
public class ProcessDefinitionController {

    private String prefix = "activiti";

    @Resource
    private RepositoryService repositoryService;

    @RequestMapping("/processDefList")
    //@RequiresPermissions("activiti:processDef:view")
    public String prodDefList(){
        return prefix + "/processDefList";
    }

    @RequestMapping("/list")
    @ResponseBody
    //@RequiresPermissions("activiti:processDef:view")
    public Result list(Integer pageSize,Integer pageNum,String processName,String processDefinitionKey,String deploymentId){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if (StringUtils.isNotEmpty(deploymentId)) {
            processDefinitionQuery.deploymentId(deploymentId);
        }
        if (StringUtils.isNotEmpty(processDefinitionKey)) {
            processDefinitionQuery.processDefinitionKey(processDefinitionKey);
        }
        if (StringUtils.isNotEmpty(processName)) {
            processDefinitionQuery.processDefinitionNameLike("%" + processName + "%");
        }
        long total = processDefinitionQuery.count();
        List<ProcessDefinition> list = processDefinitionQuery
                .orderByProcessDefinitionVersion().asc()
                .listPage(pageSize * (pageNum - 1), pageSize);
        return Result.success().put("total", total).put("rows", list);
    }

}
