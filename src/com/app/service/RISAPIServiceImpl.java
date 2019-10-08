package com.app.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.dao.RISDao;
import com.app.util.SearcherUtils;
import com.ge.med.AuditCommon;
import com.ge.med.ServException;
import com.ge.med.catcorb.modality.ModalityMgmtOperations;
import com.ge.med.catcorb.privilege.user.UserMgmtOperations;
import com.ge.med.catcorb.procedure.ProcedureMgmtOperations;
import com.ge.med.catcorb.procedurestep.ProcedureStepMgmtOperations;
import com.ge.med.dragon.rispacs.search.SearchEngineerOperations;
import com.ge.med.dragon.rispacs.service.ServiceFactory;
import com.ge.med.dragon.rispacs.service.ServiceNameConstants;
import com.ge.med.report.reportprocessOperations;
import com.ge.med.rispacs.registerserver.DataInfo;
import com.ge.med.rispacs.registerserver.ExamProcessOperations;
import com.ge.med.rispacs.registerserver.OrderProcessOperations;
import com.ge.med.rispacs.registerserver.RegistInfoOperations;
@Service
public class RISAPIServiceImpl implements RISAPIService {

	Logger log  =  LoggerFactory.getLogger(this.getClass());
	UserMgmtOperations userMgmt;
	RegistInfoOperations registInfo;
	OrderProcessOperations orderProcess;
	ProcedureStepMgmtOperations procedureStepOperations;
	ExamProcessOperations examProcessOperations;
	ModalityMgmtOperations modalityMgmtOperations;
	ProcedureMgmtOperations procedureMgmtOperations;
	SearchEngineerOperations searchEngineer;
	@Autowired
	RISDao risdao;
	
	AuditCommon auditCommon;

	@Value("${audit.userID}")
	String userID;
	@Value("${audit.username}")
	String username;
	@Value("${audit.hostname}")
	String hostname;
	@Value("${audit.IP}")
	String IP;
	@Value("${audit.appNameId}")
	String appNameId;
	

	private void initAudit(){
		if(auditCommon!=null)return;
		
		auditCommon = new AuditCommon();

		auditCommon.userID = Integer.parseInt(userID);
		auditCommon.username = username;
		auditCommon.hostname = hostname;
		auditCommon.IP = IP;
		auditCommon.appNameId =  Integer.parseInt(appNameId);
		
		userMgmt = (UserMgmtOperations) ServiceFactory.getService(ServiceNameConstants.USERMGMT);
		registInfo = (RegistInfoOperations) ServiceFactory.getService(ServiceNameConstants.REGISTINFO);
		orderProcess = (OrderProcessOperations) ServiceFactory.getService(ServiceNameConstants.ORDERPROCESS);
		procedureStepOperations = (ProcedureStepMgmtOperations) ServiceFactory
				.getService(ServiceNameConstants.PROCEDURESTEPMGMT);
		examProcessOperations = (ExamProcessOperations) ServiceFactory.getService(ServiceNameConstants.EXAMPROCESS);
		modalityMgmtOperations = (ModalityMgmtOperations) ServiceFactory.getService(ServiceNameConstants.MODALITYMGMT);
		procedureMgmtOperations = (ProcedureMgmtOperations) ServiceFactory
				.getService(ServiceNameConstants.PROCEDUREMGMT);
	}
	

	@Override
	public int saveOrUpdateOrder(Map orderMap){
		List<Map> existOrders = risdao.searchOrder(orderMap);
		log.info("search Exist orders:{}",existOrders.size());
		int r=0;
		if(existOrders.size()>0){
			log.info("updating orders:{}",orderMap);
			risdao.updateOrder(orderMap);
			log.info("updated");
		}else{
			log.info("inserting orders:{}",orderMap);
			risdao.saveOrder(orderMap);
			log.info("inserted");
		}
		
		return r;
	}

	@Override
	public RISRetData newOrderSchedule(String orderAEXml) {
		initAudit();
		
		RISRetData retData = new RISRetData();
		try {
			DataInfo returedData = registInfo.HISOrderSchedule(orderAEXml, 1, auditCommon);
			retData.dataInfo = returedData;
		} catch (ServException e) {
			e.printStackTrace();
			retData.errorMsg=e.getMessage();
		}
		return retData;
		
	}


	@Override
	public void cancelOrder(Map map) {
		risdao.invalidOrder(map);
	}


	@Override
	public void arriveOrder(String examid) {
		initAudit();
		examProcessOperations.arrival(examid, auditCommon);
	}


	@Override
	public void cancelSchedule(String examid) {
		initAudit();
		examProcessOperations.cancelSchedule(examid, auditCommon);
		
	}


	@Override
	public List searchOrder(Map searchMap) {
		return SearcherUtils.queryToMap(searchMap, new String[]{
				"p:WAIT,0128","o:WAIT,0266","e:WAIT,0267",
				"e:WAIT,0004","e:WAIT,0014","e:0008,0050"});
	}

}
