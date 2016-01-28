package com.yaps.petstore.server.domain.customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.yaps.petstore.common.exception.DataAccessException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.util.persistence.AbstractDataAccessObject;

/**
 * This class does all the database access for the class Customer.
 *
 * @see Customer
 */
public final class CustomerDAO extends AbstractDataAccessObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final String TABLE = "T_CUSTOMER";
    private static final String COLUMNS = "ID, FIRSTNAME, LASTNAME, PASSWORD, TELEPHONE, EMAIL, STREET1, STREET2, CITY, STATE, ZIPCODE, COUNTRY, CREDITCARDNUMBER, CREDITCARDTYPE, CREDITCARDEXPIRYDATE";
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "Customer";

    // ======================================
    // =           Business methods         =
    // ======================================
    protected String getInsertSqlStatement(final DomainObject object) {
        final Customer customer = (Customer) object;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + customer.getId() + "', '" + customer.getFirstname() + "','" + customer.getLastname() + "','" + customer.getPassword() + "', '" + customer.getTelephone() + "', '" + customer.getEmail() + "', '" + customer.getStreet1() + "', '" + customer.getStreet2() + "', '" + customer.getCity() + "', '" + customer.getState() + "', '" + customer.getZipcode() + "', '" + customer.getCountry() + "', '" + customer.getCreditCardNumber() + "', '" + customer.getCreditCardType() + "', '" + customer.getCreditCardExpiryDate() + "' )";
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
        sql = "UPDATE " + TABLE + " SET FIRSTNAME = '" + customer.getFirstname() + "', LASTNAME = '" + customer.getLastname() + "', PASSWORD = '" + customer.getPassword() + "', TELEPHONE = '" + customer.getTelephone() + "', EMAIL = '" + customer.getEmail() + "', STREET1 = '" + customer.getStreet1() + "', STREET2 = '" + customer.getStreet2() + "', CITY = '" + customer.getCity() + "', STATE = '" + customer.getState() + "', ZIPCODE = '" + customer.getZipcode() + "', COUNTRY = '" + customer.getCountry() + "', CREDITCARDNUMBER = '" + customer.getCreditCardNumber() + "', CREDITCARDTYPE = '" + customer.getCreditCardType() + "', CREDITCARDEXPIRYDATE = '" + customer.getCreditCardExpiryDate() + "' WHERE ID = '" + customer.getId() + "' ";
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
    
	public Collection findCustomersWithNameLike(String nameLikePattern) throws FinderException{
	       final String mname = "findCustomersWithNameLike";
	        Trace.entering(getCname(), mname, nameLikePattern);

	        Connection connection = null;
	        Statement statement = null;
	        ResultSet resultSet = null;
	        final Collection customers = new ArrayList();

	        try {
	            // Gets a database connection
	            connection = getConnection();
	            statement = connection.createStatement();

	            // Select a Row
	            final String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE LASTNAME like '%" + nameLikePattern + "%' ";
	            resultSet = statement.executeQuery(sql);

	            while (resultSet.next()) {
	                // Set data to the collection
	            	customers.add(transformResultset2DomainObject(resultSet));
	            }

	            if (customers.isEmpty())
	                throw new ObjectNotFoundException();

	        } catch (SQLException e) {
	            // A Severe SQL Exception is caught
	            displaySqlException(e);
	            throw new DataAccessException("Cannot get data from the database: " + e.getMessage(), e);
	        } finally {
	            // Close
	            try {
	                if (resultSet != null) resultSet.close();
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                displaySqlException("Cannot close connection", e);
	                throw new DataAccessException("Cannot close the database connection", e);
	            }
	        }
	        return customers;		
	}

    protected DomainObject transformResultset2DomainObject(final ResultSet resultSet) throws SQLException {
        final Customer customer;
        customer = new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        customer.setPassword(resultSet.getString(4));
        customer.setTelephone(resultSet.getString(5));
        customer.setEmail(resultSet.getString(6));
        customer.setStreet1(resultSet.getString(7));
        customer.setStreet2(resultSet.getString(8));
        customer.setCity(resultSet.getString(9));
        customer.setState(resultSet.getString(10));
        customer.setZipcode(resultSet.getString(11));
        customer.setCountry(resultSet.getString(12));
        customer.setCreditCardNumber(resultSet.getString(13));
        customer.setCreditCardType(resultSet.getString(14));
        customer.setCreditCardExpiryDate(resultSet.getString(15));
        return customer;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}


}
