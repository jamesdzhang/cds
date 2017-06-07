//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tuniu.car.basic.vo.request;

import com.tuniu.car.basic.vo.bean.validation.constant.BeanValidationConstants;

import javax.validation.constraints.NotNull;

public class CustomerInfoVo {

    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String customerName;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String phone;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String email;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
