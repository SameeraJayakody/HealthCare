package model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@XmlRootElement
public class search {

	int id;
	private int sid;
	private String hospitalID;
	private String hospitalName;
	private String docid;
	private String docname;
	private String speciality;
	private String date;
	private String startTime;
	private String endTime;
	private int roomNo;
	private String status;

	/*
	 * public search(int i, int j, String string, String string2, String string3,
	 * String string4, String string5, String string6, String string7, String
	 * string8, String string9, String string10) {}
	 */

	public search() {}

	public search(int id, int sid, String hospitalID, String hospitalName, String docid, String docname,
			String speciality, String date, String startTime, String endTime, int roomNo, String status) {

		this.id = id;
		this.sid = sid;
		this.hospitalID = hospitalID;
		this.hospitalName = hospitalName;
		this.docid = docid;
		this.docname = docname;
		this.speciality = speciality;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.roomNo = roomNo;
		this.status = status;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getHospitalID() {
		return hospitalID;
	}

	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	  @Override 
	  public String toString(){
	  
	  return getStatus();
		/*
		 * return
		 * "sid["+sid+",did"+id+",dname"+hospitalName+",hid"+docid+",dname"+docname+
		 * ",speciality"+speciality+",date"+date+",stime"+startTime+",endtime"+endTime+
		 * ",room"+roomNo+",status"+status+"]";
		 */
	  
	  }
}  
	  
	