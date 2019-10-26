package com.app.storescp.dcmrcv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.util.ApplicationContextProvider;
@Component
public class StoreSCPService2 {
	
	public static final Logger log = LoggerFactory.getLogger(StoreSCPService2.class);
	@Value("${storescp.StoreDir}")
	String storeDir;
	@Value("${storescp.StoreFile}")
	String storeFile;
	@Value("${storescp.LocalAE}")
	String localAE;
	@Value("${storescp.LocalPort}")
	String localPort;

	@Value("${moveThreadCount}")
	private String moveThreadCount;
	
	public void startScp() {
		int localPortInt=Integer.parseInt(localPort);
		int moveThreadCountInt=Integer.parseInt(moveThreadCount);
		for(int port=localPortInt;port<localPortInt+moveThreadCountInt;port++){
			DcmRcv dcmrcv= ApplicationContextProvider.getBean("DCMRCV", DcmRcv.class);
			log.info("Starting StoreSCP"+dcmrcv);
			String args [] = {localAE+port+":"+port,"-dest",storeDir,"-filepath",storeFile};
			log.info("Starting StoreSCP:"+args[0]+" "+args[1]);
			dcmrcv.startSCP(args);
		}
		
		
	}
}
