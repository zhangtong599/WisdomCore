package com.javacore.wisdom.crypto;

import java.nio.charset.Charset;

import org.springframework.stereotype.Component;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Advanced;
import de.mkammerer.argon2.Argon2Factory;

@Component
public class ArgonManage {
	
	private int iterations=4;  
	private int memoryCost=20480;
	private int parallelism=2;
	
	protected static final Charset UTF8 = Charset.forName("utf-8");
	
	
	//calculate the argon2id hash as the password
	public String getArgonHash(String password) {
		Argon2Advanced a2d=Argon2Factory.createAdvanced(Argon2Factory.Argon2Types.ARGON2id,32,64);
	
	
		
		return a2d.hash (iterations, memoryCost, parallelism,password);
		
	
	}
	
	public String getArgonHash(String password,String salt,Boolean saltPrefixFlag) {
		String strData="";
		if(saltPrefixFlag) {
			strData=salt.concat(password);
		}else {
			strData=password.concat(salt);
		}
		Argon2 ah=Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		return ah.hash(iterations, memoryCost, parallelism,strData);
	}
	
	public Boolean VerifyHash() {
		return false;
	}
	
	public ArgonManage() {
		
	}
	
	public ArgonManage(int iterations,int memoryCost,int parallelism) {
		this.iterations=iterations;
		this.memoryCost=memoryCost;
		this.parallelism=parallelism;
	}




	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public static Charset getUtf8() {
		return UTF8;
	}

	public int getMemoryCost() {
		return memoryCost;
	}

	public void setMemoryCost(int memoryCost) {
		this.memoryCost = memoryCost;
	}

	public int getParallelism() {
		return parallelism;
	}

	public void setParallelism(int parallelism) {
		this.parallelism = parallelism;
	}
	
	
	
	
}
