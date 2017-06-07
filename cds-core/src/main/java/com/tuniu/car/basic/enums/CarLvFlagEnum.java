package com.tuniu.car.basic.enums;

/**
 * 用车三级品类枚举
 * - 借用用车的
 * Created by guolong on 2016/12/14.
 * update by James
 */
public enum CarLvFlagEnum {

    DomesticRentCar(1, "国内自驾", 90),
    IntlRentCar(2, "海外自驾", 1440),
    DomesticShuttleCarFromFlight(3, "国内接送(接机)", 90),
    DomesticShuttleCarToFlight(4, "国内接送(送机)", 90),
    IntlShuttleCarFromFlight(5, "海外接送(接机)", 90),
    IntlShuttleCarToFlight(6, "海外接送(送机)", 90),
    DomesticShuttleCarFromTrain(7, "国内接送(接火车)", 90),
    DomesticShuttleCarToTrain(8, "国内接送(送火车)", 90),

    DomesticBus(9, "国内巴士", 90),
    IntlBus(10, "海外巴士", 90),
    DomesticChartered(11, "国内包车", 90),
    IntlChartered(12, "海外包车", 90);

    private Integer lvFlag;
    private String remark;
    //单位 分钟
    private int confirmFeedBackTime;

    CarLvFlagEnum(Integer lvFlag, String remark, int confirmFeedBackTime) {
        this.lvFlag = lvFlag;
        this.remark = remark;
        this.confirmFeedBackTime = confirmFeedBackTime;
    }

    public static Boolean isDomesticShuttleCar(Integer lvFlag) {

        CarLvFlagEnum[] carLfs = {DomesticShuttleCarFromFlight, DomesticShuttleCarFromTrain,
                DomesticShuttleCarToFlight, DomesticShuttleCarToTrain};
        for (CarLvFlagEnum loop : carLfs) {
            if (loop.getLvFlag() == lvFlag) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    public static Boolean isDomesticCar(Integer lvFlag) {

        CarLvFlagEnum[] carLfs = {DomesticRentCar};
        for (CarLvFlagEnum loop : carLfs) {
            if (loop.getLvFlag() == lvFlag) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    public static Boolean isInternationalCar(Integer lvFlag) {

        CarLvFlagEnum[] carLfs = {IntlRentCar};
        for (CarLvFlagEnum loop : carLfs) {
            if (loop.getLvFlag() == lvFlag) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    public static Boolean isInternationalShuttleCar(Integer lvFlag) {

        CarLvFlagEnum[] carLfs = {IntlShuttleCarFromFlight, IntlShuttleCarToFlight};
        for (CarLvFlagEnum loop : carLfs) {
            if (loop.getLvFlag() == lvFlag) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    public static CarLvFlagEnum valueOf(Integer lvFlag) {
        CarLvFlagEnum[] carLvAll = {DomesticRentCar, IntlRentCar, DomesticShuttleCarFromFlight, DomesticShuttleCarToFlight,
                IntlShuttleCarFromFlight, IntlShuttleCarToFlight, DomesticShuttleCarFromTrain, DomesticShuttleCarToTrain,
                DomesticBus, IntlBus, DomesticChartered, IntlChartered};
        for (CarLvFlagEnum loop : carLvAll) {
            if (loop.getLvFlag() == lvFlag) {
                return loop;
            }
        }
        return null;
    }

    public Integer getLvFlag() {
        return this.lvFlag;
    }

    public String getRemark() {
        return this.remark;
    }

    public Integer getConfirmFeedBackTime() {
        return this.confirmFeedBackTime;
    }

}
