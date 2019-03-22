package com.javacore.wisdom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.javacore.wisdom.cache.NodeConfigCache;

@Configuration
@PropertySource("classpath:wisdom.properties")
@ConfigurationProperties(prefix = "node")
public class NodeProperties {
	private int nodiscover;

	public int getNodiscover() {
		return nodiscover;
	}

	public void setNodiscover(int nodiscover) {
		this.nodiscover = nodiscover;
	}
	
	
	public  void LoadMapNodeProperties() {
		NodeConfigCache.nodecgc.put("nodiscover", String.valueOf(this.nodiscover) );
	}
	
	
}
