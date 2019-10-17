package com.app.movescu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ThreadAE {
	@Value("${storescp.LocalAE}")
	String storescpLocalAE;
	@Value("${storescp.LocalPort}")
	String storescpLocalPort;
	
	public String getAEByThread(String threadName){
		int tnameInt=Integer.parseInt(threadName);
		int storescpLocalPortInt = Integer.parseInt(storescpLocalPort);
		String dest = storescpLocalAE+(storescpLocalPortInt+tnameInt-1);
		return dest;
	}
}
