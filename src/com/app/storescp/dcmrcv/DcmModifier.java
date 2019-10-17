package com.app.storescp.dcmrcv;


import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.data.VR;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.app.dao.Task;


@Component("dcmModifier")
@Scope("prototype")
public class DcmModifier {

	//for cgrd anonymization
	
	public DicomObject modifyDicom(DicomObject sourceObj,Task task){
		sourceObj.putString(Tag.PatientID, VR.LO,task.getHASHED_IDCODE());
		sourceObj.putString(Tag.AccessionNumber,VR.SH, "");
		sourceObj.putString(Tag.PatientName,VR.SH, "");
		return sourceObj;
	}

}
