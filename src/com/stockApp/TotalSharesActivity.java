package com.stockApp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class TotalSharesActivity extends  ListActivity 
{ 
	
	
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
	static final ArrayList<HashMap<String,String>> shares =  new ArrayList<HashMap<String,String>>();
    ArrayList<String> shareData = new ArrayList<String>();

    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    ArrayAdapter<String> adapter;

  
    
    
    PriceRetriever priceRetriever = new PriceRetriever();
	
    //used to indicate if a set could not be retrieved
	boolean errorGettingTotals = false;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharetotal);
        
       
        
        shares.clear();
        shareData.clear();
        
        
        populateList();
        float portfolioTotal =  getPortfolioTotal();
        
        
        
            
        TextView t = new TextView(this); 
        t =(TextView)findViewById(R.id.lbl_totalworth); 
        t.setText(" £" + portfolioTotal);
        
        
        if( errorGettingTotals == true)
        {
            t =(TextView)findViewById(R.id.lbl_error); 
            t.setText("Error Retrieving Shares! , Total will not include all shares");	
        }
        
        
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		shares,
        		R.layout.portfolio_item,
        		new String[] {"name","price","extra"},
        		new int[] {R.id.lbl_sharename,R.id.lbl_share_set, R.id.lbl_extra_shareinfo}
        		);
        
        	
        		setListAdapter(adapter);
    }
    
    
    
    private void populateList()
    {
    	
    	
		ShareData shareData = new ShareData();
		
		
      	 for (Share s : shareData.getShares())
   	 {
   	        fillShare(s);
   	 }
      	 
    
  
    }
    
    
    
    private float getPortfolioTotal()
    {
    	
    	ShareData shareData = new ShareData();
    	
    	float total = 0;
     	 for (Share s : shareData.getShares())
       	 {
     		 total += getSetWorth(s);
     		
       	        
       	        
       	 }
    	
     	 //format number then return
     	 //TO DO
     	 
    	return total;
    
    }
    
    
    
    private float getSetWorth(Share share)
    {
    	float setTotal = 0;
    	
    	try
    	{
    	
    	float price = priceRetriever.getPrice(share.getStockCode());
    	
    	setTotal = share.getShareAmount() * price ;
    	
    	}
    	catch(Exception e)
    	{
    		errorGettingTotals = true;
    	}
    	
    	return setTotal ;
    }
    
    
    
    
    
    private void fillShare(Share share )
    {
    	
    	
    	try
    	{
    		
    		float price = priceRetriever.getPrice(share.getStockCode());
    		
    	
        	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", share.getShareName());
        	temp.put("price", "Set Worth : £" + ( (price * share.getShareAmount())/100) );
        	temp.put("extra", "Amount: " + share.getShareAmount() + "	 Price: " + price);
        	shares.add(temp);
        	shareData.add(share.getStockCode());
    
    		
    
    	
    	}
     	catch(Exception ex)
    	{
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, "Error Retrieving Share Data ", Toast.LENGTH_LONG);
			toast.show();
			
        	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", share.getShareName());
        	temp.put("price", "Can't Retrieve Data " );
        	temp.put("extra", "Amount: " + share.getShareAmount() );
        	shares.add(temp);
        	shareData.add(share.getStockCode());
	   	 	
    	}	 
    	
    	
    }
    
    
    
    
    /*
  
    private void fillShare(String code , int quantity)
    {
    	
    	
    	try
    	{
    	
    	float price = PriceRetriever.getPrice(code);
    	shares.add("" + code + " : " + price + " (" + quantity + ")");
   	 	totalAmount.add(price * quantity);
   	 	shareData.add(code);
    
    	
    	}
     	catch(Exception ex)
    	{
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, "Error Retrieving Share Data ", Toast.LENGTH_LONG);
			toast.show();
			
			
	    	shares.add("" + code + " :" + "Can't Retrieve Data ");
	   	 	shareData.add(code);
			
    	}	 
   	 	
    }
    
    
    */
    
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
    	try
    	{
	    	String selection = shareData.get(position);
	    	
	    	//start activity
	        Intent shareIntent = new Intent(this, ShareActivity.class);
	        
	        //Passes share code to new Activity
	        Bundle b = new Bundle();
	        b.putString("id" , selection );
	        shareIntent.putExtras(b);
	
	        startActivity(shareIntent);
    	}
	catch(Exception ex)
	{
		Context context = getApplicationContext();
		CharSequence errorText = ex.toString();
		Toast toast = Toast.makeText(context, errorText, Toast.LENGTH_LONG);
		toast.show();
			
	}
    }
    

    
}