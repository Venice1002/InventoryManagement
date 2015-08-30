package cn.edu.zucc.inventorymanagement.model;

import java.sql.Date;

	/**
	*	*@author yangsj
	*/

	public class Goods{
	private int goodsId;
	private String goodsName;
	private String goodsType;
	private Date produceDate;
	private Date limitedDate;
	private String goodsNote;
	private String unit;
	private String manufacturer;
	private int goodsLevel;
	private String goodsVolume;

	public int getGoodsId(){
		return this.goodsId;
	}
	public void setGoodsId(int goodsId){
		this.goodsId=goodsId;
	}
	public String getGoodsName(){
		return this.goodsName;
	}
	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
	}
	public String getGoodsType(){
		return this.goodsType;
	}
	public void setGoodsType(String goodsType){
		this.goodsType=goodsType;
	}
	public Date getProduceDate(){
		return this.produceDate;
	}
	public void setProduceDate(Date produceDate){
		this.produceDate=produceDate;
	}
	public Date getLimitedDate(){
		return this.limitedDate;
	}
	public void setLimitedDate(Date limitedDate){
		this.limitedDate=limitedDate;
	}
	public String getGoodsNote(){
		return this.goodsNote;
	}
	public void setGoodsNote(String goodsNote){
		this.goodsNote=goodsNote;
	}
	public String getUnit(){
		return this.unit;
	}
	public void setUnit(String unit){
		this.unit=unit;
	}
	public String getManufacturer(){
		return this.manufacturer;
	}
	public void setManufacturer(String manufacturer){
		this.manufacturer=manufacturer;
	}
	public int getGoodsLevel(){
		return this.goodsLevel;
	}
	public void setGoodsLevel(int goodsLevel){
		this.goodsLevel=goodsLevel;
	}
	public String getGoodsVolume(){
		return this.goodsVolume;
	}
	public void setGoodsVolume(String goodsVolume){
		this.goodsVolume=goodsVolume;
	}

}