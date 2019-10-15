package com.app.oracle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.app.TrasServiceTest;
import com.app.storescp.dcmrcv.StoreSCPService2;
@Component
@Order(value=3)
public class SyncDataStart implements CommandLineRunner {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Value("${startSync}")
	String startSync;
	@Autowired
	SyncThread syncThread;
	

	@Autowired
	TrasServiceTest testService;
	
	@Override
	public void run(String... arg0) throws Exception {
		if(startSync!=null&&startSync.equalsIgnoreCase("false")){
			log.info("do not need start Sync..");
			return;
		}
		log.info("this is Sync starter.........");
//		Thread t = new Thread(testService);
		syncThread.execute();
//		t.start();
	}

}
