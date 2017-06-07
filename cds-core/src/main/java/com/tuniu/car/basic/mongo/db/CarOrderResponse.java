package com.tuniu.car.basic.mongo.db;


import java.io.Serializable;

public class CarOrderResponse<T extends SeqPacket> extends AbstractMongoReference implements Serializable {

	/**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 1L;
    
    private T responseDetail;

	public T getResponseDetail() {
		return responseDetail;
	}

	public void setResponseDetail(T responseDetail) {
		this.responseDetail = responseDetail;
	}
	
}
