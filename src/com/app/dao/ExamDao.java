package com.app.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dcm4che2.data.DicomObject;


@Mapper
public interface ExamDao {
	List<Map<String,String>> getExamInfo(@Param("objmap")Map<String,String>  objmap);
	List<Map<String,String>> getStudies(@Param("objmap")Map<String,String>  objmap);
	Map<String,String> getStudy(@Param("ckey")long ckey);
	void insertStudy(@Param("objmap")Map<String,String>  objmap);
	List<Map<String,String>>  getImages(@Param("objmap")Map<String,String>  objmap);
	void insertImage(@Param("objmap")Map<String,String> objmap);
	
	void updateTaskStatus(@Param("ckey")long ckey,@Param("status")String status);
	void updateStudy(@Param("ckey")long ckey,@Param("status")String status,@Param("examId")String examId,@Param("orderId")String orderId,@Param("patientIntraId")String patientIntraId);
	
}
