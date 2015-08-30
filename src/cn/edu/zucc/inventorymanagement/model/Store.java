package cn.edu.zucc.inventorymanagement.model;

	/**
	*	*@author yangsj
	*/

	public class Store{
	private int storeId;
	private int houseId;
	private int goodsId;
	private String storeAmount;
	private int batchId;
	private String unit;
	private String storePrice;

	public int getStoreId(){
		return this.storeId;
	}
	public void setStoreId(int storeId){
		this.storeId=storeId;
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
	public String getStoreAmount(){
		return this.storeAmount;
	}
	public void setStoreAmount(String storeAmount){
		this.storeAmount=storeAmount;
	}
	public int getBatchId(){
		return this.batchId;
	}
	public void setBatchId(int batchId){
		this.batchId=batchId;
	}
	public String getUnit(){
		return this.unit;
	}
	public void setUnit(String unit){
		this.unit=unit;
	}
	public String getStorePrice(){
		return this.storePrice;
	}
	public void setStorePrice(String storePrice){
		this.storePrice=storePrice;
	}

}