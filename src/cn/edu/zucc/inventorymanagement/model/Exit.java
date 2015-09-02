package cn.edu.zucc.inventorymanagement.model;

import java.util.Date;

	/**
	*	*@author yangsj
	*/

	public class Exit{
	private int exitId;
	private int houseId;
	private int goodsId;
	private int batchId;
	private int customerId;
	private float exitAmount;
	private Date exitTime;
	private float exitPrice;
	private String exitNote;
	private int workerId;

	public int getExitId(){
		return this.exitId;
	}
	public void setExitId(int exitId){
		this.exitId=exitId;
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
	public int getCustomerId(){
		return this.customerId;
	}
	public void setCustomerId(int customerId){
		this.customerId=customerId;
	}
	public float getExitAmount(){
		return this.exitAmount;
	}
	public void setExitAmount(float exitAmount){
		this.exitAmount=exitAmount;
	}
	public Date getExitTime(){
		return this.exitTime;
	}
	public void setExitTime(Date exitTime){
		this.exitTime=exitTime;
	}
	public float getExitPrice(){
		return this.exitPrice;
	}
	public void setExitPrice(float exitPrice){
		this.exitPrice=exitPrice;
	}
	public String getExitNote(){
		return this.exitNote;
	}
	public void setExitNote(String exitNote){
		this.exitNote=exitNote;
	}
	public int getWorkerId(){
		return this.workerId;
	}
	public void setWorkerId(int workerId){
		this.workerId=workerId;
	}

}