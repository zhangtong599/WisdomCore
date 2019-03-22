package com.javacore.wisdom.init;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.javacore.wisdom.conditional.ConditionConfig;
import com.javacore.wisdom.conditional.DoService;

public class LaunchManage {

	// Initialize log4j
	public static void InitLog4j() {

		// Load different configuration files according to different operating systems
		AnnotationConfigApplicationContext cxtConditionConfig = new AnnotationConfigApplicationContext(
				ConditionConfig.class);

		DoService ds = cxtConditionConfig.getBean(DoService.class);
		ds.InitLog4jConfig();
		cxtConditionConfig.close();

	}
	
	
	

}
