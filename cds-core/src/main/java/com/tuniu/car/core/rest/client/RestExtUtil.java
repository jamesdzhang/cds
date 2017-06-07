package com.tuniu.car.core.rest.client;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tuniu.operation.platform.base.codec.BinaryEncoderHelper;
import com.tuniu.operation.platform.llt.base.ResponseVo;
import com.tuniu.operation.platform.llt.util.jackjson.JackJsonUtil;

/**
 * 重写一次，由于COC下单是不编码解码的
 */
public class RestExtUtil {
	public static final String POST = "POST";
	public static final String GET = "GET";
	public static final String DELETE = "DELETE";

	/**
	 * 
	 * 此方法描述的是：设置response head
	 * 
	 * @param response
	 *            HttpServletResponse
	 */
	private static void setHead(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
	}

	/**
	 * rest 输出
	 * 
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	public static void write(HttpServletResponse response, Object data)
			throws IOException {
		Object retData=data;
		if (!(data instanceof ResponseVo)) {
			ResponseVo rep = new ResponseVo();
			rep.setData(data);
			retData=rep;
		}
		setHead(response);
		String param = JackJsonUtil.toJson(retData);
		response.getWriter()
				.print(BinaryEncoderHelper.encodeBase64String(param
						.getBytes("UTF-8")));
	}

	public static String getData(HttpServletRequest request) throws Exception {
		String method = request.getMethod();
		String ret = null;
		if (method.equalsIgnoreCase(RestExtUtil.GET)
				|| method.equalsIgnoreCase(RestExtUtil.DELETE)) {
			ret = request.getQueryString();
		} else {
			ret = getBodyData(request);
		}
		if (ret == null) {
			return null;
		}
		return ret;
	}

	private static String getBodyData(HttpServletRequest request)
			throws Exception {
		StringBuffer data = new StringBuffer();
		String line = null;
		BufferedReader reader;
		try {
			reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e.getCause());
		}
		return data.toString();
	}
	
	
	/**
	 * rest 请求
	 * 
	 * @param param
	 * @param url
	 * @param method
	 * @return
	 */
	public static String sendData(String param, String url, String method, boolean base64Needed) {
		String ret = null;
		try {
			RestExtClient client=new RestExtClient(url,method,param,base64Needed);
			client.setConnectTimeout(5);
			ret = client.execute();
			if (BinaryEncoderHelper.isBase64(ret)) {
				ret = new String(BinaryEncoderHelper.decodeBase64(ret), "UTF-8");
			}
		} catch (Exception e) {
			throw new RuntimeException("REST接口异常"
					+ (e != null ? e.getMessage() : ""), e);
		} finally {
		}
		return ret;
	}

}
