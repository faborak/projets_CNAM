package com.yaps.petstore.server.util.persistence;

/**
 * This interface lists all the constants that the system uses to get a Database connection.
 */
public interface DataAccessConstants {

    /**
     * Database error code when we want to insert an id that already exists.
     */
    int DATA_ALREADY_EXIST = 1062;

    /**
     * JDBC Driver class to instanciate.
     */
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    String DB_SERVER_NAME = "localhost";
    int DB_PORT_NUMBER = 3306;
    String DB_NAME = "petstoreDB";

    /**
     * URL of where the database is located.
     */
    String URL_DB = "jdbc:mysql://" + DB_SERVER_NAME + ":" + DB_PORT_NUMBER + "/" + DB_NAME;


    /**
     * Username to access the database.
     */
    String USER_DB = "root";

    /**
     * Password to access the database.
     */
    String PASSWD_DB = "";
}
