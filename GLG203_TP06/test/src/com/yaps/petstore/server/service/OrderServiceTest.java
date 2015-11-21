package com.yaps.petstore.server.service;

import com.yaps.petstore.AbstractTestCase;
import com.yaps.petstore.common.dto.*;
import com.yaps.petstore.common.exception.*;
import com.yaps.petstore.server.service.catalog.CatalogService;
import com.yaps.petstore.server.service.customer.CustomerService;
import com.yaps.petstore.server.service.order.OrderService;
import junit.framework.TestSuite;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class tests the CatalogService class
 */
public final class OrderServiceTest extends AbstractTestCase {

    public OrderServiceTest(final String s) {
        super(s);
    }

    public static TestSuite suite() {
        return new TestSuite(OrderServiceTest.class);
    }

    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This test tries to find an object with a invalid identifier.
     */
    public void testServiceFindOrderWithInvalidValues() throws Exception {
        final OrderService service = getOrderService();

        // Finds an object with a unknown identifier
        final String id = getUniqueId();
        try {
            service.findOrder(id);
            fail("Object with unknonw id should not be found");
        } catch (ObjectNotFoundException e) {
        }

        // Finds an object with an empty identifier
        try {
            service.findOrder(new String());
            fail("Object with empty id should not be found");
        } catch (ObjectNotFoundException e) {
        }

        // Finds an object with a null identifier
        try {
            service.findOrder(null);
            fail("Object with null id should not be found");
        } catch (ObjectNotFoundException e) {
        }
    }

