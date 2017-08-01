package com.lichenxing.routingdatasource.routing.domain;

import com.lichenxing.routingdatasource.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import java.io.IOException;

/**
 * BodyMessageAttrbuteConverter
 *
 * @author Chenxing Li
 * @date 24/07/2017 16:57
 */
@Slf4j
public class BodyMessageAttributeConverter implements AttributeConverter<BodyMessage, String> {

    @Override
    public String convertToDatabaseColumn(BodyMessage attribute) {
        if (attribute == null) {
            return null;
        }
        return JSONUtil.mapToJsonString(attribute);
    }

    @Override
    public BodyMessage convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return null;
        }
        try {
            return JSONUtil.getObjectMapper().readValue(dbData, BodyMessage.class);
        } catch (IOException e) {
            log.error("convert json error body: {}", dbData);
        }
        return null;
    }
}
