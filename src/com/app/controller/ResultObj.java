package com.app.controller;

public class ResultObj {
	
	private String PatientID;
	private String ExamID;
	private String AccessionNumber;
	private String FLAG;
	private String ErrorMsg;
	
	public ResultObj(String PatientID,String ExamID,String AccessionNumber,String FLAG,String ErrorMsg){
		this.PatientID=PatientID;
		this.ExamID=ExamID;
		this.AccessionNumber=AccessionNumber;
		this.FLAG=FLAG;
		this.ErrorMsg=ErrorMsg;
	}
	
	public ResultObj(String FLAG,String ErrorMsg){
		this.FLAG=FLAG;
		this.ErrorMsg=ErrorMsg;
	}
	
	
	public String getPatientID() {
		return PatientID;
	}
	public void setPatientID(String patientID) {
		PatientID = patientID;
	}
	public String getExamID() {
		return ExamID;
	}
	public void setExamID(String examID) {
		ExamID = examID;
	}
	public String getAccessionNumber() {
		return AccessionNumber;
	}
	public void setAccessionNumber(String accessionNumber) {
		AccessionNumber = accessionNumber;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}


}
