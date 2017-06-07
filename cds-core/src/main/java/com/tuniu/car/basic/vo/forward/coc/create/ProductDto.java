package com.tuniu.car.basic.vo.forward.coc.create;

/**
 * Created by zhangyaping on 2017/5/23.
 */
public class ProductDto {

    private String startDate;
    private String endDate;
    private String startCityCode;
    private String startCity;
    private String desCityCode;
    private String desCity;
    private String bookCityCode;

    private String productId;
    private String productLineName;
    private Integer productLineId;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartCityCode() {
        return startCityCode;
    }

    public void setStartCityCode(String startCityCode) {
        this.startCityCode = startCityCode;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getDesCityCode() {
        return desCityCode;
    }

    public void setDesCityCode(String desCityCode) {
        this.desCityCode = desCityCode;
    }

    public String getDesCity() {
        return desCity;
    }

    public void setDesCity(String desCity) {
        this.desCity = desCity;
    }

    public String getBookCityCode() {
        return bookCityCode;
    }

    public void setBookCityCode(String bookCityCode) {
        this.bookCityCode = bookCityCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public Integer getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }
}
