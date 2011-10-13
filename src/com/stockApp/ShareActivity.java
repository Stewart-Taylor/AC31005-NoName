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
        
        
        Bundle b = getIntent().getExtras();
        String stockCode = b.getString("id");
       
        

		ShareData sd = new ShareData();
		Share share;
        
      	 for (Share s : sd.getShares())
    	 {
      		 if( stockCode.equals(s.stockCode))
      		 {
      			 share = s; 
      			 setShareData(share);
      		 }
    	 }
        
        
    
      	 
        
    }
    
    
    private void setShareData(Share share)
    {
    	
    	
    	
    	float price = 0;
    	
    	
    	
    	//get price
       	try
    	{
       		PriceRetriever prices = new PriceRetriever();
       		
       		price = prices.getPrice(share.stockCode);
       		
       	    TextView t=new TextView(this); 
            t=(TextView)findViewById(R.id.lbl_sharePrice); 
            t.setText("Price: " + price);
            
            t=(TextView)findViewById(R.id.lbl_shareTotal); 
            
            String priceDisplay = String.format("%.2f%n" , (price * share.amount)/100);
            t.setText("Share Set Worth : £" + priceDisplay );
       		
    	}
       	catch(Exception e)
       	{
       		
       		TextView t=new TextView(this); 
            t=(TextView)findViewById(R.id.lbl_sharePrice); 
            t.setText("Could Not Retrieve Price" );
       	}
    	
    	
    	
    	
        TextView t=new TextView(this); 
        

        t=(TextView)findViewById(R.id.lbl_shareName); 
        t.setText(share.fullShareName);
        
        t=(TextView)findViewById(R.id.lbl_shareCode); 
        t.setText("Stock Code: " + share.stockCode);
        

        
        t=(TextView)findViewById(R.id.lbl_shareAmount); 
        t.setText("Amount : " + share.amount);
        

    	
    	
    	
    }
    
    
  
    
    
}