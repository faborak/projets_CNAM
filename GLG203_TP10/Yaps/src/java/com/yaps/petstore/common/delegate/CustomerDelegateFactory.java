package com.yaps.petstore.common.delegate;

public class CustomerDelegateFactory  {
	private static CustomerDelegate instance = null;
	
	public CustomerDelegate createCustomerDelegate() {
		if ( instance == null ) {
			String s = System.getProperty("donotuseRMI");
			if ( s == null )
				instance = new CustomerRemoteDelegate();
			else
				instance = new CustomerLocalDelegate();
		}
		return instance;
	}
}
