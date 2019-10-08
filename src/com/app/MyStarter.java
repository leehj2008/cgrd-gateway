package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.dao.Task;
import com.app.dao.TaskDao;
import com.ge.med.ServException;
import com.ge.med.catcorb.privilege.UserInfoOB;
import com.ge.med.catcorb.privilege.user.UserMgmtOperations;
import com.ge.med.dragon.rispacs.service.CXFClientProvider;
import com.ge.med.dragon.rispacs.service.ServiceFactory;
import com.ge.med.dragon.rispacs.service.ServiceNameConstants;

@Component
public class MyStarter implements CommandLineRunner{
	Logger log  =  LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyBean mybean;
	
	@Autowired
	TaskDao dao;
	
	@Override
	public void run(String... arg0) throws Exception {
		String r = mybean.initRISAPI();
		log.info("RISAPI Test Result: "+r);
		
		String task = dao.getOneTask();
		
		log.info("DB Test Result: "+task);
	}

	
	
}
