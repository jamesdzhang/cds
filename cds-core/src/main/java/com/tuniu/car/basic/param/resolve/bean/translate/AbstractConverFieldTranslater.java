package com.tuniu.car.basic.param.resolve.bean.translate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

/**
 * 
 * @copyright(C)  James
 * @author James
 */
public abstract class AbstractConverFieldTranslater implements FieldTranslater, InitializingBean {

    protected ConfigurableConversionService converService;

    public ConfigurableConversionService getConverService() {
        return converService;
    }

    public void setConverService(ConfigurableConversionService converService) {
        this.converService = converService;
    }

    public void afterPropertiesSet() {
        if (this.converService == null) {
            converService = new DefaultFormattingConversionService();
        }
    }

}
