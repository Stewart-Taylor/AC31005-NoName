package com.stockApp;


import java.util.ArrayList;

public class ShareData 
{


	
 ArrayList<Share> shares = new ArrayList<Share>();



	
	
	
 
 

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
	
	 shares.add( new Share( "BP Amoco pl" , "BP", 192));
	 shares.add( new Share( "HSBC Holdings plc ordinary 50 Cents" , "HSBA", 343));
	 shares.add( new Share( "Experian ordinary" , "EXPN", 258));
	 shares.add( new Share( "Marks and Spencer Ordinary 25" , "MKS", 485));
	 shares.add( new Share( "Smith & Nephew plc ordinary" , "SN.", 1219));
	 shares.add( new Share( "Bowleven plc  ord 10P" , "BLVN", 100));  //AMOUNTS NEEDS CONFIRMED
 	



}




private void saveShareData()
{




}

	
}
