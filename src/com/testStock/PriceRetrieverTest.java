package com.testStock;

import org.junit.* ;
import static org.junit.Assert.* ;

import com.stockApp.PriceRetriever;

import junit.framework.TestCase;

public class PriceRetrieverTest extends TestCase
{

	
	
	   @Test
	   public void testReadPrice() 
	   {
	      PriceRetriever priceRetriever = new PriceRetriever();
	      
	      
	      float price = priceRetriever.getPrice("BP");
	      
	      
	   
	      
	      assertTrue(price >= 0f) ;
	   }
	
	
	   @Test
	   public void test() 
	   {
	      int price = 50;
	      
	      
	   
	      
	      assertTrue(price == 50) ;
	   }
	
}
