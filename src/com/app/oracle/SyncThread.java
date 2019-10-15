package com.app.oracle;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SyncThread {

	Logger log = LoggerFactory.getLogger(this.getClass());
	@Value("${syncSleep}")
	private String syncSleep;
	@Autowired
	SyncService syncService;
	public void execute() {
		new Worker().start();
	}

	private class Worker extends Thread {
		

		@Override
		public void run() {
			while (true) {
				try {
					SyncThread.this.syncService.syncData();
				} catch (Exception e1) {
					log.info("SYNC Exception.");
					log.error(e1.getMessage());
				}
				try {
					Thread.sleep(Integer.valueOf(syncSleep));
				} catch (NumberFormatException e) {
					log.error(e.getMessage());
				} catch (InterruptedException e) {
					log.error(e.getMessage());
				}
			}
		}
	}

}
