package com.tuniu.car.basic.vo.forward.coc.create;

import com.tuniu.car.basic.param.build.intf.ICocOrderParam;

import java.util.List;

/**
 * Created by zhangyaping on 2017/5/23.
 */
public class MultiPrdCreateOrderInputDto implements ICocOrderParam {

    private MultiOrderHeadDto order;
    private ChannelDataDto channelData =  new ChannelDataDto();
    private List<CreatePrdOrderDto> subOrders;
    private boolean isNewPayCenter = false;
    private int sessionId = 0;

    public ChannelDataDto getChannelData() {
        return channelData;
    }

    public void setChannelData(ChannelDataDto channelData) {
        this.channelData = channelData;
    }

    public boolean isNewPayCenter() {
        return isNewPayCenter;
    }

    public void setNewPayCenter(boolean newPayCenter) {
//        isNewPayCenter = newPayCenter;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
//        this.sessionId = sessionId;
    }

    public MultiOrderHeadDto getOrder() {
        return order;
    }

    public void setOrder(MultiOrderHeadDto order) {
        this.order = order;
    }

    public List<CreatePrdOrderDto> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<CreatePrdOrderDto> subOrders) {
        this.subOrders = subOrders;
    }
}
