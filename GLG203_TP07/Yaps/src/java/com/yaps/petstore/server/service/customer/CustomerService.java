package com.yaps.petstore.server.service.customer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.yaps.petstore.common.delegate.CustomerServiceRemote;
import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.category.Category;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.domain.customer.CustomerDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;
import com.yaps.petstore.server.service.creditcard.CreditCardService;

public class CustomerService extends AbstractRemoteService implements CustomerServiceRemote{

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final CustomerDAO _customerDAO = new CustomerDAO();
    private static final CreditCardService _creditCardService = new CreditCardService();	
	
 // ======================================
    // =            Constructors            =
    // ======================================
	public CustomerService() throws RemoteException {
	}

    // ======================================
    // =      Customer Business methods     =
    // ======================================
	public CustomerDTO createCustomer(final CustomerDTO customerDTO)throws CreateException, CheckException {
        final String mname = "createCustomer";
        Trace.entering(_cname, mname, customerDTO);

        if (customerDTO == null)
            throw new CreateException("Customer object is null");

        // Transforms DTO into domain object
        final Customer customer = new Customer(customerDTO.getId(), customerDTO.getFirstname(), customerDTO.getLastname());
        // Transforms DTO into domain object
        customer.setTelephone(customerDTO.getTelephone());
        customer.setEmail(customerDTO.getEmail());
        // adresse
        customer.setStreet1(customerDTO.getStreet1());
        customer.setStreet2(customerDTO.getStreet2());
        customer.setCity(customerDTO.getCity());
        customer.setState(customerDTO.getState());
        customer.setZipcode(customerDTO.getZipcode());
        customer.setCountry(customerDTO.getCountry());
        // CreditCard
        customer.setCreditCardNumber(customerDTO.getCreditCardNumber());
        customer.setCreditCardExpiryDate(customerDTO.getCreditCardExpiryDate());
        customer.setCreditCardType(customerDTO.getCreditCardType());  

        customer.checkData();
        
        // verify 
        _creditCardService.verifyCreditCard(customer.getCreditCard());
        
        // Creates the object
        _customerDAO.insert(customer);

        // Transforms domain object into DTO
        final CustomerDTO result = transformCustomer2DTO(customer);

        Trace.exiting(_cname, mname, result);
        return result;
	}

	public CustomerDTO findCustomer(final String customerId) throws FinderException,CheckException {
        final String mname = "findCustomer";
        Trace.entering(_cname, mname, customerId);
        
        if (customerId == null || "".equals(customerId)){
            throw new CheckException("customerId problem");
        }
        
        Customer customer = null;
        try {
         customer = (Customer) _customerDAO.findByPrimaryKey(customerId);
        } catch (ObjectNotFoundException e) {
        	throw new ObjectNotFoundException();
        }
        
        // Transforms domain object into DTO
        final CustomerDTO customerDTO = transformCustomer2DTO(customer);

        Trace.exiting(_cname, mname, customerDTO);
        return customerDTO;
	}

	public void deleteCustomer(String customerId) throws RemoveException,CheckException {
		final String mname = "deleteCustomer";
        Trace.entering(_cname, mname, customerId);

    	checkId(customerId);

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

	public void updateCustomer(final CustomerDTO customerDTO) throws UpdateException,CheckException {
		   final String mname = "updateCustomer";
	        Trace.entering(_cname, mname, customerDTO);

	        if (customerDTO == null)
	            throw new UpdateException("Customer object is null");

	    	checkId(customerDTO.getId());
	        Customer customer = new Customer();

	        // Checks if the object exists
	        try {
	            customer = (Customer) _customerDAO.findByPrimaryKey(customerDTO.getId());
	        } catch (FinderException e) {
	            throw new UpdateException("Customer must exist to be updated");
	        }

	        // Transforms DTO into domain object
	        customer.setFirstname(customerDTO.getFirstname());
	        customer.setLastname(customerDTO.getLastname());
	        customer.setTelephone(customerDTO.getTelephone());
	        customer.setEmail(customerDTO.getEmail());
	        // adresse
	        customer.setStreet1(customerDTO.getStreet1());
	        customer.setStreet2(customerDTO.getStreet2());
	        customer.setCity(customerDTO.getCity());
	        customer.setState(customerDTO.getState());
	        customer.setZipcode(customerDTO.getZipcode());
	        customer.setCountry(customerDTO.getCountry());
	        // CreditCard
	        customer.setCreditCardNumber(customerDTO.getCreditCardNumber());
	        customer.setCreditCardExpiryDate(customerDTO.getCreditCardExpiryDate());
	        customer.setCreditCardType(customerDTO.getCreditCardType());   

	        // verify 
	        _creditCardService.verifyCreditCard(customer.getCreditCard());
	        
	        // Updates the object
	        try {
	        	_customerDAO.update(customer);
	        } catch (ObjectNotFoundException e) {
	            throw new UpdateException("Customer must exist to be updated");
	        }
	}

	public Collection findCustomers() throws FinderException, RemoteException {
        final String mname = "findCustomers";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Collection customers = _customerDAO.findAll();

        // Transforms domain objects into DTOs
        final Collection customersDTO = transformCustomers2DTOs(customers);

        Trace.exiting(_cname, mname, new Integer(customersDTO.size()));
        return customersDTO;
	}

    // ======================================
    // =          Private Methods           =
    // ======================================
    private CustomerDTO transformCustomer2DTO(final Customer customer) {
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstname(customer.getFirstname());
        customerDTO.setLastname(customer.getLastname());
        customerDTO.setTelephone(customer.getTelephone());
        customerDTO.setEmail(customer.getEmail());
        // adresse
        customerDTO.setStreet1(customer.getStreet1());
        customerDTO.setStreet2(customer.getStreet2());
        customerDTO.setCity(customer.getCity());
        customerDTO.setState(customer.getState());
        customerDTO.setZipcode(customer.getZipcode());
        customerDTO.setCountry(customer.getCountry());
        // CreditCard
        customerDTO.setCreditCardNumber(customer.getCreditCardNumber());
        customerDTO.setCreditCardExpiryDate(customer.getCreditCardExpiryDate());
        customerDTO.setCreditCardType(customer.getCreditCardType());        
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
     * @param domainClassName name of a domain class (Category, Product or Item)
     * @return a unique identifer
     */
	public String getUniqueId() {
		return _customerDAO.getUniqueId("Customer");
	}

}
