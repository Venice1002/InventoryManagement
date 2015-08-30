package cn.edu.zucc.inventorymanagement.model;

import java.sql.Date;

	/**
	*	*@author yangsj
	*/

	public class Return{
	private int returnId;
	private int customerId;
	private int houseId;
	private int goodsId;
	private int batchId;
	private String returnAmount;
	private Date returnTime;
	private String returnNote;
	private int workerId;

	public int getReturnId(){
		return this.returnId;
	}
	public void setReturnId(int returnId){
		this.returnId=returnId;
	}
	public int getCustomerId(){
		return this.customerId;
	}
	public void setCustomerId(int customerId){
		this.customerId=customerId;
	}
	public int getHouseId(){
		return this.houseId;
	}
	public void setHouseId(int houseId){
		this.houseId=houseId;
	}
	public int getGoodsId(){
		return this.goodsId;
	}
	public void setGoodsId(int goodsId){
		this.goodsId=goodsId;
	}
	public int getBatchId(){
		return this.batchId;
	}
	public void setBatchId(int batchId){
		this.batchId=batchId;
	}
	public String getReturnAmount(){
		return this.returnAmount;
	}
	public void setReturnAmount(String returnAmount){
		this.returnAmount=returnAmount;
	}
	public Date getReturnTime(){
		return this.returnTime;
	}
	public void setReturnTime(Date returnTime){
		this.returnTime=returnTime;
	}
	public String getReturnNote(){
		return this.returnNote;
	}
	public void setReturnNote(String returnNote){
		this.returnNote=returnNote;
	}
	public int getWorkerId(){
		return this.workerId;
	}
	public void setWorkerId(int workerId){
		this.workerId=workerId;
	}

}