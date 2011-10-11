package com.stockApp;



import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TotalSharesActivity extends  ListActivity 
{ 
	
	
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> shares = new ArrayList<String>();
    ArrayList<Float> totalAmount = new ArrayList<Float>();

    
    
    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    ArrayAdapter<String> adapter;

  
    float total = 0;
    
    
    PriceRetriever prices = new PriceRetriever();
	
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharetotal);
        
        

            getShareData();
       
            
        TextView t=new TextView(this); 
        t=(TextView)findViewById(R.id.lbl_totalworth); 
        t.setText("Portfolio Worth : £" + (total/100));
        
        
    
        
        adapter=new ArrayAdapter<String>(this,
        	    android.R.layout.simple_list_item_1,
        	    shares);
        	setListAdapter(adapter);
        
        	
        	
        	
    }
    
    
    
    private void getShareData()
    {
    	
    	
    	
    	//TODO Get shares from share data file!
    	fillShare("BP" , 192);
    	fillShare("HSBA" , 343);
    	fillShare("EXPN" , 258);
    	fillShare("MKS" , 485);
    	fillShare("SN." , 1219);
    	
    	 total = 0;
    	 
    	 for (Float t : totalAmount)
    	 {
    	        total += t;
    	 }
    }
    
    
  
    private void fillShare(String code , int quantity)
    {
    	float price = prices.getPrice(code);
    	shares.add("" + code + " : " + price + " (" + quantity + ")");
   	 	totalAmount.add(price * quantity);
    	
    	
    }
    

    
}