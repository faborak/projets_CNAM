package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;

import com.yaps.petstore.server.service.customer.CustomerService;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CustomerService class. Each method delegates the call to the
 * CustomerService class
 */
public final class CustomerLocalDelegate extends CustomerDelegate {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static CustomerServiceRemote customerServiceRemote;

    // ======================================
    // =            Private methods         =
    // ======================================
    protected CustomerServiceRemote getCustomerService() throws RemoteException {
    	if ( customerServiceRemote == null )
    		customerServiceRemote = new CustomerService();
    	return customerServiceRemote;
    }
}
