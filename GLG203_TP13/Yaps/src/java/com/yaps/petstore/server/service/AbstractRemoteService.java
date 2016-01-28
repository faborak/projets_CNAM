package com.yaps.petstore.server.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.yaps.petstore.common.exception.CheckException;

/**
 * A service is a class that follows the Facade Design Pattern. It gives a set of services
 * to remote or local client. Every service class should extend this class.
 */
public abstract class AbstractRemoteService extends UnicastRemoteObject {

    // ======================================
    // =             Attributes             =
    // ======================================

    // Used for logging
    protected final transient String _cname = this.getClass().getName();

    // ======================================
    // =            Constructors            =
    // ======================================
    protected AbstractRemoteService() throws RemoteException {
    }

    protected void checkId(final String id) throws CheckException {
    	if ( id == null || id.equals("") )
    		throw new CheckException("Id should not be null or empty");    	
    }

	public String getCname() {
		return this.getClass().getName();
	}
}
