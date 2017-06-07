//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tuniu.car.basic.vo.request;

import com.tuniu.car.basic.vo.bean.validation.constant.BeanValidationConstants;

import javax.validation.constraints.NotNull;

public class PoiInfoVo {
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String name;
    private String cityCode;
    private String cityName;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String address;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private Double longitude;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private Double latitude;
    private int type;//默认0 不知道什么用

    public PoiInfoVo() {
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
