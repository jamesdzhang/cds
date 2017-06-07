package com.tuniu.car.basic.vo.forward.coc.loss.check.returned;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangyaping on 2017/5/27.
 */
public class CocLossDetailDto {

    private BigDecimal breakContractPrice;
    private BigDecimal backPrice;
    private Date effectiveTime;

    public BigDecimal getBreakContractPrice() {
        return breakContractPrice;
    }

    public void setBreakContractPrice(BigDecimal breakContractPrice) {
        this.breakContractPrice = breakContractPrice;
    }

    public BigDecimal getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
}
