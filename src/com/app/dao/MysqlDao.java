package com.app.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface MysqlDao {
	void syncData(@Param("data") Map<String,Object>data);
	
}
