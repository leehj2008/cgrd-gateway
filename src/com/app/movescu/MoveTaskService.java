package com.app.movescu;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.Task;
import com.app.dao.TaskDao;
import com.app.dao.TaskStatus;

@Service
public class MoveTaskService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TaskDao taskdao;

	@Autowired
	MoveService moveService;
	
	@Autowired
	MoveTxService moveTxService;

	public void processTask() throws Exception {
		log.debug("processTask..");
		
		Task task  = moveTxService.getOneTaskAndLock();
		if(task==null)
			return;
		TaskRepo.repo.put(task.getAccessionNumber(),task);
		try {
			moveService.moveByExpression(task);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			taskdao.updateTask(TaskStatus.MOVE_FAILED, task.getCkey());
		}

		TaskRepo.repo.remove(task.getAccessionNumber());
		log.info("Task processed.");
	}

}
