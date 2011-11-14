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



public class AlertActivity extends  ListActivity 
{ 
	
	static final ArrayList<HashMap<String,String>> list =  new ArrayList<HashMap<String,String>>();
	
	
    float plummetValue = -0.01f;
    float rocketValue = 2;
    
    
    PriceRetriever priceRetriever = new PriceRetriever();
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert);
        
     
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.alert_item,
        		new String[] {"name","price","color"},
        		new int[] {R.id.text1,R.id.text2, R.id.text3}

        		);
        
        
        		list.clear();
        		populateList();


        		setListAdapter(adapter);
    }
    
    
    
    
    
    
    private void populateList()
    {
    	
    	
		ShareData shareData = new ShareData();
		
		
      	 for (Share s : shareData.getShares())
   	 {
   	        fillShare(s.getStockCode() );
   	 }
      	 
    
  
    }
    
    
    private void fillShare(String code )
    {
    	
    	
    	try
    	{
    		
    	
    	float price = priceRetriever.getDailyPercentChange(code);
    	
    	
    	if( price > rocketValue)
    	{
    		
        	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", code);
        	temp.put("price", Float.toString(price)+ "%");
        	temp.put("color", "Rocket");
        	list.add(temp);
    		
    
    		
    	}
    	else if ( price < plummetValue)
    	{
        	
    		HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", code);
        	temp.put("price", Float.toString(price) + "%");
        	temp.put("color", "Plummet");
        	list.add(temp);
    		
    	}
    	
    	}
     	catch(Exception ex)
    	{
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, "Could not connect to the internet!", Toast.LENGTH_LONG);
			toast.show();
    	}	 
    	
    	
    }
    

    
}