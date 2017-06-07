package com.tuniu.car.basic.vo.forward.coc.create;


import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangyaping on 2017/5/23.
 */
public class CreatePrdOrderDto {

    private Integer orderType = 17;
    private BigDecimal totalPrice = new BigDecimal(0);
    private List<JourneyDto> journey;
    private ProductDto product;
    private RequirementDto requirement;
    private List<CustomerInfo> contactList;
    private List<TouristDto> driverList;
    private List<CarAdditionalService> additionalServices;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<JourneyDto> getJourney() {
        return journey;
    }

    public void setJourney(List<JourneyDto> journey) {
        this.journey = journey;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public RequirementDto getRequirement() {
        return requirement;
    }

    public void setRequirement(RequirementDto requirement) {
        this.requirement = requirement;
    }

    public List<CustomerInfo> getContactList() {
        return contactList;
    }

    public void setContactList(List<CustomerInfo> contactList) {
        this.contactList = contactList;
    }
}
