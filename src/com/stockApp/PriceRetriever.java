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
     //  readPage("BP");
     //  readPage("HSBA");
     //  readPage("EXPN");
     //  readPage("MKS");
     //  readPage("SN.");
   }
   
   
   
   
   
   public float getPrice(String code)
   {
       
	   String price = readPage("SN.");
	   
	   
	   Float priceFloat = new Float(price);
	   
       return priceFloat;
       
   }
   
   
   private  String  readPage(String code)
   {
       String price = "Error";
       
       try
       {
               URL url = new URL("http://finance.google.com/finance/info?client=ig&q=" + code);
       URLConnection con = url.openConnection();
       BufferedReader in = new BufferedReader(
                               new InputStreamReader(
                               con.getInputStream()));
       String inputLine;

       int counter  = 0;
       while ((inputLine = in.readLine()) != null) 
       {
           counter++;
           if(counter == 7)
           {
              // System.out.println(inputLine);
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
   
}
