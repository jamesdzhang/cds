package com.tuniu.car.basic.vo.forward.coc.create;

import java.util.List;

/**
 * Created by zhangyaping on 2017/5/23.
 */
public class JourneyDto {

    private List<ResourceBasicInfoDto> resourceShuttleCar;
    private List<CarResourceDto> resourceCar;
    private List<ResourceCharteredBusDto> charteredBusList;

    public List<ResourceBasicInfoDto> getResourceShuttleCar() {
        return resourceShuttleCar;
    }

    public void setResourceShuttleCar(List<ResourceBasicInfoDto> resourceShuttleCar) {
        this.resourceShuttleCar = resourceShuttleCar;
    }
}
