package com.tuniu.car.basic.vo.forward.coc.loss.check.returned;

/**
 * Created by zhangyaping on 2017/5/27.
 */
public class CocLossReturnedDto {

    private Integer resType;
    private CocLossDetailDto lossData;

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public CocLossDetailDto getLossData() {
        return lossData;
    }

    public void setLossData(CocLossDetailDto lossData) {
        this.lossData = lossData;
    }
}
