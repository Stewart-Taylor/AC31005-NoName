package com.stockApp;


public class Share 
{
	private String fullShareName ;
	private String stockCode;
	private int amount;
	private float oldPrice;
	
	
	public Share(String name , String code , int amountT ,float oldPriceT)
	{
		fullShareName = name;
		stockCode = code;
		amount = amountT;	
		oldPrice = oldPriceT;
	}
	
	
	public String getShareName()
	{
		return fullShareName;
	}
	
	public String getStockCode()
	{
		return stockCode;
	}
	
	
	public float getOldPrice()
	{
		return oldPrice;
	}
	
	public int getShareAmount()
	{
		return amount;
	}
	
	
}
