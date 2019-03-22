/**
 * 
 */
package com.javacore.wisdom.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * this is a configuration class
 */
@Configuration
public class ConditionConfig {
	
	@Bean
	@Conditional(WinCondition.class)
	public DoService WinDoService(){
		return new WinDoService();
	}
	
	@Bean
	@Conditional(LinuxCondition.class)
	public DoService LinuxDoService(){
		return new LinuxDoService();
	}
	
	@Bean
	@Conditional(MacCondition.class)
	public DoService MacDoService(){
		return new MacDoService();
	
	}
}
