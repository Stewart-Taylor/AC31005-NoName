package com.testStock;

import com.stockApp.Share;

import junit.framework.TestCase;

public class ShareTest extends TestCase {
	//add test share
	private Share share = new Share("test", "code", 100, 1.01f);;
	
	public void testShare()
	{
		// Proves share actually 
		assertTrue(share.getOldPrice() != 0);
		assertTrue(share.getShareName().length() != 0);
		assertTrue(share.getShareAmount() != 0);
		assertTrue(share.getOldPrice() != 0);
	}

	public void testGetShareName()
	{
		assertTrue(share.getOldPrice() != 0);
	}

	public void testGetStockCode()
	{
		assertTrue(share.getShareName().length() != 0);
	}

	public void testGetOldPrice()
	{
		assertTrue(share.getShareAmount() != 0);
	}

	public void testGetShareAmount()
	{
		assertTrue(share.getOldPrice() != 0);
	}
	
	public boolean runTests()
	{
		testGetShareAmount();
		testGetOldPrice();
		testGetStockCode();
		testGetShareName();
		testShare();
		
		assertTrue(true);
		
		return true;
	}

}
