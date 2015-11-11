package com.yaps.petstore.domain.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.exception.ObjectNotFoundException;
import com.yaps.petstore.persistence.AbstractDataAccessObject;

public final class OrderDAO extends AbstractDataAccessObject{

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final String TABLE = "T_ORDER";
    private static final String COLUMNS = "ID, DATE, FIRSTNAME, LASTNAME, TELEPHONE, STREET1, STREET2, CITY, STATE, ZIPCODE, COUNTRY, CUSTOMER";
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "Order";
	
    // ======================================
    // =           Business methods         =
    // ======================================
//	public Order findByPrimaryKey(String string) throws ObjectNotFoundException{
//		// TODO Auto-generated method stub
//	  return null;	
//	}

//	public List<Order> findAll() throws ObjectNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

    protected String getInsertSqlStatement(final DomainObject object) {
        final Customer customer = (Customer) object;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + customer.getId() + "', '" + customer.getFirstname() + "','" + customer.getLastname() + "', '" + customer.getTelephone() + "', '" + customer.getStreet1() + "', '" + customer.getStreet2() + "', '" + customer.getCity() + "', '" + customer.getState() + "', '" + customer.getZipcode() + "', '" + customer.getCountry() + "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final DomainObject object) {
        final Customer customer = (Customer) object;
        final String sql;
        sql = "UPDATE " + TABLE + " SET FIRSTNAME = '" + customer.getFirstname() + "', LASTNAME = '" + customer.getLastname() + "', TELEPHONE = '" + customer.getTelephone() + "', STREET1 = '" + customer.getStreet1() + "', STREET2 = '" + customer.getStreet2() + "', CITY = '" + customer.getCity() + "', STATE = '" + customer.getState() + "', ZIPCODE = '" + customer.getZipcode() + "', COUNTRY = '" + customer.getCountry() + "' WHERE ID = '" + customer.getId() + "' ";
        return sql;
    }

    protected String getSelectSqlStatement(final String id) {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE ID = '" + id + "' ";
        return sql;
    }

    protected String getSelectAllSqlStatement() {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE;
        return sql;
    }

    protected DomainObject transformResultset2DomainObject(final ResultSet resultSet) throws SQLException {
        final Order order;
        final Customer customer;
        order = new Order(resultSet.getString(1), (Date) resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), customer);
        order.setTelephone(resultSet.getString(4));
        order.setStreet1(resultSet.getString(5));
        order.setStreet2(resultSet.getString(6));
        order.setCity(resultSet.getString(7));
        order.setState(resultSet.getString(8));
        order.setZipcode(resultSet.getString(9));
        order.setCountry(resultSet.getString(10));
        return order;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}
    
	public void insert(Order order) {
		// TODO Auto-generated method stub
	}

	public void update(Order order) {
		// TODO Auto-generated method stub
	}

//	public void remove(String orderId) throws ObjectNotFoundException{
//		// TODO Auto-generated method stub
//	}
//
//	public String getUniqueId() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getUniqueId(String domainClassName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}