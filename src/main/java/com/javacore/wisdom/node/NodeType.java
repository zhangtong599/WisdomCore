package com.javacore.wisdom.node;

public enum NodeType {
	
	/**
	 * The node is full function type,includes all function module
	 */
	FULLNODE,
	
	
	
	/**
	 * The node is responsible for network router
	 */
	ROUTERNODE,
	
	
	
	/**
	 * The node is responsible for consensus
	 */
	CONSENSUSNODE,
	
	
	
	/**
	 * The node is single,not connect to the p2p network
	 */
	SINGLENODE

}
