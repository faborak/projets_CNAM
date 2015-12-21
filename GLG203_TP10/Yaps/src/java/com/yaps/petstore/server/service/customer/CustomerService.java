package com.yaps.petstore.server.service.customer;

import com.yaps.petstore.common.delegate.CustomerServiceRemote;
import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.*;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.domain.customer.CustomerDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;
import com.yaps.petstore.server.service.creditcard.CreditCardService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is a facade for all customer services.
 */
public final class CustomerService extends AbstractRemoteService implements CustomerServiceRemote {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final CustomerDAO _dao = new CustomerDAO();

    // ======================================
    // =            Constructors            =
    // ======================================
    public CustomerService() throws RemoteException {
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public CustomerDTO createCustomer(final CustomerDTO customerDTO) throws CreateException, CheckException {
        final String mname = "createCustomer";
        Trace.entering(getCname(), mname, customerDTO);

        if (customerDTO == null)
            throw new CreateException("Customer object is null");

        // Transforms DTO into domain object
        final Customer customer = new Customer(customerDTO.getId(), customerDTO.getFirstname(), customerDTO.getLastname());
        customer.setCity(customerDTO.getCity());
        customer.setCountry(customerDTO.getCountry());
        customer.setState(customerDTO.getState());
        customer.setStreet1(customerDTO.getStreet1());
        customer.setStreet2(customerDTO.getStreet2());
        customer.setTelephone(customerDTO.getTelephone());
        customer.setZipcode(customerDTO.getZipcode());
        customer.setEmail(customerDTO.getEmail());
        customer.setCreditCardExpiryDate(customerDTO.getCreditCardExpiryDate());
        customer.setCreditCardNumber(customerDTO.getCreditCardNumber());
        customer.setCreditCardType(customerDTO.getCreditCardType());

        customer.checkData();

        // Checks if the credit card is valid
        getCreditCardService().verifyCreditCard(customer.getCreditCard());

        // Creates the object
        _dao.insert(customer);

        // Transforms domain object into DTO
        final CustomerDTO result = transformCustomer2DTO(customer);

        Trace.exiting(getCname(), mname, result);
        return result;
    }

    public CustomerDTO findCustomer(final String customerId) throws FinderException, CheckException {
        final String mname = "findCustomer";
        Trace.entering(getCname(), mname, customerId);

    	checkId(customerId);
        // Finds the object
        final Customer customer = (Customer)_dao.findByPrimaryKey(customerId);

        // Transforms domain object into DTO
        final CustomerDTO customerDTO = transformCustomer2DTO(customer);

        Trace.exiting(getCname(), mname, customerDTO);
        return customerDTO;
    }

    public void deleteCustomer(final String customerId) throws RemoveException, CheckException {
        final String mname = "deleteCustomer";
        Trace.entering(getCname(), mname, customerId);

    	checkId(customerId);

        // Checks if the object exists
        try {
        	_dao.findByPrimaryKey(customerId);
        } catch (FinderException e) {
            throw new RemoveException("Customer must exist to be deleted");
        }

        // Deletes the object
        try {
        	_dao.remove(customerId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Customer must exist to be deleted");
        }
    }

    public void updateCustomer(final CustomerDTO customerDTO) throws UpdateException, CheckException {
        final String mname = "updateCustomer";
        Trace.entering(getCname(), mname, customerDTO);

        if (customerDTO == null)
            throw new UpdateException("Customer object is null");

    	checkId(customerDTO.getId());

    	final Customer customer;

        // Checks if the object exists
        try {
        	customer = (Customer)_dao.findByPrimaryKey(customerDTO.getId());
        } catch (FinderException e) {
            throw new UpdateException("Customer must exist to be updated");
        }

        // Transforms DTO into domain object
        customer.setCity(customerDTO.getCity());
        customer.setCountry(customerDTO.getCountry());
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setState(customerDTO.getState());
        customer.setStreet1(customerDTO.getStreet1());
        customer.setStreet2(customerDTO.getStreet2());
        customer.setTelephone(customerDTO.getTelephone());
        customer.setZipcode(customerDTO.getZipcode());
        customer.setEmail(customerDTO.getEmail());
        customer.setCreditCardExpiryDate(customerDTO.getCreditCardExpiryDate());
        customer.setCreditCardNumber(customerDTO.getCreditCardNumber());
        customer.setCreditCardType(customerDTO.getCreditCardType());

        customer.checkData();
        
        // Checks if the credit card is valid
        getCreditCardService().verifyCreditCard(customer.getCreditCard());

        // Updates the object
        try {
        	_dao.update(customer);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Customer must exist to be updated");
        }
    }

    public Collection findCustomers() throws FinderException {
        final String mname = "findCustomers";
        Trace.entering(getCname(), mname);

        // Finds all the objects
        final Collection customers = _dao.selectAll();

        // Transforms domain objects into DTOs
        final Collection customersDTO = transformCustomers2DTOs(customers);

        Trace.exiting(getCname(), mname, new Integer(customersDTO.size()));
        return customersDTO;
    }
    
	public CustomerDTO authenticate(String customerId, String password) throws FinderException, CheckException, RemoteException {
		 final String mname = "authenticate";
	        Trace.entering(getCname(), mname, customerId);

	    	checkId(customerId);
	    	
	        // Finds the object
	        final Customer customer = (Customer)_dao.findByPrimaryKey(customerId);

	        try {
	        customer.matchPassword(password);
	        } catch (CheckException e){
	        	throw new CheckException("invalid password");
	        }
	        
	        // Transforms domain object into DTO
	        final CustomerDTO customerDTO = transformCustomer2DTO(customer);

	        Trace.exiting(getCname(), mname, customerDTO);
	        return customerDTO;
	}

    // ======================================
    // =          Private Methods           =
    // ======================================
    private CustomerDTO transformCustomer2DTO(final Customer customer) {
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setCity(customer.getCity());
        customerDTO.setCountry(customer.getCountry());
        customerDTO.setFirstname(customer.getFirstname());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setLastname(customer.getLastname());
        customerDTO.setState(customer.getState());
        customerDTO.setStreet1(customer.getStreet1());
        customerDTO.setStreet2(customer.getStreet2());
        customerDTO.setTelephone(customer.getTelephone());
        customerDTO.setZipcode(customer.getZipcode());
        customerDTO.setCreditCardNumber(customer.getCreditCardNumber());
        customerDTO.setCreditCardType(customer.getCreditCardType());
        customerDTO.setCreditCardExpiryDate(customer.getCreditCardExpiryDate());
        return customerDTO;
    }

    private Collection transformCustomers2DTOs(final Collection customers) {
        final Collection customersDTO = new ArrayList();
        for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
            final Customer customer = (Customer) iterator.next();
            customersDTO.add(transformCustomer2DTO(customer));
        }
        return customersDTO;
    }

    /**
     * This method returns a unique identifer generated by the system. 
     *
     * @return a unique identifer
     */
    public final String getUniqueId() {
        return _dao.getUniqueId();
    }

    private CreditCardService getCreditCardService() {
        return new CreditCardService();
    }

}
