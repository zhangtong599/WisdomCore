package com.javacore.wisdom.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacore.wisdom.cache.NetConfigCache;
import com.javacore.wisdom.util.StringUtil;


@RestController
@Configuration
public class Test {

	private  Logger logger=Logger.getLogger(StringUtil.class);
	
	/*
	 @Autowired
	 private ArgonManage am;
	 */
	
	@RequestMapping("/")
    public String TestInfo() {
		
		logger.info("test info\n");
        return NetConfigCache.netcgc.get("rpcport");
    }
	
}
