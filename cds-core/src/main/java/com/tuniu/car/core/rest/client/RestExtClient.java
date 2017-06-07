//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tuniu.car.core.rest.client;

import com.tuniu.operation.platform.base.rest.RestException;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class RestExtClient {
	private String serverUrl;
	private String httpMethod;
	private String clientData;
	private int connectTimeout;
	private int socketTimeout;
	private boolean base64Needed = true;

	public RestExtClient(String url, String method, String data, boolean base64Needed) {
		this.serverUrl = url;
		this.httpMethod = method;
		this.clientData = data;
		this.connectTimeout = -1;
		this.socketTimeout = -1;
		this.base64Needed = base64Needed;
	}

	public RestExtClient(String url, String method, String data) {
		this.serverUrl = url;
		this.httpMethod = method;
		this.clientData = data;
		this.connectTimeout = -1;
		this.socketTimeout = -1;
	}

	public RestExtClient(String url) {
		this.serverUrl = url;
		this.httpMethod = "GET";
		this.clientData = null;
		this.connectTimeout = -1;
		this.socketTimeout = -1;
	}

	public RestExtClient(String url, String data) {
		this.serverUrl = url;
		this.httpMethod = "GET";
		this.clientData = data;
		this.connectTimeout = -1;
		this.socketTimeout = -1;
	}

	public void setURL(String url) {
		this.serverUrl = url;
	}

	public void setMethod(String method) {
		this.httpMethod = method;
	}

	public void setData(String data) {
		this.clientData = data;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public String execute() throws RestException {
		if(null == this.serverUrl) {
			return null;
		} else {
			this.clientData = RestCodec.encodeData(this.clientData);
			String ret = this.httpExecute();
			if(null != ret && base64Needed) {
				ret = RestCodec.decodeData(ret);
			}

			return ret;
		}
	}


	private DefaultHttpClient getHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter("http.protocol.content-charset", "utf-8");
		if(0 < this.connectTimeout) {
			httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(this.connectTimeout * 1000));
		}

		if(0 < this.socketTimeout) {
			httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(this.socketTimeout * 1000));
		}

		return httpClient;
	}

	private String getURLWithData() {
		return this.clientData != null?(this.serverUrl.endsWith("?")?this.serverUrl + this.clientData:this.serverUrl + "?" + this.clientData):this.serverUrl;
	}

	private String httpExecute() throws RestException {
		String ret = null;
		DefaultHttpClient httpclient = this.getHttpClient();

		try {
			BasicResponseHandler e = new BasicResponseHandler();
			if(this.httpMethod.equalsIgnoreCase("GET")) {
				HttpGet req = new HttpGet(this.getURLWithData());
				ret = (String)httpclient.execute(req, e);
			} else if(this.httpMethod.equalsIgnoreCase("POST")) {
				HttpPost req1 = new HttpPost(this.serverUrl);
				if(null != this.clientData) {
					req1.setEntity(new StringEntity(this.clientData));
				}

				ret = (String)httpclient.execute(req1, e);
			} else if(this.httpMethod.equalsIgnoreCase("PUT")) {
				HttpPut req2 = new HttpPut(this.serverUrl);
				if(null != this.clientData) {
					req2.setEntity(new StringEntity(this.clientData));
				}

				ret = (String)httpclient.execute(req2, e);
			} else if(this.httpMethod.equalsIgnoreCase("DELETE")) {
				HttpDelete req3 = new HttpDelete(this.getURLWithData());
				ret = (String)httpclient.execute(req3, e);
			}
		} catch (ClientProtocolException var10) {
			throw new RestException(var10.getMessage(), var10.getCause());
		} catch (IOException var11) {
			throw new RestException(var11.getMessage(), var11.getCause());
		} catch (Exception var12) {
			throw new RestException(var12.getMessage(), var12.getCause());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return ret;
	}
}
