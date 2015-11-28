package com.yaps.petstore.server.service.order;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.yaps.petstore.common.delegate.OrderServiceRemote;
import com.yaps.petstore.common.dto.OrderDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.domain.order.Order;
import com.yaps.petstore.server.domain.order.OrderDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;

public class OrderService extends AbstractRemoteService implements OrderServiceRemote{

	// ======================================
    // =             Attributes             =
    // ======================================
    private static final OrderDAO _orderDAO = new OrderDAO();
	
	
    // ======================================
    // =            Constructors            =
    // ======================================
	public OrderService() throws RemoteException {
	}

    // ======================================
    // =      Order Business methods        =
    // ======================================
	public OrderDTO createOrder(final OrderDTO orderDTO) throws CreateException,CheckException {
		final String mname = "createOrder";
        Trace.entering(_cname, mname, orderDTO);

        if (orderDTO == null)
            throw new CreateException("Order object is null");
        
        final Order order = new Order(orderDTO.getId(), orderDTO.getOrderDate(), orderDTO.getFirstname(), orderDTO.getLastname(), orderDTO.getStreet1(), orderDTO.getCity(), orderDTO.getZipcode(), orderDTO.getCountry(), new Customer(orderDTO.getCustomerId()));

        order.checkData();
        // Creates the object
        _orderDAO.insert(order);

        // Transforms domain object into DTO
        final OrderDTO result = transformOrder2DTO(order);

        Trace.exiting(_cname, mname, result);
        return result;
	}

	public OrderDTO findOrder(final String orderId) throws FinderException, CheckException {
        final String mname = "findOrder";
        Trace.entering(_cname, mname, orderId);

        final Order order = (Order) _orderDAO.findByPrimaryKey(orderId);

        // Transforms domain object into DTO
        final OrderDTO orderDTO = transformOrder2DTO(order);

        Trace.exiting(_cname, mname, orderDTO);
        return orderDTO;
	}

	public void deleteOrder(final String orderId) throws RemoveException,CheckException {
		final String mname = "deleteOrder";
        Trace.entering(_cname, mname, orderId);

    	checkId(orderId);

        // Checks if the object exists
        try {
            _orderDAO.findByPrimaryKey(orderId);
        } catch (FinderException e) {
            throw new RemoveException("Order must exist to be deleted");
        }

        // Deletes the object
        try {
        	_orderDAO.remove(orderId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Order must exist to be deleted");
        }
	}
	
	public void updateOrder(final OrderDTO orderDTO) throws UpdateException, CheckException {
        final String mname = "updateOrder";
        Trace.entering(_cname, mname, orderDTO);

        if (orderDTO == null)
            throw new UpdateException("Order object is null");

    	checkId(orderDTO.getId());
        Order order = new Order();

        // Checks if the object exists
        try {
            order = (Order) _orderDAO.findByPrimaryKey(orderDTO.getId());
        } catch (FinderException e) {
            throw new UpdateException("Order must exist to be updated");
        }

        // Transforms DTO into domain object
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setFirstname(order.getFirstname());
        orderDTO.setLastname(order.getLastname());
        orderDTO.setStreet1(order.getStreet1());
        orderDTO.setCity(order.getCity());
        orderDTO.setZipcode(order.getZipcode());
        orderDTO.setCountry(order.getCountry());
        orderDTO.setCustomerId(order.getCustomer().getId());

        // Updates the object
        try {
        	_orderDAO.update(order);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Order must exist to be updated");
        }
    }

    public Collection findOrderss() throws FinderException {
        final String mname = "findOrderss";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Collection orders = _orderDAO.findAll();

        // Transforms domain objects into DTOs
        final Collection ordersDTO = transformOrders2DTOs(orders);

        Trace.exiting(_cname, mname, new Integer(ordersDTO.size()));
        return ordersDTO;
    }

    // ======================================
    // =          Private Methods           =
    // ======================================
    private OrderDTO transformOrder2DTO(final Order order) {
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setFirstname(order.getFirstname());
        orderDTO.setLastname(order.getLastname());
        orderDTO.setStreet1(order.getStreet1());
        orderDTO.setCity(order.getCity());
        orderDTO.setZipcode(order.getZipcode());
        orderDTO.setCountry(order.getCountry());
        // Retreives the data for the linked object
        // Finds the linked object
        Customer customer = null;
        try {
            customer = (Customer)_orderDAO.findByPrimaryKey(order.getCustomer().getId());
        } catch (FinderException e) {
        	// No exception can occur
        }
        orderDTO.setCustomerId(customer.getId());
        return orderDTO;
    }

    private Collection transformOrders2DTOs(final Collection orders) {
        final Collection ordersDTO = new ArrayList();
        for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
            final Order order = (Order) iterator.next();
            ordersDTO.add(transformOrder2DTO(order));
        }
        return ordersDTO;
    }
	
    /**
     * This method returns a unique identifer generated by the system. 
     *
     * @param domainClassName name of a domain class (Order)
     * @return a unique identifer
     */
	public String getUniqueId(final String domainClassName) {
		return _orderDAO.getUniqueId(domainClassName);
	}

}
