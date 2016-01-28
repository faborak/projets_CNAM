package com.yaps.petstore.common.delegate;

public class OrderDelegateFactory  {
	private static OrderDelegate instance = null;
	
	public OrderDelegate createOrderDelegate() {
		if ( instance == null ) {
			String s = System.getProperty("useRMI");
			if ( s != null )
				instance = new OrderRemoteDelegate();
			else
				instance = new OrderLocalDelegate();
		}
		return instance;
	}
}
