package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;

import com.yaps.petstore.server.service.order.OrderService;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the OrderService class. Each method delegates the call to the
 * OrderService class
 */
public final class OrderLocalDelegate extends OrderDelegate {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static OrderServiceRemote orderServiceRemote;

    // ======================================
    // =            Private methods         =
    // ======================================
    protected OrderServiceRemote getOrderService() throws RemoteException {
    	if ( orderServiceRemote == null )
    		orderServiceRemote = new OrderService();
    	return orderServiceRemote;
    }
}
