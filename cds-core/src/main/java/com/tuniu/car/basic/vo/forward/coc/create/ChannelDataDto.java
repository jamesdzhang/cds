package com.tuniu.car.basic.vo.forward.coc.create;

/**
 * Created by zhangyaping on 2017/5/23.
 */
public class ChannelDataDto {

    /**
     * 参考http://wiki.tuniu.org/pages/viewpage.action?pageId=60156690
     */
    private String pValue="888";
    private Integer pSource = 3;
    private Integer clientType = 70000;

    public String getpValue() {
        return pValue;
    }

    public void setpValue(String pValue) {
//        this.pValue = pValue;
    }

    public Integer getpSource() {
        return pSource;
    }

    public void setpSource(Integer pSource) {
//        this.pSource = pSource;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
//        this.clientType = clientType;
    }
}
