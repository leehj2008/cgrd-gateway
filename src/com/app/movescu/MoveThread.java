package com.app.movescu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.util.ApplicationContextProvider;

@Component
public class MoveThread {

	Logger log = LoggerFactory.getLogger(this.getClass());
	@Value("${moveSleep}")
	private String moveSleep;
	@Value("${moveThreadCount}")
	private String moveThreadCount;
	public void execute() {
		int tcnt=Integer.parseInt(moveThreadCount);
		List<Worker> workers = new ArrayList<MoveThread.Worker>();
		for(int i=1;i<=tcnt;i++){
			Worker w = new Worker(i+"");
			workers.add(w);
			w.start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("{} move threads started successfully.",workers.size());
		
	}

	private class Worker extends Thread {
		MoveTaskService moveTaskService;
		public Worker(String threadName){
			super(threadName);
			setName(threadName);
			moveTaskService= ApplicationContextProvider.getBean("MoveTaskService", MoveTaskService.class);
		}
		
		@Override
		public void run() {
			
			while (true) {
				try {
					moveTaskService.processTask();
				} catch (Exception e1) {
					log.info("task process Exception.");
					log.error(e1.getMessage());
				}
				try {
					Thread.sleep(Integer.valueOf(moveSleep));
				} catch (NumberFormatException e) {
					log.error(e.getMessage());
				} catch (InterruptedException e) {
					log.error(e.getMessage());
				}
			}
		}
	}

}
