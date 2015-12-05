package com.yaps.petstore.common.delegate;

import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.*;
import com.yaps.petstore.common.rmi.RMIConstant;



import java.rmi.RemoteException;
import java.util.Collection;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CustomerService class. Each method delegates the call to the
 * CustomerService class
 */
public abstract class CustomerDelegate implements CustomerServiceRemote {

    // ======================================
    // =             Attributes             =
    // ======================================
    

    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Delegates the call to the {@link CustomerServiceRemote#createCustomer(CustomerDTO) CustomerServiceRemote().createCustomer} method.
     */
    public CustomerDTO createCustomer(final CustomerDTO customerDTO) throws CreateException, CheckException, RemoteException {
        return getCustomerService().createCustomer(customerDTO);
    }

    /**
     * Delegates the call to the {@link CustomerServiceRemote#findCustomer(String) CustomerServiceRemote().findCustomer} method.
     */
    public CustomerDTO findCustomer(final String customerId) throws FinderException, CheckException, RemoteException {
        return getCustomerService().findCustomer(customerId);
    }

    /**
     * Delegates the call to the {@link CustomerServiceRemote#deleteCustomer(String) CustomerServiceRemote().deleteCustomer} method.
     */
    public void deleteCustomer(final String customerId) throws RemoveException, CheckException, RemoteException {
        getCustomerService().deleteCustomer(customerId);
    }

    /**
     * Delegates the call to the {@link CustomerServiceRemote#updateCustomer(CustomerDTO) CustomerServiceRemote().updateCustomer} method.
     */
    public void updateCustomer(final CustomerDTO customerDTO) throws UpdateException, CheckException, RemoteException {
        getCustomerService().updateCustomer(customerDTO);
    }

    /**
     * Delegates the call to the {@link CustomerServiceRemote#findCustomers() CustomerServiceRemote().findCustomers} method.
     */
    public Collection findCustomers() throws FinderException, RemoteException {
        return getCustomerService().findCustomers();
    }

    // ======================================
    // =            Protected methods         =
    // ======================================
    protected abstract CustomerServiceRemote getCustomerService() throws RemoteException;
}
