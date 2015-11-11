package com.yaps.petstore.ui.swing.list;

import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.service.CustomerService;

import java.util.Collection;
import java.util.Iterator;

/**
 * This class lists all the customers of the system.
 */
public final class ListCustomerFrame extends AbstractListFrame {

    public ListCustomerFrame() {
        super();
        setTitle("Lists all the customers");
    }

    protected String[] getColumnNames() {

        final String[] columnNames = {"ID", "First Name", "Last Name", "Telephone", "Street1",
                                      "Street2", "City", "State", "Zipcode", "Country"};
        return columnNames;
    }

    protected String[][] getData() throws FinderException {
        final String[][] data;

        final CustomerService service = new CustomerService();
        final Collection customers;

        customers = service.findCustomers();
        data = new String[customers.size()][10];

        int i = 0;
        for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
            final Customer customer = (Customer) iterator.next();
            data[i][0] = customer.getId();
            data[i][1] = customer.getFirstname();
            data[i][2] = customer.getLastname();
            data[i][3] = customer.getTelephone();
            data[i][4] = customer.getStreet1();
            data[i][5] = customer.getStreet2();
            data[i][6] = customer.getCity();
            data[i][7] = customer.getState();
            data[i][8] = customer.getZipcode();
            data[i][9] = customer.getCountry();
            i++;
        }
        return data;
    }
}