    /**
     * This method ensures that creating an object works. It first finds the object,
     * makes sure it doesn't exist, creates it and checks it then exists.
     */
    public void testServiceCreateOrder() throws Exception {
        final String id = getUniqueId();
        OrderDTO orderDTO = null;

        // Creates an object
        final String orderId = createOrder(id);

        // Ensures that the object exists
        try {
            orderDTO = findOrder(orderId);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkOrder(orderDTO, id);

        // Cleans the test environment
        deleteOrder(orderId);

        try {
            findOrder(orderId);
            fail("Object has been deleted it shouldn't be found");
        } catch (ObjectNotFoundException e) {
        }
    }

    /**
     * This test tries to create an object with a invalid values.
     */
    public void testServiceCreateOrderWithInvalidValues() throws Exception {
        final OrderService service = getOrderService();
        OrderDTO orderDTO;

        // Creates an object with a null parameter
        try {
            service.createOrder(null);
            fail("Object with null parameter should not be created");
        } catch (CreateException e) {
        }

        // Creates an object with empty values
        try {
            orderDTO = new OrderDTO(new String(), new String(), new String(), new String(), new String(), new String());
            service.createOrder(orderDTO);
            fail("Object with empty values should not be created");
        } catch (CheckException e) {
        }

        // Creates an object with null values
        try {
            orderDTO = new OrderDTO(null, null, null, null, null, null);
            service.createOrder(orderDTO);
            fail("Object with null values should not be created");
        } catch (CheckException e) {
        }
    }

    //==================================
    //=          Private Methods       =
    //==================================
    private OrderService getOrderService() throws RemoteException {
        return new OrderService();
    }

    private CustomerService getCustomerService() throws RemoteException{
        return new CustomerService();
    }

    private CatalogService getCatalogService() throws RemoteException{
        return new CatalogService();
    }

    private OrderDTO findOrder(final String id) throws FinderException, CheckException, RemoteException {
        final OrderDTO orderDTO = getOrderService().findOrder(id);
        return orderDTO;
    }

    // Creates a category first, then a product linked to this category and an item linked to the product
    // Creates a Customer and an order linked to the customer
    // Creates an orderLine linked to the order and the item
    private String createOrder(final String id) throws CreateException, CheckException, RemoteException {
        // Create Category
    	final String categoryId = getCatalogService().getUniqueId("Category");
        final CategoryDTO categoryDTO = new CategoryDTO("cat" + categoryId, "name" + categoryId, "description" + categoryId);
        getCatalogService().createCategory(categoryDTO);
        // Create Product
        final String productId = getCatalogService().getUniqueId("Product");
        final ProductDTO productDTO = new ProductDTO("prod" + productId, "name" + productId, "description" + productId);
        productDTO.setCategoryId("cat" + categoryId);
        getCatalogService().createProduct(productDTO);
        // Create Item
        final String itemId = getCatalogService().getUniqueId("Item");
        final ItemDTO itemDTO = new ItemDTO("item" + itemId, "name" + itemId, Double.parseDouble(itemId));
        itemDTO.setProductId("prod" + productId);
        getCatalogService().createItem(itemDTO);

        // Create Customer
        String customerId = getCustomerService().getUniqueId();
        final CustomerDTO customerDTO = new CustomerDTO("custo" + customerId, "firstname" + id, "lastname" + id);
        getCustomerService().createCustomer(customerDTO);

        // Create Order
        final OrderDTO orderDTO = new OrderDTO("firstname" + id, "lastname" + id, "street1" + id, "city" + id, "zip" + id, "country" + id);
        orderDTO.setStreet2("street2" + id);
        orderDTO.setCreditCardExpiryDate("ccexp" + id);
        orderDTO.setCreditCardNumber("ccnum" + id);
        orderDTO.setCreditCardType("cctyp" + id);
        orderDTO.setState("state" + id);
        orderDTO.setCustomerId(customerDTO.getId());

        // Creates two order lines
        final OrderLineDTO oi1 = new OrderLineDTO(Integer.parseInt(id), itemDTO.getUnitCost());
        oi1.setItemId(itemDTO.getId());
        final OrderLineDTO oi2 = new OrderLineDTO(Integer.parseInt(id), itemDTO.getUnitCost());
        oi2.setItemId(itemDTO.getId());
        final Collection orderLines = new ArrayList();
        orderLines.add(oi1);
        orderLines.add(oi2);
        orderDTO.setOrderLines(orderLines);

        final OrderDTO result = getOrderService().createOrder(orderDTO);
        return result.getId();
    }

    private void deleteOrder(final String orderId) throws RemoveException, CheckException, RemoteException, FinderException {
    	final OrderDTO orderDTO = getOrderService().findOrder(orderId);
    	getOrderService().deleteOrder(orderId);
    	getCustomerService().deleteCustomer(orderDTO.getCustomerId());
    }

    private void checkOrder(final OrderDTO orderDTO, final String id) {
        assertEquals("firstname", "firstname" + id, orderDTO.getFirstname());
        assertEquals("lastname", "lastname" + id, orderDTO.getLastname());
        assertEquals("city", "city" + id, orderDTO.getCity());
        assertEquals("country", "country" + id, orderDTO.getCountry());
        assertEquals("state", "state" + id, orderDTO.getState());
        assertEquals("street1", "street1" + id, orderDTO.getStreet1());
        assertEquals("street2", "street2" + id, orderDTO.getStreet2());
        assertEquals("zipcode", "zip" + id, orderDTO.getZipcode());
        assertEquals("CreditCardExpiryDate", "ccexp" + id, orderDTO.getCreditCardExpiryDate());
        assertEquals("CreditCardNumber", "ccnum" + id, orderDTO.getCreditCardNumber());
        assertEquals("CreditCardType", "cctyp" + id, orderDTO.getCreditCardType());
        assertEquals("order items", 2, orderDTO.getOrderLines().size());
        OrderLineDTO firstOrderLineDTO = ((OrderLineDTO)orderDTO.getOrderLines().iterator().next());
        assertEquals("First OrderLine quantity", Integer.parseInt(id), firstOrderLineDTO.getQuantity());
    }

    private String getUniqueId() throws RemoteException {
		return getOrderService().getUniqueId("Order");
	}
}
