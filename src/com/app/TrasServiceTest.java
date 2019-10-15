package com.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.TaskDao;
import com.app.oracle.OracleTestDao;

@Service
public class TrasServiceTest {
	
	@Autowired
	TaskDao taskDao;
	@Autowired
	OracleTestDao oracleDao;

	Logger log = LoggerFactory.getLogger(this.getClass());
	@Transactional(transactionManager="oracleTransactionManager",rollbackFor = Exception.class)
	public void runService() throws Exception{
		taskDao.getOneTask();
		List l = oracleDao.getA();
		oracleDao.insert("事务2");
			//throw new Exception("Test Exception occured");
	}
	
	public void startServer(){
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true){
					try {
						runService();
					} catch (Exception e) {
						e.printStackTrace();
					}
					log.info("TestService...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		t.start();
	}


}
