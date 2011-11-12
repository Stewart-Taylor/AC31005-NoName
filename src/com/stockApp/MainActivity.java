package com.stockApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;



public class MainActivity extends Activity implements OnClickListener 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
    	//Creates XML GUI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
            
        // click-handlers for buttons
        View newButton = findViewById(R.id.btn_portfolio);
        newButton.setOnClickListener(this);
        View alertButton = findViewById(R.id.btn_alert);
        alertButton.setOnClickListener(this);
        View performanceButton = findViewById(R.id.btn_performance);
        performanceButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.btn_about);
        aboutButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.btn_exit);
        exitButton.setOnClickListener(this);
         
    }
    
    
    public void onClick(View v)
    {
    	
    	try
    	{
    		//Starts new activity based on which button pressed
	        switch (v.getId()) 
	        {
		            case R.id.btn_portfolio:
		                Intent sharesIntent = new Intent(this, TotalSharesActivity.class);
		                startActivity(sharesIntent);
		                break;
		            case R.id.btn_alert:
		                Intent alertIntent = new Intent(this, AlertActivity.class);
		                startActivity(alertIntent);
		                break;
		            case R.id.btn_performance:
		                Intent performanceIntent = new Intent(this, PerformanceActivity.class);
		                startActivity(performanceIntent);
		                break;
		            case R.id.btn_about:
		                Intent aboutIntent = new Intent(this, AboutActivity.class);
		                startActivity(aboutIntent);
		                break;
		            case R.id.btn_exit:
		            	finish();
		                break;
	        }
        
        
    	}
    	catch(Exception ex)
    	{
    			//POP UP Error Message
			Context context = getApplicationContext();
			CharSequence text = ex.toString();
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
    	}
    }
}