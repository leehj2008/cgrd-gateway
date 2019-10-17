package com.app.movescu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.app.dao.Task;
import com.app.util.ApplicationContextProvider;
import com.app.util.FTLString;


@Component("MoveService")
@Scope("prototype")
public class MoveService {

	Logger log = LoggerFactory.getLogger(MoveService.class);
	@Value("${movescu.LocalAE}")
	String localAE;
	@Value("${movescu.LocalPort}")
	String localPort;
	//@Value("${movescu.RemoteAE}")
	String remoteAE;
	//@Value("${movescu.RemoteHost}")
	String remoteHost;
	//@Value("${movescu.RemotePort}")
	String remotePort;
	@Value("${movescu.Dest}")
	String dest;
	@Value("${movescu.parameters}")
	String parameters;
	@Autowired
	FTLString ftlString;
	@Autowired
	ThreadAE threadAE;
	
	DcmQR dcmqr;
	Properties config;
	public MoveService(){
		dcmqr = ApplicationContextProvider.getBean("DCMQR", DcmQR.class);
		ClassPathResource resource = new ClassPathResource("movescu.properties");
		try {
			config = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	public void moveByAcc(long ckey,String accno) throws Exception{
		String args[] = {"-L",localAE,remoteAE+"@"+remoteHost+":"+remotePort,"-cmove",dest,"-qAccessionNumber="+accno};
		dcmqr.curr_acc=accno;
		dcmqr.curr_ckey=ckey;
		dcmqr.move(args);
		dcmqr.curr_acc="";
		dcmqr.curr_ckey=0;
	}
	
	public void moveByExpression(Task task) throws Exception{
		
		log.info("moveByExpression task.ckey={}",task.getCkey());
		Map data = new HashMap<String,Object>();
		data.put("task", task);
		parameters = parameters.replaceAll("[@]", "\\$");
		log.info("parameter:{},patientid={},modality={} ,data={}",parameters,task.getPatientid(),task.getModality(),data.toString());
		String expression = ftlString.compileString(parameters, data);
		log.info("expression={}",expression);
		String [] queryArgs = expression.split("[|]");
		List<String> allArgs = new ArrayList<String>();
		
		//load remote config by LOC
		remoteAE=config.getProperty("movescu."+task.getLOC()+".RemoteAE");
		remoteHost=config.getProperty("movescu."+task.getLOC()+".RemoteHost");
		remotePort=config.getProperty("movescu."+task.getLOC()+".RemotePort");
		String tname = Thread.currentThread().getName();
		String destAE=threadAE.getAEByThread(tname);
		String args[] = {"-L",localAE,remoteAE+"@"+remoteHost+":"+remotePort,"-cmove",destAE};
		for(String arg:args){
			allArgs.add(arg);
		}
		for(String arg:queryArgs){
			allArgs.add(arg);
		}
		String [] a = {};
		
		dcmqr.curr_ckey=task.getCkey();
		dcmqr.move(allArgs.toArray(a));
		dcmqr.curr_ckey=0;
	}
	
	public void moveBySuid(String suid) throws Exception{
		String args[] = {"-L",localAE,remoteAE+"@"+remoteHost+":"+remotePort,"-cmove",dest,"-qStudyInstanceUID="+suid};
		dcmqr.curr_suid=suid;
		dcmqr.move(args);
		dcmqr.curr_suid="";
	}
	
	public void moveBySOPUid(String sopuid) throws Exception{
		String args[] = {"-L",localAE,remoteAE+"@"+remoteHost+":"+remotePort,"-cmove",dest,"-I -qSOPInstanceUID="+sopuid};
		dcmqr.curr_sopuid=sopuid;
		dcmqr.move(args);
		dcmqr.curr_sopuid="";
	}
}
