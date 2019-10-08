package com.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface RISDao {
	void saveOrder(Map data);
	void updateOrder(Map data);
	List<Map> searchOrder(Map data);
	void invalidOrder(Map map);
	List<Map> searchProcedure(Map map);
	
}
