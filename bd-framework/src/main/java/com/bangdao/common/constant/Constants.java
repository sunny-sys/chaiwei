package com.bangdao.common.constant;

/**
 * 通用常量信息
 */
public class Constants {

    /**
     * 通用成功标识
     */
    public static final int SUCCESS = 0;

    /**
     * 通用失败标识
     */
    public static final int FAIL = 1;

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static String IS_ASC = "isAsc";

    /**
     * 单据状态
     */
    public static final class Status {
        /**
         * 逻辑删除状态（字符串类型）
         */
        public static final String DELETED = "-1";

        /**
         * 正常状态（字符串类型）
         */
        public static final String NORMAL = "1";

        /**
         * 正常状态（数值类型）
         */
        public static final int NORMAL_INT = 1;

        /**
         * 逻辑删除状态（数值类型）
         */
        public static final int DELETED_INT = -1;

        /**
         * 冻结状态（数值类型）
         */
        public static final int FROZEN_INT = 2;
    }

    /**
     * 类别 分类 常量
     */
    public static final class Category {
        public static final String COMMODITY = "COMMODITY";//商品类别

        public static final String UNIT = "UNIT";//单位类别

        public static final String WAREHOUSE = "WAREHOUSE";//仓库类别

        public static final String CUSTOMER = "CUSTOMER";//客户类别
    }

    /**
     * 单据审批状态(-1驳回 0草稿 1待审批 2审批中 3通过)
     */
    public static final class ApprovalStatus {
        /**
         * 审批驳回（字符串类型）
         */
        public static final String REJECT = "-1";
        /**
         * 草稿（字符串类型）
         */
        public static final String DRAFT = "0";
        /**
         * 待审批（字符串类型）
         */
        public static final String PENDING_APPROVAL= "1";
        /**
         * 审批中（字符串类型）
         */
        public static final String IN_APPROVAL = "2";
        /**
         * 审批通过（字符串类型）
         */
        public static final String ADOPT = "3";
        /**
         * 审批驳回（数值类型）
         */
        public static final int REJECT_INT = -1;
        /**
         * 草稿（数值类型）
         */
        public static final int DRAFT_INT = 0;
        /**
         * 待审批（字符串类型）
         */
        public static final int PENDING_APPROVAL_INT= 1;
        /**
         * 审批中（数值类型）
         */
        public static final int IN_APPROVAL_INT = 2;
        /**
         * 审批通过（数值类型）
         */
        public static final int ADOPT_INT = 3;
    }


    /**
     * 审批动作(-1驳回；1同意)
     */
    public static final class ApprovalAction {
        /**
         * 驳回（字符串类型）
         */
        public static final String REJECT = "-1";
        /**
         * 同意（字符串类型）
         */
        public static final String AGREE = "1";
        /**
         * 驳回（数值类型）
         */
        public static final int REJECT_INT = -1;
        /**
         * 同意（数值类型）
         */
        public static final int AGREE_INT = 1;
    }



    /**
     * 关联activiti业务字段
     */
    public static final class Key
    {
        /**
         * 采购管理-入库
         */
        public static final String PURCHASE_WAREHOUSE = "purchaseWarehouse";
        /**
         * 采购管理-退货
         */
        public static final String PURCHASE_RETURN = "purchaseReturn";

        /**
         * 出库管理-出库
         */
        public static final String OUT_WAREHOUSE = "outWarehouse";
        /**
         * 出库管理-退货
         */
        public static final String OUT_RETURN = "return";
        /**
         * 调拨单
         */
        public static final String ALLOT = "allot";
        /**
         * 盘点单
         */
        public static final String CHECK = "check";
    }

    /**
     * 订单来源标识（仓库中使用）
     */
    public static final class BillSource
    {
        /**来源采购入库**/
        public static final String PURCHASE_WAREHOUSE = "purchaseWarehouse";
        /**出库退货**/
        public static final String RETURN = "returnGoods";
        /**调拨单**/
        public static final String ALLOT = "allot";
    }

}
