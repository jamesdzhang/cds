package com.tuniu.car.basic.constants;



public enum CarOrderStateDefinition {

    /**
     * "000000", "未知状态"
     */
    UNKNOW(new OrderState(OrderState.UNKNOWN,"000000",null, "未知状态")),

    /**
     * "OS0000", "初始状态"
     */
    INITIAL(new OrderState(OrderState.CREATED,"OS0000",null, "初始状态")),

    /**
     * "OS0002", "需求确认"
     */
    NEED_SURE(new OrderState(OrderState.CREATED,"OS0002",null, "需求确认")),

    /**
     * "OS0002D","占位反馈中"
     */
    NEED_SURE_OCCUPY_ING(new OrderState(OrderState.CREATED,"OS0002D",null, "占位反馈中")),

    /**
     * "OS0002C", "占位失败"
     */
    NEED_SURE_OCCUPY_FAIL(new OrderState(OrderState.CREATED,"OS0002C",null, "占位失败")),

    /**
     * "OS0002G","占位成功"
     */
    NEED_SURE_OCCUPY_SUC(new OrderState(OrderState.CREATED,"OS0002G",null, "占位成功")),

    /**
     * "OS0003", "待付款"
     */
    NEED_SIGN_PAY(new OrderState(OrderState.CREATED,"OS0003",null, "待付款")),

    /**
     * "OS0004", "确认中"
     */
    NEED_SUREING(new OrderState(OrderState.CONFIRMING,"OS0004",OrderState.CREATED, "确认中")),

    /**
     * "OS0008", "待提车"
     */
    BEFORE_USECAR(new OrderState(OrderState.CONFIRMED,"OS0008",OrderState.CONFIRMING, "用车前")),

    /**
     * "OS0009", "用车中"
     */
    USECAR_ING(new OrderState(OrderState.CONFIRMED,"OS0009",OrderState.CONFIRMING, "用车中")),

    /**
     * "OS0010", "已使用"
     */
    AFTER_USECAR(new OrderState(OrderState.CONFIRMED,"OS0010",OrderState.CONFIRMING, "用车后")),

    /**
     * "OS0111", "用车后"
     */
    COMPLETED_USECAR(new OrderState(OrderState.DONE,"OS0111",OrderState.CONFIRMED, "已完成")),

    /**
     * "OS0013A", "取消订单核损中"
     */
    CANCEL_REMOVE_CHECKING(new OrderState(OrderState.LOSS_CHECKING,"OS0013A",OrderState.CONFIRMED, "核损中")),

    /**
     * "OS0014A", "取消订单核损已反馈"
     */
    CANCEL_REMOVE_CHECK_FEEDBACK(new OrderState(OrderState.LOSS_CHECKED,"OS0014A",OrderState.LOSS_CHECKING, "核损已反馈")),

    /**
     * "OS0098", "已取消(签约后取消)"
     */
    CANCLE_AFS_OVER(new OrderState(OrderState.CANCELED,"OS0098",OrderState.LOSS_CHECKED, "已取消(签约后取消)")),

    /**
     * "OS0099", "已取消"
     */
    CANCLE_OVER(new OrderState(OrderState.CANCELED,"OS0099",OrderState.LOSS_CHECKED, "已取消")),


    /**
     * "OS0005","已确认"(给国际租车新加的状态。)
     */
    HAD_SUREING(new OrderState(OrderState.CONFIRMED,"OS0005", OrderState.CONFIRMING, "已确认"));


    private OrderState orderState;

    private CarOrderStateDefinition(OrderState orderState) {
        this.orderState = orderState;
    }

    /**
     * 获取状态code
     * 
     * @return
     */
    public String getStateCode() {
        return orderState.getStateCode();
    }

    /**
     * 获取状态desc
     * 
     * @return
     */
    public String getStateDesc() {
        return orderState.getStateDesc();
    }

    public static Integer compareTo(String param, String compareParam) {
        return param.compareTo(compareParam);
    }

    public OrderState getOrderState() {
        return orderState;
    }

    /**
     * 根据状态码获取状态描述
     * 
     * @param cocCode
     * @return
     */
    public static OrderState getStateByCocCode(String cocCode) {
        for (CarOrderStateDefinition tmp : CarOrderStateDefinition.values()) {
            if (tmp.getOrderState().getCocCode().equals(cocCode)) {
                return tmp.getOrderState();
            }
        }
        return UNKNOW.getOrderState();
    }


    public static OrderState getStateBySysCode(String sysCode) {
        for (CarOrderStateDefinition tmp : CarOrderStateDefinition.values()) {
            if (tmp.getOrderState().getStateCode().equals(sysCode)) {
                return tmp.getOrderState();
            }
        }
        return UNKNOW.getOrderState();
    }

    
    public static boolean canLossCheck(String code){
    	return code.equals(OrderState.CONFIRMED);
    }

    public static boolean canCanceled(String code){
        return code.equals(OrderState.LOSS_CHECKED);
    }

}
