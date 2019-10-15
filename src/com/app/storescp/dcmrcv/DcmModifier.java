package com.app.storescp.dcmrcv;


import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.data.VR;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DcmModifier {

	@Value("${storescp.patidPrefix}")
	String patidPrefix;
	@Value("${storescp.accnumPrefix}")
	String accnumPrefix;
	
	public DicomObject modifyDicom(DicomObject sourceObj){
		String sourcePaientID=sourceObj.getString(Tag.PatientID);
		String sourceAccessionNumber = sourceObj.getString(Tag.AccessionNumber);
		String newPatid=patidPrefix+sourcePaientID;
		String newAccnum = accnumPrefix+sourceAccessionNumber;
		sourceObj.putString(Tag.PatientID, VR.LO,newPatid);
		sourceObj.putString(Tag.AccessionNumber,VR.SH, newAccnum);
		return sourceObj;
	}

}
