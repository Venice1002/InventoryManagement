package cn.edu.zucc.inventorymanagement.model;

import java.sql.Date;

	/**
	*	*@author yangsj
	*/

	public class Enter{
	private int enterId;
	private int houseId;
	private int goodsId;
	private int supplierId;
	private String enterAmount;
	private Date enterTime;
	private String enterPrice;
	private int batchId;
	private String enterNote;
	private int workerId;

	public int getEnterId(){
		return this.enterId;
	}
	public void setEnterId(int enterId){
		this.enterId=enterId;
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
	public int getSupplierId(){
		return this.supplierId;
	}
	public void setSupplierId(int supplierId){
		this.supplierId=supplierId;
	}
	public String getEnterAmount(){
		return this.enterAmount;
	}
	public void setEnterAmount(String enterAmount){
		this.enterAmount=enterAmount;
	}
	public Date getEnterTime(){
		return this.enterTime;
	}
	public void setEnterTime(Date enterTime){
		this.enterTime=enterTime;
	}
	public String getEnterPrice(){
		return this.enterPrice;
	}
	public void setEnterPrice(String enterPrice){
		this.enterPrice=enterPrice;
	}
	public int getBatchId(){
		return this.batchId;
	}
	public void setBatchId(int batchId){
		this.batchId=batchId;
	}
	public String getEnterNote(){
		return this.enterNote;
	}
	public void setEnterNote(String enterNote){
		this.enterNote=enterNote;
	}
	public int getWorkerId(){
		return this.workerId;
	}
	public void setWorkerId(int workerId){
		this.workerId=workerId;
	}

}