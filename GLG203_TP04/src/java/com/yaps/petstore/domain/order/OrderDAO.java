package com.yaps.petstore.domain.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.persistence.AbstractDataAccessObject;

public final class OrderDAO extends AbstractDataAccessObject {

	// ======================================
	// = Attributes =
	// ======================================
	private static final String TABLE = "T_ORDER";
	private static final String COLUMNS = "ID, ORDERDATE, FIRSTNAME, LASTNAME, STREET1, STREET2, CITY, STATE, ZIPCODE, COUNTRY, CREDITCARDNUMBER, CREDITCARDTYPE, CREDITCARDEXPIRYDATE, CUSTOMER_FK";
	// Used to get a unique id with the UniqueIdGenerator
	private static final String COUNTER_NAME = "Order";

	// ======================================
	// = Business methods =
	// ======================================

	protected String getInsertSqlStatement(final DomainObject object) {
		final Order order = (Order) object;
		final String sql;
		java.sql.Date date_sql = new java.sql.Date(order.getOrderDate().getTime());
		sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('"
				+ order.getId() + "', '" + date_sql + "', '"
				+ order.getFirstname() + "','" + order.getLastname() + "', '"
				+ order.getStreet1() + "', '"
				+ order.getStreet2() + "', '" + order.getCity() + "', '"
				+ order.getState() + "', '" + order.getZipcode() + "', '"
				+ order.getCountry() + "', '" + order.getCreditCardNumber()
				+ "', '" + order.getCreditCardType() + "', '"
				+ order.getCreditCardExpiryDate() + "', '"
				+ order.getCustomer().getId() + "' )";
		return sql;
	}

	protected String getDeleteSqlStatement(final String id) {
		final String sql;
		sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
		return sql;
	}

	protected String getUpdateSqlStatement(final DomainObject object) {
		final Order order = (Order) object;
		final String sql;
		sql = "UPDATE " + TABLE + " SET FIRSTNAME = '" + order.getFirstname()
				+ "', LASTNAME = '" + order.getLastname() + "', STREET1 = '" + order.getStreet1()
				+ "', STREET2 = '" + order.getStreet2() + "', CITY = '"
				+ order.getCity() + "', STATE = '" + order.getState()
				+ "', ZIPCODE = '" + order.getZipcode() + "', COUNTRY = '"
				+ order.getCountry() + "', CREDITCARDNUMBER = '" + order.getCreditCardNumber()
				+ "', CREDITCARDTYPE = '" + order.getCreditCardType() + "', CREDITCARDEXPIRYDATE = '"
				+ order.getCreditCardExpiryDate() + "', CUSTOMER_FK = '" + order.getCustomer().getId()
				+ "' WHERE ID = '" + order.getId() + "' ";
		return sql;
	}

	protected String getSelectSqlStatement(final String id) {
		final String sql;
		sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE ID = '" + id
				+ "' ";
		return sql;
	}

	protected String getSelectAllSqlStatement() {
		final String sql;
		sql = "SELECT " + COLUMNS + " FROM " + TABLE;
		return sql;
	}

	protected DomainObject transformResultset2DomainObject(
			final ResultSet resultSet) throws SQLException {
		final Order order;
		order = new Order(resultSet.getString(1), 
				resultSet.getDate(2), resultSet.getString(3),
				resultSet.getString(4), resultSet.getString(5),
				resultSet.getString(6), resultSet.getString(7),
				resultSet.getString(8), resultSet.getString(9),
				resultSet.getString(10), resultSet.getString(11),
				resultSet.getString(12), resultSet.getString(13),
				new Customer(resultSet.getString(14)));
		return order;
	}

	protected String getCounterName() {
		return COUNTER_NAME;
	}

}