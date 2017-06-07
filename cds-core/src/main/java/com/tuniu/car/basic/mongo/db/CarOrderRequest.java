package com.tuniu.car.basic.mongo.db;


import java.io.Serializable;

public class CarOrderRequest<T extends SeqPacket> extends AbstractMongoReference implements Serializable {

	/**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 1L;

	private T requestDetail;

	public T getRequestDetail() {
		return requestDetail;
	}

	public void setRequestDetail(T requestDetail) {
		this.requestDetail = requestDetail;
	}
	
}
