package com.yaps.petstore.domain.orderline;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.item.Item;
import com.yaps.petstore.domain.order.Order;
import com.yaps.petstore.exception.DataAccessException;
import com.yaps.petstore.exception.ObjectNotFoundException;
import com.yaps.petstore.logging.Trace;
import com.yaps.petstore.persistence.AbstractDataAccessObject;

public class OrderLineDAO extends AbstractDataAccessObject {

	// ======================================
	// = Attributes =
	// ======================================
	private static final String TABLE = "T_ORDER";
	private static final String COLUMNS = "ID, QUANTITY, UNITCOST, ORDER_FK, ITEM_FK";
	// Used to get a unique id with the UniqueIdGenerator
	private static final String COUNTER_NAME = "OrderLine";

	// ======================================
	// = Business methods =
	// ======================================
	protected String getInsertSqlStatement(final DomainObject object) {
		final OrderLine orderLine = (OrderLine) object;
		final String sql;
		sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('"
				+ orderLine.getId() + "', '" + orderLine.getQuantity() + "', '"
				+ orderLine.getUnitCost() + "','"
				+ orderLine.getOrder().getId() + "', '"
				+ orderLine.getItem().getId() + "' )";
		return sql;
	}

	protected String getDeleteSqlStatement(final String id) {
		final String sql;
		sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
		return sql;
	}

	protected String getUpdateSqlStatement(final DomainObject object) {
		final OrderLine orderLine = (OrderLine) object;
		final String sql;
		sql = "UPDATE " + TABLE + " SET ID = '" + orderLine.getId()
				+ "', QUANTITY = '" + orderLine.getQuantity()
				+ "', UNITCOST = '" + orderLine.getUnitCost()
				+ "', ORDER_FK = '" + orderLine.getOrder().getId()
				+ "', ITEM_FK = '" + orderLine.getItem().getId()
				+ "' WHERE ID = '" + orderLine.getId() + "' ";
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
		final OrderLine orderLine;
		orderLine = new OrderLine(resultSet.getString(1),
				Integer.parseInt(resultSet.getString(2)),
				resultSet.getDouble(3), new Order(resultSet.getString(4)),
				new Item(resultSet.getString(5)));
		return orderLine;
	}

	protected String getCounterName() {
		return COUNTER_NAME;
	}
	
    /**
     * This method return all the objects from the database.
     *
     * @return collection of DomainObject
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
	public Collection<OrderLine> findAll(String orderId) throws ObjectNotFoundException{
		return selectAll(orderId);
	}
	
    /**
     * This method returns a select * sql statement
     * This method follows the "Template Method" Design Pattern. It is used by
     * the {@link #selectAll() selectAll} method. Every concrete class must redefine this method.
     *
     * @return a select * sql statement
     */
	protected String getSelectAllSqlStatement(String orderId) {
		final String sql;
		sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE ORDER_FK = '" + orderId + "' ";
		return sql;
	}
	
	 /**
     * This method return all the objects from the database.
     *
     * @return collection of DomainObject
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public final Collection selectAll(String orderId) throws ObjectNotFoundException {
        final String mname = "selectAll";
        Trace.entering(_cname, mname);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        final Collection objects = new ArrayList();

        try {
            // Gets a database connection
            connection = getConnection();
            statement = connection.createStatement();

            // Select a Row
            resultSet = statement.executeQuery(getSelectAllSqlStatement(orderId));

            while (resultSet.next()) {
                // Set data to the collection
                objects.add(transformResultset2DomainObject(resultSet));
            }

            if (objects.isEmpty())
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

        Trace.exiting(_cname, mname, new Integer(objects.size()));
        return objects;
    }



}
