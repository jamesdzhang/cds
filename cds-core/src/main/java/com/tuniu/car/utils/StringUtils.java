package com.tuniu.car.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangyaping on 2017/6/9.
 */
public class StringUtils extends org.springframework.util.StringUtils {

    public static String msgFormat(String str,String[] arr){
        Matcher m= Pattern.compile("\\{(\\d)\\}").matcher(str);
        while(m.find()){
            str=str.replace(m.group(),arr[Integer.parseInt(m.group(1))]);
        }
        return str;
    }


}
