/*
 * Copyright (C) 2006-2012 Tuniu.com. All rights reserved
 * Author:zhaiweisen
 * Date:2012-3-2
 * Version:1.0.0
 */
package com.tuniu.car.core.rest.client;

import java.io.UnsupportedEncodingException;

import com.tuniu.operation.platform.base.rest.RestException;
import org.apache.commons.codec.binary.Base64;

/**
 * Base64 encoding and decoding
 * 
 */
public class RestCodec {
	/**
	 * decode the provided string
	 * 
	 * @param base64Data string to be decoded
	 * @return string contains decoded characters
	 * @throws RestException 
	 * 			Thrown when exception in decode
	 */	
	public static String decodeData(String base64Data) throws RestException {
		try {
			return new String(Base64.decodeBase64(base64Data.getBytes("utf-8")), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RestException(e.getMessage(), e.getCause());
		} finally {
			
		}
	}
	
	/**
	 * encode the provided string
	 * 
	 * @param binaryData string to be encoded
	 * @return string contains Base64 characters
	 * @throws RestException 
	 * 			Thrown when exception in encode
	 */	
	static String encodeData(String binaryData) throws RestException {
		try {
			if (null == binaryData) { 
				return null;
			} else {
				
			}
			return Base64.encodeBase64String(binaryData.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RestException(e.getMessage(), e.getCause());
		} finally {
			
		}
	}
}
