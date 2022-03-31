package com.uet.spring.practice.model.user;

import com.uet.spring.practice.model.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Grade implements Code<Integer> {

    SIX(6), SEVEN(7), EIGHT(8), NINE(9);

    @Getter
    private final Integer code;
}
