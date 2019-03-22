package com.javacore.wisdom.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition{
	public boolean matches(ConditionContext context,AnnotatedTypeMetadata metadata){
		return context.getEnvironment().getProperty("os.name").toLowerCase().contains("mac");
	}

}
