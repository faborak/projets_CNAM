package com.yaps.petstore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.yaps.petstore.domain.category.Category;
import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.domain.customer.CustomerDAO;
import com.yaps.petstore.domain.order.OrderDAO;
import com.yaps.petstore.domain.orderline.OrderLineDAO;
import com.yaps.petstore.domain.product.Product;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.ObjectNotFoundException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;

/**
 * This class is a facade for all customer services.
 */
public class CustomerService {
	private static final CustomerDAO _customerDAO = new CustomerDAO();
    private static final OrderDAO _orderDAO = new OrderDAO();
    private static final OrderLineDAO _orderLineDAO = new OrderLineDAO();

    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Given a Customer object, this method creates a Customer.
     *
     * @param customer cannot be null.
     * @return the created Order
     * @throws CreateException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data is found
     */
	public Customer createCustomer(Customer customer) throws CreateException, CheckException{
		if (customer == null)
            throw new CreateException("Customer object is null");
		
		customer.checkData();
		// Creates the object
		_customerDAO.insert(customer);
		
		return customer;
	}

    /**
     * Given an id this method uses the Customer domain object to load all the data of this
     * object.
     *
     * @param customerId identifier
     * @return Customer
     * @throws ObjectNotFoundException is thrown if no object with this given id is found
     * @throws FinderException         is thrown if a DomainException is caught
     *                                 or a system failure is occurs
     * @throws CheckException          is thrown if a invalid data is found
     */
	public Customer findCustomer(String customerId) throws FinderException, CheckException{

		// Finds the object
		checkId(customerId);
        
		return (Customer) _customerDAO.findByPrimaryKey(customerId);
	}

    /**
     * Given an id, this method finds an Order domain object and then calls its deletion
     * method.
     *
     * @param orderId identifier
     * @throws RemoveException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data is found
     */
	public void deleteCustomer(String customerId) throws RemoveException, CheckException {
		final Customer customer = new Customer();
		
		// Checks if the object exists
        try {
        	_customerDAO.findByPrimaryKey(customerId);
        } catch (FinderException e) {
            throw new RemoveException("Customer must exist to be deleted");
        }
		
        // Deletes the object
        try {
        	_customerDAO.remove(customerId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Customer must exist to be deleted");
        }
        
	}

    /**
     * Given a Customer object, this method updates an Customer.
     *
     * @param item cannot be null.
     * @throws UpdateException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if a invalid data is found
     */
	public void updateCustomer(Customer customer) throws UpdateException, CheckException{
        if (customer == null)
            throw new UpdateException("Customer object is null");
    	checkId(customer.getId());
    	customer.checkData();
        // Updates the object
        try {
        	_customerDAO.update(customer);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Customer must exist to be updated");
        }
	}

    /**
     * This method return all the customers from the system. It uses the Customer domain object
     * to get the data.
     *
     * @return a collection of Customer
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws FinderException         is thrown if a DomainException is caught
     *                                 or a system failure is occurs
     */
	public Collection findCustomers() throws FinderException{
		final Collection result = new ArrayList();

        // Finds all the objects
        final Collection customers = _customerDAO.selectAll();

        // For each object
        for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
            final Customer customer = (Customer) iterator.next();

            // Adds the object to the collection
            result.add(customer);
        }

        return result;
	}


    /**
     * This method returns a unique identifer generated by the system. 
     *
     * @return a unique identifer
     */
	public String getUniqueId() {
		return _customerDAO.getUniqueId("Customer");
	}

    private void checkId(final String id) throws CheckException {
    	if ( id == null || id.equals("") )
    		throw new CheckException("Id should not be null or empty");    	
    }
	
}
