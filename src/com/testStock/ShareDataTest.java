package com.testStock;

import com.stockApp.ShareData;

import junit.framework.TestCase;

public class ShareDataTest extends TestCase {

	public void testGetShares()
	{
		ShareData shareData = new ShareData();
		
		assertTrue(true);
	}

	public void testShareData()
	{
		ShareData shareData = new ShareData();
		
		//share list isn't empty
		assertTrue(shareData.getShares() != null);
	}
	
	public void testShareDataSize()
	{
		ShareData shareData = new ShareData();
		
		//share list has the six required shares
		assertTrue(shareData.getShares().size() == 6);
	}

}
