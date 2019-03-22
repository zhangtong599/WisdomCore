package com.javacore.wisdom.conditional;

import org.apache.log4j.PropertyConfigurator;

import com.javacore.wisdom.WisdomApplication;

public class WinDoService implements DoService {

	@Override
	public void InitLog4jConfig() {
		try{
			String log4Path=WisdomApplication.class.getClassLoader().getResource("").getPath()+
					"log4j-win.properties";
			System.out.println("initialize win log4j...");
			PropertyConfigurator.configure(log4Path);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
