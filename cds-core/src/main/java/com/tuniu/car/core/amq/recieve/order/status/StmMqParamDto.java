package com.tuniu.car.core.amq.recieve.order.status;


/**
 * 状态机向mq发送的入参dto
 * From COC
 * @author liuhaitao
 * 
 */
public class StmMqParamDto {
    /**
     * 订单号
     */
    private Integer orderId;
    /**
     * 订单来源
     * 判断是否属于CDS来源的依据
     */
    private String resourceChannel;
    /**
     * 各个应用客户端队列名称
     */
    private String queueName;

    private Integer flowId;

    /**
     * 当前订单状态
     */
    private String orderStatus;

    /**
     * ACTION运行之后的订单状态
     */
    private String orderStatusAfter;

    /**
     * stm服务器队列
     */
    private String stmServerQueueName;

    private String logicCode;

    /**
     * 当前操作用户id
     */
    private Integer currentUid;

    /**
     * 当前操作用户名称
     */
    private String currentUserName;

    /**
     * 入参数据
     */
    private Object data;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCurrentUid() {
        return currentUid;
    }

    public void setCurrentUid(Integer currentUid) {
        this.currentUid = currentUid;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusAfter() {
        return orderStatusAfter;
    }

    public void setOrderStatusAfter(String orderStatusAfter) {
        this.orderStatusAfter = orderStatusAfter;
    }

    public String getStmServerQueueName() {
        return stmServerQueueName;
    }

    public void setStmServerQueueName(String stmServerQueueName) {
        this.stmServerQueueName = stmServerQueueName;
    }

    public String getLogicCode() {
        return logicCode;
    }

    public void setLogicCode(String logicCode) {
        this.logicCode = logicCode;
    }

    public String getResourceChannel() {
        return resourceChannel;
    }

    public void setResourceChannel(String resourceChannel) {
        this.resourceChannel = resourceChannel;
    }
}
