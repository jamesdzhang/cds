package com.tuniu.car.basic.vo.request;

import javax.validation.constraints.*;

/**
 * Created by zhangyaping on 2017/5/15.
 */

public class HelloVo {

    @NotNull(message="name不能为空")
    @Size(max = 1, message = "长度不能超过"+1)
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    @Min(value = 16,message = "age不能小于16")
    @Max(value = 60,message = "age不能大于60")
    private int age;
}
