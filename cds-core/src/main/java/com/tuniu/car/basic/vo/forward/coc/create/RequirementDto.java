package com.tuniu.car.basic.vo.forward.coc.create;

import com.tuniu.car.basic.vo.request.CustomerInfoVo;

/**
 * Created by zhangyaping on 2017/5/23.
 */
public class RequirementDto {

    private String bookCity;
    private String bookCityCode;
    private String startCity;
    private String startCityCode;
    private String desCity;
    private String desCityCode;
    private String departureDate;
    private String endDate;
    private Integer duration;
    private Integer adultCount;
    private String busServiceRemark;
    private CustomerInfoVo contactDto;

    public String getBookCity() {
        return bookCity;
    }

    public void setBookCity(String bookCity) {
        this.bookCity = bookCity;
    }

    public String getBookCityCode() {
        return bookCityCode;
    }

    public void setBookCityCode(String bookCityCode) {
        this.bookCityCode = bookCityCode;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public String getBusServiceRemark() {
        return busServiceRemark;
    }

    public void setBusServiceRemark(String busServiceRemark) {
        this.busServiceRemark = busServiceRemark;
    }

    public CustomerInfoVo getContactDto() {
        return contactDto;
    }

    public void setContactDto(CustomerInfoVo contactDto) {
        this.contactDto = contactDto;
    }
}
