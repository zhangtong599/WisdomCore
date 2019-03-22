package com.javacore.wisdom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.javacore.wisdom.cache.DataConfigCache;

@Configuration
@PropertySource("classpath:wisdom.properties")
@ConfigurationProperties(prefix = "data")
public class DataProperties {
	private String datadir;
	
	
	public String getDatadir() {
		return datadir;
	}

	public void setDatadir(String datadir) {
		this.datadir = datadir;
	}
	
	public  void LoadMapDataProperties() {
		DataConfigCache.datacgc.put("datadir", String.valueOf(this.datadir) );
	}
}
