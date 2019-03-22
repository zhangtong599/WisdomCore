package com.javacore.wisdom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.javacore.wisdom.cache.CheckConfigCache;

@Configuration
@PropertySource("classpath:wisdom.properties")
@ConfigurationProperties(prefix = "check")
public class CheckProperties {
	
	//set verified blocks when startup
	private int verifyblocks;
	
	
	public int getVerifyblocks() {
		return verifyblocks;
	}

	public void setVerifyblocks(int verifyblocks) {
		this.verifyblocks = verifyblocks;
	}
	
	public  void LoadMapCheckProperties() {
		CheckConfigCache.checkcgc.put("verifyblocks", String.valueOf(this.verifyblocks) );
	}
}
