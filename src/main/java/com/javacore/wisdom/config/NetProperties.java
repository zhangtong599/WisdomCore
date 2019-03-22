package com.javacore.wisdom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.javacore.wisdom.cache.NetConfigCache;


@Configuration
@PropertySource("classpath:wisdom.properties")
@ConfigurationProperties(prefix = "net")
public class NetProperties {
	
	private int rpcport;
	private int testrpctport;
	private int p2pport;
	private int testp2pport;
	private int testmode;
	
	private int networkid;
	private String rpcaddr;
	private int maxpeers;
	private String rpcallowip;
	
	
	public int getNetworkid() {
		return networkid;
	}

	public void setNetworkid(int networkid) {
		this.networkid = networkid;
	}

	public String getRpcaddr() {
		return rpcaddr;
	}

	public void setRpcaddr(String rpcaddr) {
		this.rpcaddr = rpcaddr;
	}

	public int getMaxpeers() {
		return maxpeers;
	}

	public void setMaxpeers(int maxpeers) {
		this.maxpeers = maxpeers;
	}

	public String getRpcallowip() {
		return rpcallowip;
	}

	public void setRpcallowip(String rpcallowip) {
		this.rpcallowip = rpcallowip;
	}

	public String getRpcuser() {
		return rpcuser;
	}

	public void setRpcuser(String rpcuser) {
		this.rpcuser = rpcuser;
	}

	public String getRpcpassword() {
		return rpcpassword;
	}

	public void setRpcpassword(String rpcpassword) {
		this.rpcpassword = rpcpassword;
	}

	private String rpcuser;
	private String rpcpassword;

	public int getRpcport() {
		return rpcport;
	}

	public void setRpcport(int rpcport) {
		this.rpcport = rpcport;
	}


	public int getTestrpctport() {
		return testrpctport;
	}

	public void setTestrpctport(int testrpctport) {
		this.testrpctport = testrpctport;
	}

	public int getP2pport() {
		return p2pport;
	}

	public void setP2pport(int p2pport) {
		this.p2pport = p2pport;
	}

	public int getTestp2pport() {
		return testp2pport;
	}

	public void setTestp2pport(int testp2pport) {
		this.testp2pport = testp2pport;
	}

	public int getTestmode() {
		return testmode;
	}

	public void setTestmode(int testmode) {
		this.testmode = testmode;
	}
	
	
	public  void LoadMapNetProperties() {
		NetConfigCache.netcgc.put("rpcport", String.valueOf(this.rpcport) );
		NetConfigCache.netcgc.put("testrpctport", String.valueOf(this.testrpctport) );
		NetConfigCache.netcgc.put("p2pport", String.valueOf(this.p2pport) );
		NetConfigCache.netcgc.put("testp2pport", String.valueOf(this.testp2pport) );
		
		NetConfigCache.netcgc.put("testmode", String.valueOf(this.testmode) );
		NetConfigCache.netcgc.put("networkid", String.valueOf(this.networkid) );
		NetConfigCache.netcgc.put("rpcaddr", String.valueOf(this.rpcaddr) );
		
		NetConfigCache.netcgc.put("maxpeers", String.valueOf(this.maxpeers) );
		NetConfigCache.netcgc.put("rpcallowip", String.valueOf(this.rpcallowip) );
		
	}

}
