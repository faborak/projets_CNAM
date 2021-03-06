package com.yaps.petstore.domain.category;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.persistence.AbstractDataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class does all the database access for the class Category.
 *
 * @see Category
 */
public final class CategoryDAO extends AbstractDataAccessObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final String TABLE = "T_CATEGORY";
    private static final String COLUMNS = "ID, NAME, DESCRIPTION";
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "Category";

    // ======================================
    // =           Business methods         =
    // ======================================
    protected String getInsertSqlStatement(final DomainObject object) {
        final Category category = (Category) object;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + category.getId() + "', '" + category.getName() + "', '" + category.getDescription() + "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final DomainObject object) {
        final Category category = (Category) object;
        final String sql;
        sql = "UPDATE " + TABLE + " SET NAME = '" + category.getName() + "', DESCRIPTION = '" + category.getDescription() + "' WHERE ID = '" + category.getId() + "' ";
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
        final Category category;
        category = new Category(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        return category;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}
}
