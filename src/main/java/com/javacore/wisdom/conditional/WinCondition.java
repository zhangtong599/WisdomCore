package com.javacore.wisdom.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

//用于判断是否Windows系统
public class WinCondition implements Condition {
	
	public boolean matches(ConditionContext context,AnnotatedTypeMetadata metadata){
		return context.getEnvironment().getProperty("os.name").toLowerCase().contains("windows");
	}

}
