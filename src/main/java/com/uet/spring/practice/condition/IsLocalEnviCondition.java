package com.uet.spring.practice.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;

public class IsLocalEnviCondition extends EnviProfile implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var enabledProfiles = context.getEnvironment().getActiveProfiles();
        var isLocalEnviRunning = Arrays.asList(enabledProfiles).contains(this.LOCAL_ENVI);

        return isLocalEnviRunning;
    }
}
