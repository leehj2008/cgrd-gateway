package com.app.oracle;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OracleTestDao {
	List<Map> getA();
	void insert(@Param("text")String text);
}
