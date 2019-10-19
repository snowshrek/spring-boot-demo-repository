package com.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by David on 2019/10/17.
 */
public class JdbcTemplateCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context,
                           AnnotatedTypeMetadata metadata) {
        try {
            context.getClassLoader().loadClass(
                    "com.domain.Book");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
