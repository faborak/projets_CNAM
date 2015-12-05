package com.yaps.petstore.common.delegate;

import com.yaps.petstore.common.dto.OrderDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.rmi.RMIConstant;



import java.rmi.RemoteException;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the OrderService class. Each method delegates the call to the
 * OrderService class
 */
public abstract class OrderDelegate implements OrderServiceRemote {

    // ======================================
    // =             Attributes             =
    // ======================================
    

    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Delegates the call to the {@link OrderServiceRemote#createOrder(OrderDTO) OrderServiceRemote().createOrder} method.
     */
    public OrderDTO createOrder(final OrderDTO orderDTO) throws CreateException, CheckException, RemoteException {
        return getOrderService().createOrder(orderDTO);
    }

    /**
     * Delegates the call to the {@link OrderServiceRemote#findOrder(String) OrderServiceRemote().findOrder} method.
     */
    public OrderDTO findOrder(final String orderId) throws FinderException, CheckException, RemoteException {
        return getOrderService().findOrder(orderId);
    }

    /**
     * Delegates the call to the {@link OrderServiceRemote#deleteOrder(String) OrderServiceRemote().deleteOrder} method.
     */
    public void deleteOrder(final String orderId) throws RemoveException, CheckException, RemoteException {
        getOrderService().deleteOrder(orderId);
    }

    // ======================================
    // =            Protected methods         =
    // ======================================
    protected abstract OrderServiceRemote getOrderService() throws RemoteException;
}
