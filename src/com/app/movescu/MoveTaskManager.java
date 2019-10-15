package com.app.movescu;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.app.dao.Task;
import com.app.dao.TaskDao;
import com.app.dao.TaskStatus;


@Service
public class MoveTaskManager implements Runnable{
	Logger log = LoggerFactory.getLogger(MoveTaskManager.class);
	@Autowired
	TaskDao taskdao;
	@Autowired
	MoveService moveService;
	//MoveClient moveClient;
	@Override
	public void run() {
		//moveClient.configure();
		while(true){
			Task task = taskdao.getMoveTask(TaskStatus.INIT);
				log.info("task="+task.getCkey());
				log.info("Moving accno={},suid={}",task.getAccessionNumber(),task.getPatientid());
				taskdao.updateTaskStartMove( task.getCkey());
				try {
					moveService.moveByExpression(task);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage());
					taskdao.updateTask(TaskStatus.MOVE_FAILED, task.getCkey());
					continue;
				}
				/*Thread t = new Thread(moveClient);
				t.start();*/
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
