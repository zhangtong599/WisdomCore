package com.javacore.wisdom.p2p;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class P2PClient {

	// log record
	private Logger logger = Logger.getLogger(P2PClient.class);
	
	
	//local p2p server url
	private String wsUrlLocal;
	
	
	
	

}
