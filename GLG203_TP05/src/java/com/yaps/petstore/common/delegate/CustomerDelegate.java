package com.yaps.petstore.common.delegate;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;
import com.yaps.petstore.common.rmi.RMIConstant;
import com.yaps.petstore.server.service.customer.CustomerServiceRemote;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CustomerService class. Each method delegates the call to the
 * CustomerService class
 */
public class CustomerDelegate implements RMIConstant {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static CustomerServiceRemote customerServiceRemote;
	
 // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Delegates the call to the {@link CustomerServiceRemote#createCustomer(CustomerDTO) CustomerServiceRemote().createCustomer} method.
     */
    public static CustomerDTO createCustomer(final CustomerDTO customerDTO) throws CreateException, CheckException, RemoteException {
        return getCustomerService().createCustomer(customerDTO);
    }
    
    /**
     * Delegates the call to the {@link CustomerServiceRemote#findCustomer(String) CustomerServiceRemote().findCustomer} method.
     */
    public static CustomerDTO findCustomer(final String customerId) throws FinderException, CheckException, RemoteException {
        return getCustomerService().findCustomer(customerId);
    }

    /**
     * Delegates the call to the {@link CustomerServiceRemote#deleteCustomer(String) CustomerServiceRemote().deleteCustomer} method.
     */
    public static void deleteCustomer(final String customerId) throws RemoveException, CheckException, RemoteException {
        getCustomerService().deleteCustomer(customerId);
    }

    /**
     * Delegates the call to the {@link CustomerServiceRemote#updateCustomer(String) CustomerServiceRemote().updateCustomer} method.
     */
	public static void updateCustomer(CustomerDTO customer) throws UpdateException, CheckException, RemoteException{
		// TODO Auto-generated method stub
	}
	
    /**
     * Delegates the call to the {@link CustomerServiceRemote#findCustomers(String) CustomerServiceRemote().findCustomers} method.
     */
	public static List<CustomerDTO> findCustomers() throws FinderException, RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
    
    // ======================================
    // =            Private methods         =
    // ======================================
    private static CustomerServiceRemote getCustomerService() throws RemoteException {
        try {
            customerServiceRemote = (CustomerServiceRemote) Naming.lookup(ORDER_SERVICE_URL);
        } catch (Exception e) {
            throw new RemoteException("Lookup exception", e);
        }
        return customerServiceRemote;
    }

}
