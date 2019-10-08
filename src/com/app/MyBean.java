package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


public class MyBean {

	Logger log  =  LoggerFactory.getLogger(this.getClass());
	String msg;
	
	@Value("${ris_ws_url}")
	String ris_ws_url;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String sayHello(){
		System.out.println(msg);
		return "RTN:"+msg;
	}
	
	public String initRISAPI(){
		return "";
	}
}
