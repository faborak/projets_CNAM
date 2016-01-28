package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;
import java.util.Collection;

import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CustomerService class. Each method delegates the call to the
 * CustomerService class
 */
public abstract class CustomerDelegate implements CustomerServiceRemote {

    // ======================================
    // =             Attributes             =
    // ======================================
    

    /** @link dependency 
     * @label delegates*/
    /*# com.yaps.petstore.server.service.customer.CustomerService lnkCustomerService; */
    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Delegates the call to the {@link CustomerServiceRemote#authenticate(String, String) CustomerServiceRemote().authenticate} method.
     */
    public CustomerDTO authenticate(final String customerId, final String password) throws FinderException, CheckException, RemoteException {
        return getCustomerService().authenticate(customerId, password);
    }

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
    
    /**
     * Delegates the call to the {@link CustomerServiceRemote#findCustomersWithNameStartingWith() CustomerServiceRemote().findCustomersWithNameStartingWith} method.
     */
    public Collection findCustomersWithNameStartingWith(final String lastNameFirstChars) throws FinderException, CheckException, RemoteException{
    	return getCustomerService().findCustomersWithNameStartingWith(lastNameFirstChars);
    }
    
    /**
     * Delegates the call to the {@link CustomerServiceRemote#findCustomersByAge() CustomerServiceRemote().findCustomersByAge} method.
     */
    public Collection findCustomersByAge(int minAge, int maxAge) throws FinderException, CheckException, RemoteException{
    	return getCustomerService().findCustomersByAge(minAge, maxAge);
    }

    // ======================================
    // =            Protected methods         =
    // ======================================
    protected abstract CustomerServiceRemote getCustomerService() throws RemoteException;
}
