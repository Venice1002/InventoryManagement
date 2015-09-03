package cn.edu.zucc.inventorymanagement.model;

import java.util.Date;

	/**
	*	*@author yangsj
	*/

	public class Exchange{
	private int exchangeId;
	private int lastHouseId;
	private int nextHouseId;
	private int goodsId;
	private int batchId;
	private float exchangeAmount;
	private Date exchangeTime;
	private String exchangeNote;
	private int workerId;

	public int getExchangeId(){
		return this.exchangeId;
	}
	public void setExchangeId(int exchangeId){
		this.exchangeId=exchangeId;
	}
	public int getLastHouseId(){
		return this.lastHouseId;
	}
	public void setLastHouseId(int lastHouseId){
		this.lastHouseId=lastHouseId;
	}
	public int getNextHouseId(){
		return this.nextHouseId;
	}
	public void setNextHouseId(int nextHouseId){
		this.nextHouseId=nextHouseId;
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
	public float getExchangeAmount(){
		return this.exchangeAmount;
	}
	public void setExchangeAmount(float exchangeAmount){
		this.exchangeAmount=exchangeAmount;
	}
	public Date getExchangeTime(){
		return this.exchangeTime;
	}
	public void setExchangeTime(Date exchangeTime){
		this.exchangeTime=exchangeTime;
	}
	public String getExchangeNote(){
		return this.exchangeNote;
	}
	public void setExchangeNote(String exchangeNote){
		this.exchangeNote=exchangeNote;
	}
	public int getWorkerId(){
		return this.workerId;
	}
	public void setWorkerId(int workerId){
		this.workerId=workerId;
	}

}