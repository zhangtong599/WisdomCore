package com.javacore.wisdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.javacore.wisdom.init.LaunchManage;

@SpringBootApplication

//@EnableScheduling
public class WisdomApplication extends SpringBootServletInitializer   {

	// TODO: mac log4j properties
	static {
		LaunchManage.InitLog4j();
	}
	
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(WisdomApplication.class, args);
	}
	


	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<WisdomApplication> applicationClass = WisdomApplication.class;
    
    
	
}
