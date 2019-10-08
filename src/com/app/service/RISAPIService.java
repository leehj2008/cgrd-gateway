package com.app.service;

import java.util.List;
import java.util.Map;

public interface RISAPIService {

	
	public RISRetData newOrderSchedule(String orderAEXml) ;
	public List searchOrder(Map searchMap ) ;
	public int saveOrUpdateOrder(Map orderMap);
	public void cancelOrder(Map map);
	public void arriveOrder(String examid);
	public void cancelSchedule(String examid);
	

}
