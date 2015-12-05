package com.yaps.petstore.common.delegate;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.yaps.petstore.common.rmi.RMIConstant;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CustomerService class. Each method delegates the call to the
 * CustomerService class
 */
public final class CustomerRemoteDelegate extends CustomerDelegate implements RMIConstant {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static CustomerServiceRemote customerServiceRemote;

    // ======================================
    // =            Private methods         =
    // ======================================
    protected CustomerServiceRemote getCustomerService() throws RemoteException {
        try {
            customerServiceRemote = (CustomerServiceRemote) Naming.lookup(CUSTOMER_SERVICE_URL);
        } catch (Exception e) {
            throw new RemoteException("Lookup exception", e);
        }
        return customerServiceRemote;
    }
}
