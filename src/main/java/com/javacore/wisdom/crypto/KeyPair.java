package com.javacore.wisdom.crypto;

public class KeyPair {
	private String privKey;
	private String pubKey;

	public static byte[] GetKeyPair(){
		return new byte[64];
	}
}
