package com.stockApp;


public class Share 
{
	private String fullShareName ;
	private String stockCode;
	private int amount;

	
	
	public Share(String name , String code , int amountT)
	{
		fullShareName = name;
		stockCode = code;
		amount = amountT;	
	}
	
	
	public String getShareName()
	{
		return fullShareName;
	}
	
	public String getStockCode()
	{
		return stockCode;
	}
	
	public int getShareAmount()
	{
		return amount;
	}
	
	
}
