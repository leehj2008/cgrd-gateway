package com.app;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.Task;
import com.app.dao.TaskDao;
import com.app.oracle.OracleTestDao;

@Component
@Order(value=3)
public class MyStarter implements CommandLineRunner{
	Logger log  =  LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyBean mybean;
	
	@Autowired
	TaskDao dao;
	
	@Autowired
	TrasServiceTest testService;
	
	
	@Override
	public void run(String... arg0) {
		String r = mybean.initRISAPI();
		log.info("RISAPI Test Result: "+r);
		
		String task = dao.getOneTask();
		
		log.info("MySQL DB Test Result: "+task);
		try {
			testService.runService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
