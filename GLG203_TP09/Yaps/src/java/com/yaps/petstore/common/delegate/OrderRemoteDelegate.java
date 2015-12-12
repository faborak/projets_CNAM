package com.yaps.petstore.common.delegate;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.yaps.petstore.common.rmi.RMIConstant;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the OrderService class. Each method delegates the call to the
 * OrderService class
 */
public final class OrderRemoteDelegate extends OrderDelegate implements RMIConstant {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static OrderServiceRemote orderServiceRemote;

    // ======================================
    // =            Private methods         =
    // ======================================
    protected OrderServiceRemote getOrderService() throws RemoteException {
        try {
            orderServiceRemote = (OrderServiceRemote) Naming.lookup(ORDER_SERVICE_URL);
        } catch (Exception e) {
            throw new RemoteException("Lookup exception", e);
        }
        return orderServiceRemote;
    }
}
