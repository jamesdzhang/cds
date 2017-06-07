package com.tuniu.car.basic.vo.request;

import com.tuniu.car.basic.vo.bean.validation.constant.BeanValidationConstants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by zhangyaping on 2017/5/18.
 */
public class DomesticShuttleRequestDetail implements RequestDetail{

    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String desCity;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String desCityCode;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String startCity;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String startCityCode;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String resourceCode;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String serviceCode;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String suppliersCode;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String vehicleGroupCode;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String flightNo;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String useTime;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String locationType;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String locationCode;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String promotionCode;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    @Valid
    private PoiInfoVo poi;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    @Valid
    private CustomerInfoVo customerInfo;

    public CustomerInfoVo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfoVo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getDesCity() {
        return desCity;
    }

    public void setDesCity(String desCity) {
        this.desCity = desCity;
    }

    public String getDesCityCode() {
        return desCityCode;
    }

    public void setDesCityCode(String desCityCode) {
        this.desCityCode = desCityCode;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartCityCode() {
        return startCityCode;
    }

    public void setStartCityCode(String startCityCode) {
        this.startCityCode = startCityCode;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getSuppliersCode() {
        return suppliersCode;
    }

    public void setSuppliersCode(String suppliersCode) {
        this.suppliersCode = suppliersCode;
    }

    public String getVehicleGroupCode() {
        return vehicleGroupCode;
    }

    public void setVehicleGroupCode(String vehicleGroupCode) {
        this.vehicleGroupCode = vehicleGroupCode;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public PoiInfoVo getPoi() {
        return poi;
    }

    public void setPoi(PoiInfoVo poi) {
        this.poi = poi;
    }
}
