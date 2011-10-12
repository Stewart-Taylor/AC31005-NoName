package com.stockApp;



import java.util.ArrayList;




import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TotalSharesActivity extends  ListActivity 
{ 
	
	
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> shares = new ArrayList<String>();
    ArrayList<String> shareData = new ArrayList<String>();
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
    	
    	try
    	{
    	
    		/*
    	//TODO Get shares from share data file!
    	fillShare("BP" , 192);
    	fillShare("HSBA" , 343);
    	fillShare("EXPN" , 258);
    	fillShare("MKS" , 485);
    	fillShare("SN." , 1219);
    	
    	*/
    		
    		ShareData sd = new ShareData();
    		
    		
    		
       	 for (Share s : sd.getShares())
    	 {
    	        fillShare(s.stockCode , s.amount);
    	 }
    		
    	
    	 total = 0;
    	 
    	 for (Float t : totalAmount)
    	 {
    	        total += t;
    	 }
    	 
    	}
     	catch(Exception ex)
    	{
			Context context = getApplicationContext();
			CharSequence text = ex.toString();
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, "Could not connect to the internet!", duration);
			toast.show();
    	}
    	 
    }
    
    
  
    private void fillShare(String code , int quantity)
    {
    	float price = prices.getPrice(code);
    	shares.add("" + code + " : " + price + " (" + quantity + ")");
   	 	totalAmount.add(price * quantity);
   	 	shareData.add(code);
    	
    }
    
    
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
    	try
    	{
    	
    
    	String selection = shareData.get(position);
    	
    	//start activity
        Intent shareIntent = new Intent(this, ShareActivity.class);
        
        Bundle b = new Bundle();

        b.putString("id" , selection );

        shareIntent.putExtras(b);

        startActivity(shareIntent);
        
    	}
		catch(Exception ex)
		{
			Context context = getApplicationContext();
			CharSequence text = ex.toString();
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
		}
    }
    

    
}