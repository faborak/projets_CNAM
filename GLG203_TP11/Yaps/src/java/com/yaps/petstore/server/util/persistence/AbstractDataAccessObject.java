package com.yaps.petstore.server.util.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.yaps.petstore.common.exception.DataAccessException;
import com.yaps.petstore.common.exception.DuplicateKeyException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.util.uidgen.UniqueIdGenerator;

/**
 * This class follows the Data Acces Object (DAO) Design Pattern.
 * It uses JDBC to store object values in a database.
 * Every concrete DAO class should extends this class.
 */
public abstract class AbstractDataAccessObject implements DataAccessConstants {

    // ======================================
    // =             Attributes             =
    // ======================================

    // Used for logging
    private final transient String _cname = this.getClass().getName();
    private static final String sname = AbstractDataAccessObject.class.getName();
    private static final String _datasourceName = "jdbc/petstoreDS11"; // OK pour TomCat, Glassfish en definissant META-INF/context.xml 
    // OK pour JBoss7 : en definissant WEB-INF/jboss-web.xml et standalone/configuration/standalone.xml en plus de META-INF/context.xml 
    
    private static DataSource _datasource;

    // ======================================
    // =            Static Block            =
    // ======================================
    static {
        // Loads the JDBC driver class
        try {
        	// Obtain our environment naming context
        	Context initCtx = new InitialContext();
        	Context envCtx = (Context) initCtx.lookup("java:comp/env");

        	// Look up our data source
        	_datasource = (DataSource)envCtx.lookup(_datasourceName);
        } catch (NamingException e) {
        	// try to get a MysqlDataSource (used runnning DAOTests!)
            com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds 
            	= new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
            ds.setServerName(DB_SERVER_NAME);
            ds.setPortNumber(DB_PORT_NUMBER);
            ds.setDatabaseName(DB_NAME);
            ds.setUser(USER_DB);
            ds.setPassword(PASSWD_DB);
            _datasource = ds;
            // _datasource = null; // DEBUG TODO
            // Trace.throwing(sname, "static", e);
		}
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * This method gets all the attributes for the object from the database.
     *
     * @param id Object identifier to be found in the persistent layer
     * @return DomainObject the object with all its attributs set
     * @throws ObjectNotFoundException is thrown if the object id not found in the persistent layer
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public final DomainObject findByPrimaryKey(final String id) throws ObjectNotFoundException {
    	return this.select(id);
    }
    /**
     * This method gets all the attributes for the object from the database.
     *
     * @param id Object identifier to be found in the persistent layer
     * @return DomainObject the object with all its attributs set
     * @throws ObjectNotFoundException is thrown if the object id not found in the persistent layer
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public final DomainObject select(final String id) throws ObjectNotFoundException {
        final String mname = "select";
        Trace.entering(getCname(), mname, id);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DomainObject object;

        try {
            // Gets a database connection
            connection = getConnection();
            statement = connection.createStatement();

            // Select a Row
            String query = getSelectSqlStatement(id);
            resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
            	// System.out.println("Query returning nothing: " + query);
                throw new ObjectNotFoundException();
            }

            // Set data to current object
            object = transformResultset2DomainObject(resultSet);

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

        Trace.exiting(getCname(), mname, object);
        return object;
    }

    /**
     * This method return all the objects from the database.
     *
     * @return collection of DomainObject
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public final Collection findAll() throws ObjectNotFoundException {
    	return selectAll();
    }
    /**
     * This method return all the objects from the database.
     *
     * @return collection of DomainObject
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public final Collection selectAll() throws ObjectNotFoundException {
        final String mname = "selectAll";
        Trace.entering(getCname(), mname);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        final Collection objects = new ArrayList();

        try {
            // Gets a database connection
            connection = getConnection();
            statement = connection.createStatement();

            // Select a Row
            resultSet = statement.executeQuery(getSelectAllSqlStatement());

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

        Trace.exiting(getCname(), mname, new Integer(objects.size()));
        return objects;
    }

    /**
     * This method inserts an object into the database.
     *
     * @param object Domain object to be inserted
     * @throws DuplicateKeyException is thrown when an identical object is already in the persistent layer
     * @throws DataAccessException   is thrown if there's a persistent problem
     */
    public final void insert(final DomainObject object) throws DuplicateKeyException {
        final String mname = "insert";
        Trace.entering(getCname(), mname, object);

        Connection connection = null;
        Statement statement = null;

        try {
            // Gets a database connection
            connection = getConnection();
            statement = connection.createStatement();

            // Sets the object Id if necessary
            if ( object.getId() == null )
            	object.setId("" + getUniqueId());

            // Inserts a Row
            statement.executeUpdate(getInsertSqlStatement(object));

        } catch (SQLException e) {
            // The data already exists in the database
            if (e.getErrorCode() == DATA_ALREADY_EXIST) {
                throw new DuplicateKeyException();
            } else {
                // A Severe SQL Exception is caught
                displaySqlException(e);
                throw new DataAccessException("Cannot insert data into the database", e);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            // Close
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
    }

    /**
     * This method updates an object in the database.
     *
     * @param object Object to be updated in the database
     * @throws ObjectNotFoundException is thrown if the object id not found in the database
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public final void update(final DomainObject object) throws ObjectNotFoundException {
        final String mname = "update";
        Trace.entering(getCname(), mname, object);

        Connection connection = null;
        Statement statement = null;

        try {
            // Gets a database connection
            connection = getConnection();
            statement = connection.createStatement();

            // Update a Row
            if (statement.executeUpdate(getUpdateSqlStatement(object)) == 0)
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            // A Severe SQL Exception is caught
            displaySqlException(e);
            throw new DataAccessException("Cannot update data into the database", e);
        } finally {
            // Close
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
    }

    /**
     * This method deletes an object from the database.
     *
     * @param id identifier of the object to be deleted
     * @throws ObjectNotFoundException is thrown if the object id not found in the persistent layer
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public final void remove(final String id) throws ObjectNotFoundException {
        final String mname = "remove";
        Trace.entering(getCname(), mname, id);

        Connection connection = null;
        Statement statement = null;

        try {
            // Gets a database connection
            connection = getConnection();
            statement = connection.createStatement();

            // Delete a Row
            if (statement.executeUpdate(getDeleteSqlStatement(id)) == 0)
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            // A Severe SQL Exception is caught
            displaySqlException(e);
            throw new DataAccessException("Cannot remove data into the database", e);

        } finally {
            // Close
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
    }

    /**
     * This method returns a unique identifer generated by the system. 
     *
     * @return a unique identifer
     */
    public final String getUniqueId() {
        return UniqueIdGenerator.getInstance().getUniqueId(getCounterName());
    }

    /**
     * This method returns a unique identifer generated by the system. 
     *
     * @param domainClassName name of a domain class (e.g. Customer, Product, Order, ...
     * @return a unique identifer
     */
    public final String getUniqueId(final String domainClassName) {
        return UniqueIdGenerator.getInstance().getUniqueId(domainClassName);
    }

    protected abstract String getCounterName();
    
    /**
     * This method returns a database connection.
     *
     * @return a JDBC connection to the petstore database
     * @throws SQLException if a SQl expcetion if found
     */
    public static final Connection getConnection() throws SQLException {
    	return(_datasource.getConnection());
    }

    /**
     * This method displays all information of an SQL exception. Its error code, state,
     * sql message and ultimately the stacktrace of the Exception
     *
     * @param e SQLException that you want to display
     */
    public static void displaySqlException(final SQLException e) {
        final String mname = "displaySqlException";

        Trace.severe(sname, mname, "Error code  : " + e.getErrorCode());
        Trace.severe(sname, mname, "SQL state   : " + e.getSQLState());
        Trace.severe(sname, mname, "SQL message : " + e.getMessage());
        Trace.throwing(sname, mname, e);
    }

    /**
     * This method displays all information of an SQL exception and a custom message.
     * Display the sql error code, state, sql message and ultimately the stacktrace of the Exception
     *
     * @param message custom message to display
     * @param e       SQLException that you want to display
     */
    public static void displaySqlException(final String message, final SQLException e) {
        final String mname = "displaySqlException";

        Trace.severe(sname, mname, "Message     : " + message);
        Trace.severe(sname, mname, "Error code  : " + e.getErrorCode());
        Trace.severe(sname, mname, "SQL state   : " + e.getSQLState());
        Trace.severe(sname, mname, "SQL message : " + e.getMessage());
        Trace.throwing(sname, mname, e);
    }

    /**
     * This method returns an insert sql statement
     * This method follows the "Template Method" Design Pattern. It is used by
     * the {@link #insert(DomainObject) insert} method. Every concrete class must redefine this method.
     *
     * @param object DomainObject to insert in the database
     * @return an insert sql statement
     */
    protected abstract String getInsertSqlStatement(DomainObject object);

    /**
     * This method returns a remove sql statement
     * This method follows the "Template Method" Design Pattern. It is used by
     * the {@link #remove(String) remove} method. Every concrete class must redefine this method.
     *
     * @param id of the domain object to remove
     * @return a remove sql statement
     */
    protected abstract String getDeleteSqlStatement(String id);

    /**
     * This method returns an update sql statement
     * This method follows the "Template Method" Design Pattern. It is used by
     * the {@link #update(DomainObject) update} method. Every concrete class must redefine this method.
     *
     * @param object DomainObject to update in the database
     * @return an update sql statement
     */
    protected abstract String getUpdateSqlStatement(DomainObject object);

    /**
     * This method returns a select sql statement
     * This method follows the "Template Method" Design Pattern. It is used by
     * the {@link #select(String) select} method. Every concrete class must redefine this method.
     *
     * @param id of the domain object to select
     * @return a select sql statement
     */
    protected abstract String getSelectSqlStatement(String id);

    /**
     * This method returns a select * sql statement
     * This method follows the "Template Method" Design Pattern. It is used by
     * the {@link #selectAll() selectAll} method. Every concrete class must redefine this method.
     *
     * @return a select * sql statement
     */
    protected abstract String getSelectAllSqlStatement();

    /**
     * This method takes a resultset and transforms it into a Domain Object.
     * This method follows the "Template Method" Design Pattern. It is used by
     * the {@link #select(String) select} and {@link #selectAll() selectAll} method. Every concrete class must redefine this method.
     *
     * @param resultSet JDBC resultset containing all the information for one row
     * @return a DomainObject with its values set
     * @throws SQLException
     */
    protected abstract DomainObject transformResultset2DomainObject(ResultSet resultSet) throws SQLException;
	protected String getCname() {
		return _cname;
	}
}
