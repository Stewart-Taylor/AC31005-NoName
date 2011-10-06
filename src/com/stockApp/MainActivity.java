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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        // click-handlers for buttons
        View newButton = findViewById(R.id.btn_new);
        newButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.btn_exit);
        exitButton.setOnClickListener(this);
        
        
        
    }
    
    
    public void onClick(View v)
    {
    	
    	try
    	{
    	
        switch (v.getId()) 
        {
            case R.id.btn_new:
                Intent newlog = new Intent(this, TotalSharesActivity.class);
                startActivity(newlog);
                break;
            case R.id.btn_exit:
            	finish();
            	//System.exit(0);  
                break;
        }
        
        
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