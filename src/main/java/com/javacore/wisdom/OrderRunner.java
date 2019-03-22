package com.javacore.wisdom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.javacore.wisdom.cache.CheckConfigCache;
import com.javacore.wisdom.cache.DataConfigCache;
import com.javacore.wisdom.cache.NetConfigCache;
import com.javacore.wisdom.cache.NodeConfigCache;
import com.javacore.wisdom.config.CheckProperties;
import com.javacore.wisdom.config.DataProperties;
import com.javacore.wisdom.config.NetProperties;
import com.javacore.wisdom.config.NodeProperties;

@Component
@Order(1)
public class OrderRunner implements CommandLineRunner {

	@Autowired
	private NetProperties netpt;
	
	@Autowired
	private NodeProperties nodpt;
	
	@Autowired
	private DataProperties datapt;
	
	@Autowired
	private CheckProperties checkpt;

	@Override
	public void run(String... args) throws Exception {

		this.AssignCacheValue();

		System.out.println("The OrderRunner start to initialize ...");
	}

	private void AssignCacheValue() {

		NetConfigCache.netcgc.put("p2pport", String.valueOf(netpt.getP2pport()));

		NetConfigCache.netcgc.put("testp2pport", String.valueOf(netpt.getTestp2pport()));

		NetConfigCache.netcgc.put("rpcport", String.valueOf(netpt.getRpcport()));

		NetConfigCache.netcgc.put("testrpctport", String.valueOf(netpt.getTestrpctport()));

		NetConfigCache.netcgc.put("testmode", String.valueOf(netpt.getTestmode()));

		NetConfigCache.netcgc.put("networkid", String.valueOf(netpt.getNetworkid()));

		NetConfigCache.netcgc.put("rpcaddr", String.valueOf(netpt.getRpcaddr()));

		NetConfigCache.netcgc.put("maxpeers", String.valueOf(netpt.getMaxpeers()));

		NetConfigCache.netcgc.put("rpcallowip", String.valueOf(netpt.getRpcallowip()));

		NetConfigCache.netcgc.put("rpcuser", String.valueOf(netpt.getRpcuser()));

		NetConfigCache.netcgc.put("rpcpassword", String.valueOf(netpt.getRpcpassword()));
		
		
		NodeConfigCache.nodecgc.put("nodiscover", String.valueOf(nodpt.getNodiscover()));
		
		DataConfigCache.datacgc.put("datadir", String.valueOf(datapt.getDatadir()));
		
		CheckConfigCache.checkcgc.put("verifyblocks", String.valueOf(checkpt.getVerifyblocks()));
		

	}

}
