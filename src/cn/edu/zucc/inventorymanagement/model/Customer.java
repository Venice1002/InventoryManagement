package cn.edu.zucc.inventorymanagement.model;

	/**
	*	*@author yangsj
	*/

	public class Customer{
	private int customerId;
	private String customerName;
	private String customerAddress;
	private String customerPhone;

	public int getCustomerId(){
		return this.customerId;
	}
	public void setCustomerId(int customerId){
		this.customerId=customerId;
	}
	public String getCustomerName(){
		return this.customerName;
	}
	public void setCustomerName(String customerName){
		this.customerName=customerName;
	}
	public String getCustomerAddress(){
		return this.customerAddress;
	}
	public void setCustomerAddress(String customerAddress){
		this.customerAddress=customerAddress;
	}
	public String getCustomerPhone(){
		return this.customerPhone;
	}
	public void setCustomerPhone(String customerPhone){
		this.customerPhone=customerPhone;
	}

}