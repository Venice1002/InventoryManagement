package cn.edu.zucc.inventorymanagement.model;

	/**
	*	*@author yangsj
	*/

	public class Supplier{
	private int supplierId;
	private String supplierName;
	private String supplierAddress;
	private int supplierPhone;

	public int getSupplierId(){
		return this.supplierId;
	}
	public void setSupplierId(int supplierId){
		this.supplierId=supplierId;
	}
	public String getSupplierName(){
		return this.supplierName;
	}
	public void setSupplierName(String supplierName){
		this.supplierName=supplierName;
	}
	public String getSupplierAddress(){
		return this.supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress){
		this.supplierAddress=supplierAddress;
	}
	public int getSupplierPhone(){
		return this.supplierPhone;
	}
	public void setSupplierPhone(int supplierPhone){
		this.supplierPhone=supplierPhone;
	}

}