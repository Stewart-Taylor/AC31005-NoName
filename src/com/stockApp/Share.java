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
	
	
	public getShareName()
	{
		return fullShareName;
	}
	
	public getStockCode()
	{
		return stockCode;
	}
	
	public getShareAmount()
	{
		return amount;
	}
	
	
}
