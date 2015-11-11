package com.yaps.petstore.service;

import java.util.List;

import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.ObjectNotFoundException;
import com.yaps.petstore.exception.UpdateException;

public class CustomerService {

	public Customer findCustomer(String id) throws ObjectNotFoundException, CheckException{
		// TODO Auto-generated method stub
		return null ;
	}

	public Customer createCustomer(Customer customer) throws CreateException, CheckException{
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomer(Object object) throws UpdateException, CheckException{
		// TODO Auto-generated method stub
		
	}

	public List<Customer> findCustomers() throws ObjectNotFoundException{
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteCustomer(String string) {
		// TODO Auto-generated method stub
		
	}

	public String getUniqueId() {
		// TODO Auto-generated method stub
		return null;
	}

}
