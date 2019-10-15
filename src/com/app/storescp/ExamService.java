package com.app.storescp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.SpecificCharacterSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ExamDao;

@Service
public class ExamService {
	public static final Logger log = LoggerFactory.getLogger(ExamService.class);
	@Autowired
	ExamDao examDao;
	public void saveOrUpdateExam(DicomObject dicomObj){
		Map<String,String> objmap = transferDicomObjToMap(dicomObj);
		List<Map<String,String>> studies = examDao.getStudies(objmap);
		if(studies==null||studies.isEmpty()){
			examDao.insertStudy(objmap);
		}
		List<Map<String,String>> images = examDao.getImages(objmap);
		if(images==null||images.isEmpty()){
			examDao.insertImage(objmap);
		}
		
	}
	
	public List<Map<String,String>> getExamInfo(DicomObject dicomObj){
		Map<String,String> objmap = transferDicomObjToMap(dicomObj);
		List<Map<String,String>> data = examDao.getExamInfo(objmap);
		return data;
	}
	
	private Map<String,String> transferDicomObjToMap(DicomObject dicomObj){
		Map<String,String> map = new HashMap<String, String>();
		Iterator itr = dicomObj.iterator();
		for (;itr.hasNext();){
			Object item = itr.next();
			if (item.getClass().toString().endsWith("SimpleDicomElement")) {
				DicomElement element = (DicomElement) item;
				
				map.put(Integer.toHexString(element.tag()).toUpperCase(),element.getValueAsString(SpecificCharacterSet.valueOf(new String[]{"ISO_IR 100"}), 100));
			}
		}

		log.info(map.toString());
		return map;
	}
}
