package com.yaps.petstore.common.delegate;

import com.yaps.petstore.AbstractTestCase;
import junit.framework.TestSuite;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * This class tests the ShoppingCartDelegate class
 */
public final class ShoppingCartDelegateTest extends AbstractTestCase {
	String _sessionId1 = "sid1";
	ShoppingCartDelegate _delegate1;

    public ShoppingCartDelegateTest(final String s) {
        super(s);
    }

    public static TestSuite suite() {
        return new TestSuite(ShoppingCartDelegateTest.class);
    }
    
    public void setUp() throws RemoteException {
    	_delegate1 = new ShoppingCartDelegate(_sessionId1);
    	_delegate1.empty();
    }

    //==================================
    //=            Test cases          =
    //==================================

    /**
     * This test ensures that the method getCart works.
     */
    public void testGetCart() throws Exception {
    	assertEquals(_sessionId1, _delegate1.getSessionId());
        
        final String sessionId2 = "sid2";
    	ShoppingCartDelegate delegate2 = new ShoppingCartDelegate(sessionId2);
    	assertEquals(sessionId2, delegate2.getSessionId());
    }

    /**
     * This test ensures that the method the shopping cart is not a singleton.
     */
    public void testAddItemsToSeveralCarts() throws Exception {
        final String sessionId2 = "sid2";

    	_delegate1.addItem("EST10");
    	_delegate1.addItem("EST11");
    	assertEquals(54.0, _delegate1.getTotal());
    	Collection c = _delegate1.getItems();
    	assertEquals(2, c.size());
        
    	ShoppingCartDelegate delegate2 = new ShoppingCartDelegate(sessionId2);
    	delegate2.addItem("EST10");
    	c = delegate2.getItems();
    	assertEquals(1, c.size());
    	
    	c = _delegate1.getItems();
    	assertEquals(2, c.size());
    }

    /**
     * This test ensures that the methods addItem and updateItemQuantity work.
     */
    public void testAddAndUpdateAndRemoveItems() throws Exception {
    	String itemId = "EST11";
    	_delegate1.addItem(itemId);
    	Collection c = _delegate1.getItems();
    	assertEquals(1, c.size());
    	_delegate1.updateItemQuantity(itemId, 3);
    	c = _delegate1.getItems();
    	assertEquals(1, c.size());
    	assertEquals(96.0, _delegate1.getTotal());
        
    	_delegate1.removeItem(itemId);
    	c = _delegate1.getItems();
    	assertEquals(0, c.size());
    	assertEquals(0.0, _delegate1.getTotal());
    }

}
