package com.tuniu.car.basic.vo.feedback;

import com.tuniu.car.basic.vo.request.RequestDetail;

/**
 * Created by zhangyaping on 2017/5/18.
 */
public class DriverInfoFeedbackDetail implements RequestDetail{

    private String orderId;
    private String driverName;
    private String servNumber;
    private String vehicleName;
    private String plateNumber;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getServNumber() {
        return servNumber;
    }

    public void setServNumber(String servNumber) {
        this.servNumber = servNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
