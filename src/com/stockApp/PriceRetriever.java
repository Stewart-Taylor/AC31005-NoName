package com.stockApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PriceRetriever
{
    
    
   public  void PriceRetriever(String[] args) throws Exception
   {
 
   }
   

   public float getPrice(String code)
   {
	return new Float(readPriceFromURL(code));
   }
   
   public float getDailyPercentChange(String code)
   {
   	return new Float(extractPrice(code));
   }
   
   
   
   private String readPriceFromURL(String code)
   {
       String price = "Error";
       
       try
       {
               	URL url = new URL("http://finance.google.com/finance/info?client=ig&q=" + code);
       	       	URLConnection connection = url.openConnection();
     		BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
      		
      		String inputLine;

	       int counter  = 0;
	       while ((inputLine = in.readLine()) != null) 
	       {
	           counter++;
	           if(counter == 7)
	           {
	              price = extractPrice(inputLine);
	           }
	       }
	       
	       in.close();
       
	}
	catch(Exception e)
	{
		
	}
       
       return price;
   }
   
   
   
   private  String extractPrice(String input)
   {
       //,"l" : "326.40" INPUT FORMAT
       
       String endText = input.substring(8 , input.length() );
       endText =  endText.substring(0, endText.indexOf("\"") );
     
       return endText; 
   }
   
   
    public String readDayPercentChangeFromURL(String code)
   {
       String price = "Error";
       
       try
       {
                URL url = new URL("http://finance.google.com/finance/info?client=ig&q=" + code);
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            
            String inputLine;

           int counter  = 0;
           while ((inputLine = in.readLine()) != null) 
           {
               counter++;
               if(counter == 13)
               {
                  price = extractChangePercent(inputLine);
               }
           }

           in.close();
       
    }
    catch(Exception e)
    {

    }
       
       return price;
   }
   
    private  String extractChangePercent(String input)
   {
       //,"cp" : "0.85" INPUT FORMAT
       
       String endText = input.substring(9 , input.length() );
       endText =  endText.substring(0, endText.indexOf("\"") );
       
       return endText; 
   }
   
   
}
