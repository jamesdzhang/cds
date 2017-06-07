package com.tuniu.car.basic.param.resolve;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * 
 * @copyright(C)  James
 * @author James
 */
public class EncodingJsonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private static final Logger log = LoggerFactory.getLogger(EncodingJsonHttpMessageConverter.class);


    public EncodingJsonHttpMessageConverter() {
        super();
        setObjectMapper(ObjectMapperFactory.getDefaultObjectMapper());
    }

    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {
        try {
            byte[] bytes = getObjectMapper().writeValueAsBytes(object);
            FileCopyUtils.copy(Base64.encodeBase64(bytes), outputMessage.getBody());
        } catch (JsonProcessingException ex) {
            log.error("Could not write JSON: " + ex.getMessage(), ex);
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

}
