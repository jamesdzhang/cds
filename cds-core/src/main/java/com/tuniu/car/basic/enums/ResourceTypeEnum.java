/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: bifengle
 * Date: 2012-7-31
 * Description: 
 */
package com.tuniu.car.basic.enums;

/**
 * 资源类型
 * 
 * @author James
 */
public enum ResourceTypeEnum {

    /** 未知 */
    UNKNOW("_", 0,"未知",0,"未知"),
    /** 国内接机 */
    DOMESTIC_SHUTTLE_PICKUP("domestic_car_pickup", 3 ,"国内接机",1865098, "国内接送机"),
    /** 国内送机 */
    DOMESTIC_SHUTTLE_DROP_OFF("domestic_car_drop_off", 4,"国内送机",1865098, "国内接送机")
    ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getLvFlag() {
        return lvFlag;
    }

    public void setLvFlag(Integer lvFlag) {
        this.lvFlag = lvFlag;
    }

    public Integer getPrdLineId() {
        return prdLineId;
    }

    public void setPrdLineId(Integer prdLineId) {
        this.prdLineId = prdLineId;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    private String code;
    private String desc;
    private Integer lvFlag; //对应COC的lvFlag
//    private Integer prdId; //对应COC的productId
    private Integer prdLineId; //对应COC的productLineId
    private String prdName; //对应COC的productName

    ResourceTypeEnum(String code, Integer lvFlag, String desc, Integer prdLineId, String prdName ){
        this.desc = desc;
        this.code = code;
        this.lvFlag = lvFlag;
        this.prdLineId=prdLineId;
        this.prdName=prdName;
    }


    public static ResourceTypeEnum codeOf(String code){
        for(ResourceTypeEnum tmp : ResourceTypeEnum.values()){
            if(tmp.getCode().equals(code))
                return tmp;
        }
        return UNKNOW;
    }

}
