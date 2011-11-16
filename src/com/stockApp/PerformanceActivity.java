package com.stockApp;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
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



public class PerformanceActivity extends   ListActivity  
{ 
	
	static final ArrayList<HashMap<String,String>> list =  new ArrayList<HashMap<String,String>>();

	
	
	 PriceRetriever priceRetriever = new PriceRetriever();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.performance);
        
        refreshList();
        
    }
    
    
    
    
    private void refreshList()
    {
    	
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.performance_item,
        		new String[] {"name","change","other"},
        		new int[] {R.id.text1,R.id.text2, R.id.lbl_plummet }

        		);
    	
		list.clear();
		populateList();


		setListAdapter(adapter);
    
    }
    
   
    
    private void populateList()
    {
    	
    	
	
		
		
		
    	setBestShares();
		setWorstShares();
		
      
      	 
    
  
    }
    
    
    private void fillShareBest(Share share , float price )
    {
    	price *= 100f;
    	price -= 100f;

        	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", share.getShareName());
        	temp.put("change", "+ " + price + "%");
        	temp.put("other", "Best");
        	list.add(temp);	
    }
    
    
    private void fillShareWorst(Share share , float price )
    {
    	price *= 100f;
    	
        	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", share.getShareName());
        	temp.put("change", price + "%");
        	temp.put("other", "Worst");
        	list.add(temp);	
    }
    

    
    private void setBestShares()
    {
    	ShareData shareData = new ShareData();
    	
    	Share bestShare = null ;
    	float best = 0;
    	
    	Share secondBestShare = null ;
    	float secondBest = 0;
    	
    	//Get Best
     	 for (Share s : shareData.getShares())
       	 {
     		 try
     		 {
     			 	float price = getWeekChange( s);
     			 	if( price >= best)
     			 	{
     			 		if( bestShare != null)
     			 		{
     			 			secondBestShare = bestShare;
     			 			secondBest = best;   			 			
     			 		}
     			 		best = price;
     			 		bestShare = s;
     			 	} 	
     		 }
     		 catch(Exception e)
     		 {
     		 }
       	 }
     	 
     	//Get second Best
     	 for (Share s : shareData.getShares())
       	 {
     		 try
     		 {
     			 	float price = getWeekChange( s);
     			 	if( (price >= secondBest) && (price <= best ))
     			 	{
     			 		if(!(s.equals(bestShare)))
     			 		{
     			 			
     			 		secondBest = price;
     			 		secondBestShare = s;
     			 		}
     			 	} 	
     		 }
     		 catch(Exception e)
     		 {
     		 }
       	 }
     	 
     	 //Set best share
     	 if(bestShare != null)
     	 {
     		 fillShareBest(bestShare , best);
     	 }
     	 
     	 if(secondBestShare != null)
     	 {
     		 fillShareBest(secondBestShare , secondBest);
     	 }
    }
    
    
    private void setWorstShares()
    {
    	ShareData shareData = new ShareData();
    	
    	Share worstShare = null ;
    	float worst = 0;
    	
    	Share secondWorstShare = null ;
    	float secondWorst = 0;
    	
    	//Get Best
     	 for (Share s : shareData.getShares())
       	 {
     		 try
     		 {
     			 	float price = getWeekChange( s);
     			 	if( price <= worst)
     			 	{
     			 		if( worstShare != null)
     			 		{
     			 			secondWorstShare = worstShare;
     			 			secondWorst = worst;   			 			
     			 		}
     			 		worst = price;
     			 		worstShare = s;
     			 	} 	
     		 }
     		 catch(Exception e)
     		 {
     		 }
       	 }
     	 
     	//Get second Best
     	 for (Share s : shareData.getShares())
       	 {
     		 try
     		 {
     			 	float price = getWeekChange( s);
     			 	if( (price <= secondWorst) && (price >= worst ))
     			 	{
     			 		if(!(s.equals(worstShare)))
     			 		{
     			 			
     			 		secondWorst = price;
     			 		secondWorstShare = s;
     			 		}
     			 	} 	
     		 }
     		 catch(Exception e)
     		 {
     		 }
       	 }
     	 
     	 //Set best share
     	 if(secondWorstShare != null)
     	 {
     		 fillShareWorst(secondWorstShare , secondWorst);
     	 }
     	 
     	 if(worstShare != null)
     	 {
     		 fillShareWorst(worstShare , worst);
     	 }
     	 

    	
    	
    
    }
    
    
    
    private float getWeekChange(Share share)
    {
    	float weekChange = 0;
    	
    	
    	float todayPrice = priceRetriever.getPrice(share.getStockCode());
    	
    	weekChange = todayPrice / share.getOldPrice();
    	
    	if(weekChange > 1)
    	{
    	 //weekChange = 100f * weekChange;
    	}
    	else
    	{
    		weekChange = 1f - weekChange;
    		//weekChange = 100f * weekChange;
    		weekChange = -weekChange;
    	}
    	
    	return weekChange;
    }
    
    
    
    
    
}
    
  
  
   
    
    

    
