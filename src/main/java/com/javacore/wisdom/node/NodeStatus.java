package com.javacore.wisdom.node;

public enum NodeStatus {
	
	
	/**
	 * The node is running normal
	 */
	ACTIVE,
	
	
	/**
	 * The node is not running 
	 */
	INACTIVE,
	
	
	/**
	 * The node is busy dealing with transaction
	 */
	BUSY,
	
	
	/**
	 * The node is active but occurs failure when request
	 */
	FAILURE,
	
	

	/**
	 * The node is in unknown status
	 */
	UNKNOW

}
