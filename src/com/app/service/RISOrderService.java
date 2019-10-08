package com.app.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.controller.ResultObj;
import com.app.dao.RISDao;
import com.app.util.FTL;
import com.app.util.SearcherUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RISOrderService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FTL ftl;
	
	@Autowired
	RISDao risdao;
	
	@Autowired
	RISAPIService risAPIService;
	
	ObjectMapper objmap = new ObjectMapper();
	public ResultObj createNewOrder(String orderData){
		ResultObj r = new ResultObj("0", "");
		//parse JSON and convert to MAP
		Map map = null;
		try {
			map = objmap.readValue(orderData, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("convert to Map:{}",map);
		if(map==null){
			r.setErrorMsg("error read json");
			r.setFLAG("-1");
			return r;
		}
		//save order to tempdb
		String flag= (String)map.get("FLAG");
		if(StringUtils.isEmpty(flag)){
			r.setErrorMsg("flag is empty.");
			r.setFLAG("-1");
			return r;
		}
		if(flag.equalsIgnoreCase("nw")){
			risAPIService.saveOrUpdateOrder(map);
		}else if(flag.equalsIgnoreCase("ca")){
			risAPIService.cancelOrder(map);
		}
		return r;
	}
	
	public ResultObj scheduleOrder(String  orderData){
		ResultObj r = new ResultObj("0", "");
		//parse JSON and convert to MAP
		Map map = null;
		try {
			map = objmap.readValue(orderData, Map.class);
		} catch (JsonParseException e) {
			r.setErrorMsg(e.getMessage());
			r.setFLAG("-1");
			e.printStackTrace();
			return r;
		} catch (JsonMappingException e) {
			r.setErrorMsg(e.getMessage());
			r.setFLAG("-1");
			e.printStackTrace();
			return r;
		} catch (IOException e) {
			r.setErrorMsg(e.getMessage());
			r.setFLAG("-1");
			e.printStackTrace();
			return r;
		}
		log.info("convert to Map:{}",map);
		if(map==null){
			r.setErrorMsg("error read json");
			r.setFLAG("-1");
			return r;
		}
		
		String flag = (String)map.get("FLAG");
		if(StringUtils.isEmpty(flag)){
			r.setErrorMsg("flag is empty.");
			r.setFLAG("-1");
			return r;
		}
		String applyCode = (String)map.get("HISEXAMNO");
		if(StringUtils.isEmpty(flag)){
			r.setErrorMsg("HISEXAMNO is empty.");
			r.setFLAG("-1");
			return r;
		}
		
		List<Map> orders = risdao.searchOrder(map);
		if(orders==null||orders.size()!=1){
			String msg= "found 0 order or more than 1 order according to hisorderid:"+applyCode;
			r.setErrorMsg(msg);
			r.setFLAG("-1");
			return r;
		}
		
		Map orderFromDB = orders.get(0);
		
		map.putAll(orderFromDB);
		log.info("merged Map:{}",map);
		
		//fill procedure and modality info
		List<Map> pstMapList = risdao.searchProcedure(map);
		if(pstMapList.size()!=1){
			String msg= "found 0 order or more than 1 procedure";
			r.setErrorMsg(msg);
			r.setFLAG("-1");
			return r;
		}
		
		Map pstMap = pstMapList.get(0);
		map.putAll(pstMap);
		log.info("filled procedure map:{}",map);
		
		//check order by HISExamID, if order exists,return false
		Map searchMap = new HashMap();
		searchMap.put("e:WAIT,0267", map.get("orderCode"));
		searchMap.put("e:WAIT,0014", map.get("0;1;2;3;5;6;7;8"));
		List risExamList = SearcherUtils.queryToMap(searchMap, new String[]{
				"p:WAIT,0128","o:WAIT,0266","e:WAIT,0267",
				"e:WAIT,0004","e:WAIT,0014","e:0008,0050"});
		if(risExamList.size()>0){
			r.setFLAG("-1");
			Map m =(Map)risExamList.get(0);
			r.setErrorMsg("This order already exists, patientID="+m.get("p:WAIT,0128")
				+",AccNum="+m.get("e:0008,0050")+",Status="+m.get("e:WAIT,0014"));
			return r;
		}
		
		//Compile template and map data to AEXML
		String orderAEXml = ftl.compileTemplateToString(map, "risorder.xml");
		log.info("order AEXAML=[{}]",orderAEXml);
		
		//invoke RIS  API
		RISRetData data = risAPIService.newOrderSchedule(orderAEXml);
		if(data.dataInfo!=null&&data.errorMsg==null){
			r.setAccessionNumber(data.dataInfo.accessionID);
			r.setPatientID(data.dataInfo.patientIntraID);
			r.setExamID(data.dataInfo.examID);
			return r;
		}else{
			r.setErrorMsg(data.errorMsg);
			r.setFLAG("-1");
			return r;
		}
	}
	
	
	public ResultObj arriveOrder(String  orderData){
		ResultObj r = new ResultObj("0", "");
		//parse JSON and convert to MAP
		Map map = null;
		try {
			map = objmap.readValue(orderData, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("convert to Map:{}",map);
		String flag = (String)map.get("FLAG");
		if(StringUtils.isEmpty(flag)){
			r.setErrorMsg("flag is empty.");
			r.setFLAG("-1");
			return r;
		}
		String examid = (String)map.get("ExamID");
		if(StringUtils.isEmpty(examid)){
			r.setErrorMsg("examid is empty.");
			r.setFLAG("-1");
			return r;
		}
		
		if(flag.equalsIgnoreCase("nw")){
			risAPIService.arriveOrder(examid);
		}else if(flag.equalsIgnoreCase("ca")){
			risAPIService.cancelSchedule(examid);
		}
		return r;
		
	}
}
