package com.stockApp;


import java.util.ArrayList;


public class ShareData 
{

	private ArrayList<Share> shares = new ArrayList<Share>();
	
	
	public ArrayList<Share> getShares()
	{
		return shares;
	}
	
	
	public ShareData()
	{
		loadShareData();	
	}
	
	
	private void loadShareData()
	{

		//will get from file in future
		shares.add( new Share( "BP Amoco pl" , "BP", 192 , 458.8f));
		shares.add( new Share( "Bowleven plc  ord 10P" , "BLVN", 3960 , 99.0f));   
		shares.add( new Share( "Experian ordinary" , "EXPN", 258 , 828.5f));
		shares.add( new Share( "HSBC Holdings plc ordinary 50 Cents" , "HSBA", 343 ,504.7f));
		shares.add( new Share( "Marks and Spencer Ordinary 25" , "MKS", 485 ,330.9f));
		shares.add( new Share( "Smith & Nephew plc ordinary" , "SN.", 1219 ,572.5f));
		
		//shares.add( new Share( "Test ltd 40 PLC" , "Test", 999));
	}
	
	
	
	
	private void saveShareData()
	{
	
		//yeah i know...

	}

	
}
