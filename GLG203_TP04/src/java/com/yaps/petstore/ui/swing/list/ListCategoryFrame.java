package com.yaps.petstore.ui.swing.list;

import com.yaps.petstore.domain.category.Category;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.service.CatalogService;

import java.util.Collection;
import java.util.Iterator;

/**
 * This class lists all the categories of the system.
 */
public final class ListCategoryFrame extends AbstractListFrame {

    public ListCategoryFrame() {
        super();
        setTitle("Lists all the categories");
    }

    protected String[] getColumnNames() {

        final String[] columnNames = {"ID", "Name", "Description"};

        return columnNames;
    }

    protected String[][] getData() throws FinderException {
        final String[][] data;

        final CatalogService service = new CatalogService();
        final Collection categories;

        categories = service.findCategories();
        data = new String[categories.size()][3];

        int i = 0;
        for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
            final Category category = (Category) iterator.next();
            data[i][0] = category.getId();
            data[i][1] = category.getName();
            data[i][2] = category.getDescription();
            i++;
        }
        return data;
    }
}
