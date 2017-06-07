package com.tuniu.car.basic.vo.forward.coc.create;

import com.tuniu.car.basic.vo.request.PoiInfoVo;

/**
 * Created by zhangyaping on 2017/5/23.
 */
public class ResourceBasicInfoDto {

    private String serviceName;
    private String serviceCode;
    private String suppliersName;
    private String suppliersCode;
    private Integer lvFlag;
    private String vehicleGroupCode;
    private Integer locationType;
    private String locationCode;
    private PoiInfoVo poi;
    private String startAddressPoi;
    private String destAddressPoi;
    private String pid;
    private String shuttleCarFlightNo;
    private String flightStartTime;
    private String flightArriveTime;
    private String useTime;
    private String Remark;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getSuppliersName() {
        return suppliersName;
    }

    public void setSuppliersName(String suppliersName) {
        this.suppliersName = suppliersName;
    }

    public String getSuppliersCode() {
        return suppliersCode;
    }

    public void setSuppliersCode(String suppliersCode) {
        this.suppliersCode = suppliersCode;
    }

    public Integer getLvFlag() {
        return lvFlag;
    }

    public void setLvFlag(Integer lvFlag) {
        this.lvFlag = lvFlag;
    }

    public String getVehicleGroupCode() {
        return vehicleGroupCode;
    }

    public void setVehicleGroupCode(String vehicleGroupCode) {
        this.vehicleGroupCode = vehicleGroupCode;
    }

    public Integer getLocationType() {
        return locationType;
    }

    public void setLocationType(Integer locationType) {
        this.locationType = locationType;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public PoiInfoVo getPoi() {
        return poi;
    }

    public void setPoi(PoiInfoVo poi) {
        this.poi = poi;
    }

    public String getShuttleCarFlightNo() {
        return shuttleCarFlightNo;
    }

    public void setShuttleCarFlightNo(String shuttleCarFlightNo) {
        this.shuttleCarFlightNo = shuttleCarFlightNo;
    }

    public String getFlightStartTime() {
        return flightStartTime;
    }

    public void setFlightStartTime(String flightStartTime) {
        this.flightStartTime = flightStartTime;
    }

    public String getFlightArriveTime() {
        return flightArriveTime;
    }

    public void setFlightArriveTime(String flightArriveTime) {
        this.flightArriveTime = flightArriveTime;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
