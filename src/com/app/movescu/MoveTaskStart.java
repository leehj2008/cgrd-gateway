package com.app.movescu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=1)
public class MoveTaskStart implements CommandLineRunner {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	MoveThread moveThread ;
	@Value("${startMove}")
	String startMove;
	public void run(String... arg0) throws Exception {
		if(startMove!=null&&startMove.equalsIgnoreCase("false")){
			log.info("do not need start move.");
			return;
		}
		log.info("this is movescu starter.........");
//		Thread moveThread = new Thread(moveTaskManager);
//		moveThread.start();
		moveThread.execute();
		
	}

}
