package com.app.oracle;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OracleDao {
	List<Map> readRequest();
	void markRequest(@Param("PACSNO") String PACSNO);
}
