package com.app.movescu;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.app.dao.Task;
import com.app.dao.TaskDao;
import com.app.dao.TaskStatus;
import com.app.util.ApplicationContextProvider;

@Component("MoveTaskService")
@Scope("prototype")
public class MoveTaskService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TaskDao taskdao;

	MoveService moveService;
	
	@Autowired
	MoveTxService moveTxService;
	@Autowired
	ThreadAE threadAE;


	public MoveTaskService(){

		moveService = ApplicationContextProvider.getBean("MoveService", MoveService.class);
	}
	
	public void processTask() throws Exception {
		log.debug("processTask..");
		
		Task task  = moveTxService.getOneTaskAndLock();
		if(task==null)
			return;
		
		String tname = Thread.currentThread().getName();
		String destAE=threadAE.getAEByThread(tname);
		TaskRepo.repo.put(destAE+task.getAccessionNumber(),task);
		try {
			moveService.moveByExpression(task);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			taskdao.updateTask(TaskStatus.MOVE_FAILED, task.getCkey());
		}

		TaskRepo.repo.remove(destAE+task.getAccessionNumber());
		log.info("Task processed.");
	}

}
