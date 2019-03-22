package com.javacore.wisdom.node;

public class Node {
	
	private String IPAddress;
	
	private int port;
	
	private int rpcport;

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getRpcport() {
		return rpcport;
	}

	public void setRpcport(int rpcport) {
		this.rpcport = rpcport;
	}
	
	

}
