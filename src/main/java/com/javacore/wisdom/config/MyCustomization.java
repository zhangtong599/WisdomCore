package com.javacore.wisdom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;


@Component
public class MyCustomization implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
	
	
	@Autowired
	private NetProperties np;
	

	@Override
	public void customize(ConfigurableServletWebServerFactory server) {
		int testMode=np.getTestmode();
		if(testMode==0)
			server.setPort(np.getRpcport());
		else if(testMode==1)
			server.setPort(np.getTestrpctport());
		else 
			server.setPort(60000);
	}
}
