package com.tuniu.car.basic.param.resolve.bean.translate;

/**
 * 
 * @copyright(C)  James
 * @author James
 */
public interface FieldTranslater {

    public <T> T translate(final Object targetObj, final String srcFieldName, final String additional,
                           final Class<? extends Object> additionalClass, final Class<T> type) throws TranslateException;

}
