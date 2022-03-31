package com.uet.spring.practice.model.tag;

import com.uet.spring.practice.model.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Description implements Code<Integer> {
    SINGER(1), TEACHER(2), MANAGER(3);

    @Getter
    private final Integer code;

    public static Description fromCode(Integer code) {
        return Code.fromCode(code, Description.values());
    }

    public static Description fromCode(Integer code, Description defaultValue) {
        return Code.fromCode(code, Description.values(), defaultValue);
    }
}
