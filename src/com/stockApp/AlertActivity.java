package com.stockApp;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;



public class AlertActivity extends  ListActivity 
{ 
	
	static final ArrayList<HashMap<String,String>> list =  new ArrayList<HashMap<String,String>>();
	
	
    float plummetValue = -20.0f;
    float rocketValue = 10.0f;
    
    
    PriceRetriever priceRetriever = new PriceRetriever();
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert);
        
     

        refreshList();
        

    }
    
    
    
    
    
    private void refreshList()
    {
    	
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.alert_item,
        		new String[] {"name","change","rocket" ,"plummet"},
        		new int[] {R.id.text1,R.id.text2, R.id.lbl_rocket , R.id.lbl_plummet }

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
   	        fillShare(s );
   	 }
      	 
    if(list.isEmpty())
    {
    	HashMap<String,String> temp = new HashMap<String,String>();
    	temp.put("name", "No Plummets or Rockets!");
    	temp.put("change", "");
    	temp.put("rocket", "");
    	temp.put("plummet", "");
    	list.add(temp);
    	
    }
  
    }
    
    
    private void fillShare(Share share )
    {
    	
    	
    	try
    	{
    		
    	
    	float price = priceRetriever.getDailyPercentChange(share.getStockCode());
    	
    	
    	if( price > rocketValue)
    	{
    		
        	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", share.getShareName());
        	temp.put("change", Float.toString(price)+ "%");
        	temp.put("rocket", "Rocket");
        	temp.put("plummet", "");
        	list.add(temp);
    		
    
    		
    	}
    	else if ( price < plummetValue)
    	{
        	
    		HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", share.getShareName());
        	temp.put("change", Float.toString(price) + "%");
        	temp.put("rocket", "");
        	temp.put("plummet", "plummet");
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