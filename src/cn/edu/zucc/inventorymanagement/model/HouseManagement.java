package cn.edu.zucc.inventorymanagement.model;

import java.util.Date;

	/**
	*	*@author yangsj
	*/

	public class HouseManagement{
	private int houseId;
	private int workerId;
	private int houseManageId;
	private Date houseManageTime;
	private String houseManageNote;

	public int getHouseId(){
		return this.houseId;
	}
	public void setHouseId(int houseId){
		this.houseId=houseId;
	}
	public int getWorkerId(){
		return this.workerId;
	}
	public void setWorkerId(int workerId){
		this.workerId=workerId;
	}
	public int getHouseManageId(){
		return this.houseManageId;
	}
	public void setHouseManageId(int houseManageId){
		this.houseManageId=houseManageId;
	}
	public Date getHouseManageTime(){
		return this.houseManageTime;
	}
	public void setHouseManageTime(Date houseManageTime){
		this.houseManageTime=houseManageTime;
	}
	public String getHouseManageNote(){
		return this.houseManageNote;
	}
	public void setHouseManageNote(String houseManageNote){
		this.houseManageNote=houseManageNote;
	}

}