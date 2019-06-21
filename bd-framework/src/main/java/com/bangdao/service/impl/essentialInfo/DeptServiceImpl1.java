package com.bangdao.service.impl.essentialInfo;

import com.bangdao.service.essentialInfo.IDeptService1;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部门管理 服务实现
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl1 implements IDeptService1 {
   /* @Autowired
    private DeptMapper1 deptMapper1;

    *//**
     * 查询部门管理数据
     *
     * @return 部门信息集合
     *//*
    @Override
    public List<Dept> selectDeptList(Dept dept) {
        return deptMapper1.selectDeptList(dept);
    }

    *//**
     * 查询部门所有数据
     *
     * @return 部门信息集合
     *//*
    @Override
    public List<Dept> selectDeptAll() {
        return deptMapper1.selectDeptAll();
    }

    *//**
     * 查询部门管理树
     *
     * @return 所有部门信息
     *//*
    @Override
    public List<Map<String, Object>> selectDeptTree() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<Dept> deptList = deptMapper1.selectDeptAll();

        for (Dept dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus())) {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("id", dept.getDeptId());
                deptMap.put("pId", dept.getParentId());
                deptMap.put("name", dept.getDeptName());
                deptMap.put("title", dept.getDeptName());
                trees.add(deptMap);
            }
        }
        return trees;
    }

    *//**
     * 查询部门人数
     *
     * @param parentId 部门ID
     * @return 结果
     *//*
    @Override
    public int selectDeptCount(Long parentId) {
        Dept dept = new Dept();
        dept.setParentId(parentId);
        return deptMapper1.selectDeptCount(dept);
    }

    *//**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     *//*
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        int result = deptMapper1.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    *//**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     *//*
    @Override
    public int deleteDeptById(Long deptId) {
        return deptMapper1.deleteDeptById(deptId);
    }

    *//**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     *//*
    @Override
    public int insertDept(Dept dept) {
        Dept info = deptMapper1.selectDeptById(dept.getParentId());
        dept.setCreateBy(ShiroUtils.getLoginName());
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        return deptMapper1.insertDept(dept);
    }

    *//**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     *//*
    @Override
    public int updateDept(Dept dept) {
        Dept info = deptMapper1.selectDeptById(dept.getParentId());
        String ancestors = info.getAncestors() + "," + dept.getParentId();
        dept.setUpdateBy(ShiroUtils.getLoginName());
        dept.setAncestors(ancestors);
        updateDeptChildren(dept.getDeptId(), ancestors);
        return deptMapper1.updateDept(dept);
    }

    *//**
     * 修改子元素关系
     *
     * @param deptId    部门ID
     * @param ancestors 元素列表
     *//*
    public void updateDeptChildren(Long deptId, String ancestors) {
        Dept dept = new Dept();
        dept.setParentId(deptId);
        List<Dept> childrens = deptMapper1.selectDeptList(dept);
        for (Dept children : childrens) {
            children.setAncestors(ancestors + "," + dept.getParentId());
        }
        if (childrens.size() > 0) {
            deptMapper1.updateDeptChildren(childrens);
        }
    }

    *//**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     *//*
    @Override
    public Dept selectDeptById(Long deptId) {
        return deptMapper1.selectDeptById(deptId);
    }

    *//**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     *//*
    @Override
    public boolean checkDeptNameUnique(Dept dept) {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        Dept info = deptMapper1.checkDeptNameUnique(dept.getDeptName());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue()) {
            return false;
        }
        return true;
    }*/
}
