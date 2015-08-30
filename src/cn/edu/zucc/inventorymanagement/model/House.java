package cn.edu.zucc.inventorymanagement.model;

import java.sql.Date;

	/**
	*	*@author yangsj
	*/

	public class House{
	private int houseId;
	private String houseName;
	private String houseAddress;
	private String linkman;
	private int linkPhone;
	private String totalAmount;
	private String houseType;
	private String totalPrice;
	private String hosueState;
	private Date lastCheckDate;
	private String houseNote;
	private int houseLevel;

	public int getHouseId(){
		return this.houseId;
	}
	public void setHouseId(int houseId){
		this.houseId=houseId;
	}
	public String getHouseName(){
		return this.houseName;
	}
	public void setHouseName(String houseName){
		this.houseName=houseName;
	}
	public String getHouseAddress(){
		return this.houseAddress;
	}
	public void setHouseAddress(String houseAddress){
		this.houseAddress=houseAddress;
	}
	public String getLinkman(){
		return this.linkman;
	}
	public void setLinkman(String linkman){
		this.linkman=linkman;
	}
	public int getLinkPhone(){
		return this.linkPhone;
	}
	public void setLinkPhone(int linkPhone){
		this.linkPhone=linkPhone;
	}
	public String getTotalAmount(){
		return this.totalAmount;
	}
	public void setTotalAmount(String totalAmount){
		this.totalAmount=totalAmount;
	}
	public String getHouseType(){
		return this.houseType;
	}
	public void setHouseType(String houseType){
		this.houseType=houseType;
	}
	public String getTotalPrice(){
		return this.totalPrice;
	}
	public void setTotalPrice(String totalPrice){
		this.totalPrice=totalPrice;
	}
	public String getHosueState(){
		return this.hosueState;
	}
	public void setHosueState(String hosueState){
		this.hosueState=hosueState;
	}
	public Date getLastCheckDate(){
		return this.lastCheckDate;
	}
	public void setLastCheckDate(Date lastCheckDate){
		this.lastCheckDate=lastCheckDate;
	}
	public String getHouseNote(){
		return this.houseNote;
	}
	public void setHouseNote(String houseNote){
		this.houseNote=houseNote;
	}
	public int getHouseLevel(){
		return this.houseLevel;
	}
	public void setHouseLevel(int houseLevel){
		this.houseLevel=houseLevel;
	}

}