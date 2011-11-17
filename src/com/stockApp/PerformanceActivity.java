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
	ArrayList<PerformanceShare> shares = new ArrayList<PerformanceShare>();
	
	
	 PriceRetriever priceRetriever = new PriceRetriever();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.performance);
        
        refreshList();
        
    }
    
    
    
    
    
    private void loadShares()
    {
    	ShareData shareData = new ShareData();
    	
    	 for (Share s : shareData.getShares())
       	 {
    		 PerformanceShare share  = new PerformanceShare();
    		 share.share = s;
    		 
    		 try
    		 {
    		 	share.price = priceRetriever.getPrice(s.getStockCode());
    		 	
    		 	shares.add(share);
    		 }
    		 catch(Exception e)
    		 {
    			 
    		 }
       	 }
    }
    
    
    private void refreshList()
    {
    	
    	
    	loadShares();
    	
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		list,
        		R.layout.performance_item,
        		new String[] {"name","best" , "worst"},
        		new int[] {R.id.text1, R.id.lbl_best , R.id.lbl_worst }

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
    	
    	
    	String priceDisplay  = String.format("%.2f" , price);

        	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", share.getShareName());
        	//temp.put("change", "+" + priceDisplay + "%");
        	temp.put("best", "+" + priceDisplay + "%");
        	temp.put("worst", "");
        	list.add(temp);	
    }
    
    
    private void fillShareWorst(Share share , float price )
    {
    	price *= 100f;
    	
    	String priceDisplay  = String.format("%.2f" , price);
    	
        	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", share.getShareName());
        	 // temp.put("change", priceDisplay + "%");
        	temp.put("worst", priceDisplay + "%");
        	temp.put("best", "");
        	list.add(temp);	
    }
    

    
    private void setBestShares()
    {
  
    	
    	PerformanceShare bestShare = null ;
    	float best = 0;
    	
    	PerformanceShare secondBestShare = null ;
    	float secondBest = 0;
    	
    	//Get Best
    	 for (PerformanceShare s : shares)
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
     	 for (PerformanceShare s : shares)
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
     		
         	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", "Best Of The Week");
        	temp.put("change", "");
        	temp.put("worst", "");
        	temp.put("best", "");
        	list.add(temp);	
     		 
     		 fillShareBest(bestShare.share , best);
     	 }
     	 
     	 if(secondBestShare != null)
     	 {
     		 fillShareBest(secondBestShare.share , secondBest);
     	 }
    }
    
    
    private void setWorstShares()
    {
    	
    	
    	PerformanceShare worstShare = null ;
    	float worst = 0;
    	
    	PerformanceShare secondWorstShare = null ;
    	float secondWorst = 0;
    	
    	//Get Best
    	 for (PerformanceShare s : shares)
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
     	 for (PerformanceShare s : shares)
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
     		 
          	HashMap<String,String> temp = new HashMap<String,String>();
        	temp.put("name", "Worst Of The Week");
        	temp.put("change", "");
        	temp.put("worst", "");
        	temp.put("best", "");
        	list.add(temp);	
     		 
     		 
     		 fillShareWorst(secondWorstShare.share , secondWorst);
     	 }
     	 
     	 if(worstShare != null)
     	 {
     		 fillShareWorst(worstShare.share , worst);
     	 }
     	 

    	
    	
    
    }
    
    
    
    private float getWeekChange(PerformanceShare share)
    {
    	float weekChange = 0;
    	
    	
    	
    	weekChange = share.price / share.share.getOldPrice();
    	
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
    

//Acts as a struct
class PerformanceShare
{
    public Share share;
    public float price;
}
  
  
   
    
    

    
