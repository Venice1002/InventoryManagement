package cn.edu.zucc.inventorymanagement.model;

import java.util.Date;

	/**
	*	*@author yangsj
	*/

	public class Destory{
	private int destoryId;
	private int houseId;
	private int goodsId;
	private int batchId;
	private Date destoryTime;
	private float destoryAmount;
	private String destoryNote;
	private int workerId;

	public int getDestoryId(){
		return this.destoryId;
	}
	public void setDestoryId(int destoryId){
		this.destoryId=destoryId;
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
	public Date getDestoryTime(){
		return this.destoryTime;
	}
	public void setDestoryTime(Date destoryTime){
		this.destoryTime=destoryTime;
	}
	public float getDestoryAmount(){
		return this.destoryAmount;
	}
	public void setDestoryAmount(float destoryAmount){
		this.destoryAmount=destoryAmount;
	}
	public String getDestoryNote(){
		return this.destoryNote;
	}
	public void setDestoryNote(String destoryNote){
		this.destoryNote=destoryNote;
	}
	public int getWorkerId(){
		return this.workerId;
	}
	public void setWorkerId(int workerId){
		this.workerId=workerId;
	}

}