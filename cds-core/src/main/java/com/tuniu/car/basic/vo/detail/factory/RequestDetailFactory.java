package com.tuniu.car.basic.vo.detail.factory;


import com.fasterxml.jackson.databind.JsonNode;
import com.tuniu.car.basic.enums.RequestTypeEnum;
import com.tuniu.car.basic.param.resolve.ObjectMapperFactory;
import com.tuniu.car.basic.vo.request.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author James
 */
public class RequestDetailFactory {
	
	private static final Map<RequestTypeEnum, Class<? extends RequestDetail>> requestDetailTypes =
			new HashMap<RequestTypeEnum, Class<? extends RequestDetail>>();
	
	static {
		requestDetailTypes.put(RequestTypeEnum.BOOK, DomesticShuttleRequestDetail.class);
		requestDetailTypes.put(RequestTypeEnum.CANCEL, OrderCancelRequestDetail.class);
		requestDetailTypes.put(RequestTypeEnum.LOSS_CHECK, LossCheckRequestDetail.class);
		requestDetailTypes.put(RequestTypeEnum.STATUS_QUERY, OrderQueryRequestDetail.class);
	}

	public static Class<? extends RequestDetail> getRequestDetailType(String resourceType) {
		return getRequestDetailType(RequestTypeEnum.codeOf(resourceType));
	}
	
	public static Class<? extends RequestDetail> getRequestDetailType(RequestTypeEnum resourceType) {
		return requestDetailTypes.get(resourceType);
	}
		
	public static <T extends RequestDetail> T getRequestDetailType(String resourceType, JsonNode detail) {
		return getRequestDetailType(RequestTypeEnum.codeOf(resourceType), detail);
	}
	
	@SuppressWarnings("unchecked")
    public static <T extends RequestDetail> T getRequestDetailType(RequestTypeEnum resourceType, JsonNode detail) {
		return (T) ObjectMapperFactory.getDefaultObjectMapper().convertValue(detail, requestDetailTypes.get(resourceType));
	}
	
}
