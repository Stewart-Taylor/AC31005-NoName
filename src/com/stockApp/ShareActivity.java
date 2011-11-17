package com.stockApp;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class ShareActivity extends Activity 
{
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shareinfo);
        
        
        //Extracts stock Code
        Bundle b = getIntent().getExtras();
        String stockCode = b.getString("id");
       
        

	ShareData shareData = new ShareData();
	Share share;
        
        
        //Retrieves selected share
      	 for (Share s : shareData.getShares())
    	 {
      		if( stockCode.equals(s.getStockCode()))
      		 {
      			 share = s; 
      			 displayShareData(share);
      		 }
    	 }
        
        }
    
    
    private void displayShareData(Share share)
    {
    	
    	float price = 0;
    	
    	
       	try
    	{
       		PriceRetriever priceRetriever = new PriceRetriever();
       		price = priceRetriever.getPrice(share.getStockCode());
       		
	       	TextView t = new TextView(this); 
	       
	       t=(TextView)findViewById(R.id.lbl_sharePrice); 
	        t.setText("Price: " + (int)price);
            
                t=(TextView)findViewById(R.id.lbl_shareTotal_title); 
                t.setText("Share Set Worth " );
                
                
                
             	int setDisplay = (int)(price * share.getShareAmount())/100;
             	String displaySet = Integer.toString(setDisplay);
             	 displaySet =  insertCommas(displaySet);
                
                t=(TextView)findViewById(R.id.lbl_sharetotal_display); 
                t.setText("£" + displaySet );
                
                t=(TextView)findViewById(R.id.lbl_daychange_percent); 
                
                float percentChange = priceRetriever.getDailyPercentChange(share.getStockCode());
                t.setText("Daily Price Change :  " + percentChange + "%" );
       		
    	}
       	catch(Exception e)
       	{
       		TextView t = new TextView(this); 
                t = (TextView)findViewById(R.id.lbl_sharePrice); 
                t.setText("Could Not Retrieve Price" );
       	}
    	
    	
        TextView t = new TextView(this); 
        

        t=(TextView)findViewById(R.id.lbl_shareName); 
        t.setText(share.getShareName());
        
        t=(TextView)findViewById(R.id.lbl_shareCode); 
        t.setText("Stock Code: " + share.getStockCode());
        

     	String shareAmount = Integer.toString(share.getShareAmount());
     	shareAmount =  insertCommas(shareAmount);
        t=(TextView)findViewById(R.id.lbl_shareAmount); 
        t.setText("Amount : " + shareAmount);
        
    }
  
    
    private String insertCommas(String str)
    {
        if(str.length() < 4){
            return str;
        }
        return insertCommas(str.substring(0, str.length() - 3)) + "," + str.substring(str.length() - 3, str.length());
    }
    
}