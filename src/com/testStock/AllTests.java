package com.testStock;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(PriceRetrieverTest.class);
		suite.addTestSuite(ShareDataTest.class);
		suite.addTestSuite(ShareTest.class);
		//$JUnit-END$
		return suite;
	}

}
