package cn.edu.zucc.inventorymanagement.model;

import java.sql.Date;

	/**
	*	*@author yangsj
	*/

	public class GoodsManagement{
	private int goodsId;
	private int workerId;
	private int goodsManageId;
	private Date goodsManageTime;
	private String goodsManageNote;

	public int getGoodsId(){
		return this.goodsId;
	}
	public void setGoodsId(int goodsId){
		this.goodsId=goodsId;
	}
	public int getWorkerId(){
		return this.workerId;
	}
	public void setWorkerId(int workerId){
		this.workerId=workerId;
	}
	public int getGoodsManageId(){
		return this.goodsManageId;
	}
	public void setGoodsManageId(int goodsManageId){
		this.goodsManageId=goodsManageId;
	}
	public Date getGoodsManageTime(){
		return this.goodsManageTime;
	}
	public void setGoodsManageTime(Date goodsManageTime){
		this.goodsManageTime=goodsManageTime;
	}
	public String getGoodsManageNote(){
		return this.goodsManageNote;
	}
	public void setGoodsManageNote(String goodsManageNote){
		this.goodsManageNote=goodsManageNote;
	}

}