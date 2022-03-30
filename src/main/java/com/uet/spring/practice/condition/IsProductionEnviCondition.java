package com.uet.spring.practice.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;

public class IsProductionEnviCondition extends EnviProfile implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var enabledProfiles = context.getEnvironment().getActiveProfiles();
        var isProdEnviRunning = Arrays.asList(enabledProfiles).contains(this.PRODUCTION_ENVI);

        return isProdEnviRunning;
    }
}
