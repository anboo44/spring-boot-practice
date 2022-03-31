package com.uet.spring.practice.model.tag;

import javax.persistence.AttributeConverter;

public class DescriptionConverter implements AttributeConverter<Description, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Description description) {
        return description.getCode();
    }

    @Override
    public Description convertToEntityAttribute(Integer code) {
        if (code == null) return null;
        return Description.fromCode(code);
    }
}
