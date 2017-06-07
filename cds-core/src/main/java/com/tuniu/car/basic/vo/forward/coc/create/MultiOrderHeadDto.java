package com.tuniu.car.basic.vo.forward.coc.create;

/**
 * Created by zhangyaping on 2017/5/23.
 */
public class MultiOrderHeadDto {

    private Integer memberId;
    private Integer source = 3;
    private Integer orderPayType;
    private String uniqueId;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
//        this.source = source;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(Integer orderPayType) {
        this.orderPayType = orderPayType;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
