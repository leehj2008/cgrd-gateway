package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.ge.med.ServException;
import com.ge.med.catcorb.privilege.UserInfoOB;
import com.ge.med.catcorb.privilege.user.UserMgmtOperations;
import com.ge.med.dragon.rispacs.service.CXFClientProvider;
import com.ge.med.dragon.rispacs.service.ServiceFactory;
import com.ge.med.dragon.rispacs.service.ServiceNameConstants;

public class MyBean {

	Logger log  =  LoggerFactory.getLogger(this.getClass());
	String msg;
	
	@Value("${ris_ws_url}")
	String ris_ws_url;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String sayHello(){
		System.out.println(msg);
		return "RTN:"+msg;
	}
	
	public String initRISAPI(){
		System.setProperty("rispacs.model", "webservice");
		CXFClientProvider.setPublishBaseURL(ris_ws_url);
		UserMgmtOperations userMgmt = (UserMgmtOperations) ServiceFactory.getService(ServiceNameConstants.USERMGMT);
		String result = "";
		UserInfoOB u = null;
		try {
			u = userMgmt.getUserInfoByUserLoginName("itpschina");
		} catch (ServException e) {
			e.printStackTrace();
			return null;
		}
		result = u.getUserName();
		log.info("RIS API Test Result:{}",result);
		return result;
	}
}
