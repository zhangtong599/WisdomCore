package com.javacore.wisdom.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacore.wisdom.cache.NetConfigCache;
import com.javacore.wisdom.util.Utils;


@RestController
@Configuration
public class Test {

	private  Logger logger=Logger.getLogger(Utils.class);
	
	/*
	 @Autowired
	 private ArgonManage am;
	 */
	
	@RequestMapping("/")
    public String greeting() {
		
		logger.info("what are you doing?\n");
        return NetConfigCache.netcgc.get("rpcport");
    }
	
}
