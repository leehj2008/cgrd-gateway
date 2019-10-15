package com.app.movescu;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.Task;
import com.app.dao.TaskDao;
import com.app.dao.TaskStatus;

@Service
public class MoveTxService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TaskDao taskdao;

	@Transactional(transactionManager = "mysqlTransactionManager", rollbackFor = Exception.class)
	public Task getOneTaskAndLock() throws Exception {
		log.debug("getOneTaskAndLock..");

		Task task= taskdao.getMoveTask(TaskStatus.INIT);
		if(task==null){
			log.debug("No task return.");
			return null;
		}
		log.info("Moving accno={},patid={},task={}", task.getAccessionNumber(), task.getPatientid(),task.getCkey());
		Thread.sleep(2000);
		
		taskdao.updateTaskStartMove(task.getCkey());

		log.info("getOneTaskAndLock return.");
		return task;
	}

}
