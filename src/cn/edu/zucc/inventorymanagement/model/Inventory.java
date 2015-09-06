package cn.edu.zucc.inventorymanagement.model;

/**
*	*@author yangsj
*/

public class Inventory
{
	private int houseId;
	private int goodsId;
	private float enterAmount;
	private float exitAmount;
	private float exchangeInAmount;
	private float exchangeOutAmount;
	private float returnAmount;
	private float destoryAmount;
	private float storeAmount;
	private String averagePrice;

	public int getHouseId()
	{
		return this.houseId;
	}

	public void setHouseId(int houseId)
	{
		this.houseId = houseId;
	}

	public int getGoodsId()
	{
		return this.goodsId;
	}

	public void setGoodsId(int goodsId)
	{
		this.goodsId = goodsId;
	}

	public float getEnterAmount()
	{
		return this.enterAmount;
	}

	public void setEnterAmount(float enterAmount)
	{
		this.enterAmount = enterAmount;
	}

	public float getExitAmount()
	{
		return this.exitAmount;
	}

	public void setExitAmount(float exitAmount)
	{
		this.exitAmount = exitAmount;
	}

	public float getExchangeInAmount()
	{
		return this.exchangeInAmount;
	}

	public void setExchangeInAmount(float exchangeInAmount)
	{
		this.exchangeInAmount = exchangeInAmount;
	}

	public float getExchangeOutAmount()
	{
		return this.exchangeOutAmount;
	}

	public void setExchangeOutAmount(float exchangeOutAmount)
	{
		this.exchangeOutAmount = exchangeOutAmount;
	}
	
	public float getReturnAmount()
	{
		return this.returnAmount;
	}

	public void setReturnAmount(float returnAmount)
	{
		this.returnAmount = returnAmount;
	}

	public float getDestoryAmount()
	{
		return this.destoryAmount;
	}

	public void setDestoryAmount(float destoryAmount)
	{
		this.destoryAmount = destoryAmount;
	}

	public float getStoreAmount()
	{
		return this.storeAmount;
	}

	public void setStoreAmount(float storeAmount)
	{
		this.storeAmount = storeAmount;
	}

	public String getAveragePrice()
	{
		return this.averagePrice;
	}

	public void setAveragePrice(String averagePrice)
	{
		this.averagePrice = averagePrice;
	}

}