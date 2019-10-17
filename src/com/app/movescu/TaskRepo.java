package com.app.movescu;

import java.util.Hashtable;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.app.dao.Task;

public class TaskRepo {
	public static Hashtable<String, Task> repo = new Hashtable<String, Task>();
	

	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		for(Entry<String,Task> entry:repo.entrySet()){
			msg.append(entry.getKey()+"="+entry.getValue().getCkey());
		}
		return msg.toString();
	}
	
	

}
