/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: rongzhiming
 * Date: 2015年4月10日
 * Description:IpUtil.java 
 */
package com.tuniu.car.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * ip工具
 *
 * @author rongzhiming
 */
public class IpUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP     
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
//                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    public static String getServerIP() {
        try {
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
            if (interfaceList != null) {
                String ipAddr = null;
                while (interfaceList.hasMoreElements()) {
                    NetworkInterface iface = interfaceList.nextElement();
                    Enumeration<InetAddress> addrList = iface.getInetAddresses();
                    while (addrList.hasMoreElements()) {
                        InetAddress address = addrList.nextElement();
                        if (address instanceof Inet4Address) {
                            ipAddr = address.getLocalHost().getHostAddress();
                            if (ipAddr.startsWith("127.0.0.1")) {
                                ipAddr = address.getHostAddress();
                                if (ipAddr.startsWith("127.0.0.1")) {
                                    continue;
                                } else {
                                    return ipAddr;
                                }
                            } else {
                                return ipAddr;
                            }
                        }
                    }
                }
            }
            return "127.0.0.1";
        } catch (Exception e) {
            return "127.0.0.1";
        }
    }
}
