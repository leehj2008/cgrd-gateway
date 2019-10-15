package com.app.storescp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.app.storescp.dcmrcv.StoreSCPService2;
@Component
@Order(value=2)
public class StoreScpStart implements CommandLineRunner {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	StoreSCPService2 storeScpService ;
	@Value("${startStoreSCP}")
	String startStoreSCP;
	@Override
	public void run(String... arg0) throws Exception {
		if(startStoreSCP!=null&&startStoreSCP.equalsIgnoreCase("false")){
			log.info("do not need start storescp..");
			return;
		}
		log.info("this is scp starter.........");
		storeScpService.startScp();
	}

}
