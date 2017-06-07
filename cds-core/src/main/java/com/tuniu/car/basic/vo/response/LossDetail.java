package com.tuniu.car.basic.vo.response;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangyaping on 2017/5/19.
 */
public class LossDetail {

    private BigDecimal lossAmount;
    private Integer currencyType = 8;//参考wiki，8是人民币
    private Date expireTime;

    public BigDecimal getLossAmount() {
        return lossAmount;
    }

    public void setLossAmount(BigDecimal lossAmount) {
        this.lossAmount = lossAmount;
    }

    public Integer getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Integer currencyType) {
        this.currencyType = currencyType;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
