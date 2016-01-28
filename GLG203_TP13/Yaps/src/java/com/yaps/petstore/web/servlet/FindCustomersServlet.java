package com.yaps.petstore.web.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaps.petstore.common.delegate.CustomerDelegateFactory;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.logging.Trace;

/**
 * This servlet returns the list of all items.
 */
public class FindCustomersServlet extends AbstractServlet {

    // ======================================
    // =         Entry point method         =
    // ======================================
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String mname = "service";
        Trace.entering(getCname(), mname);

        final Collection customersDTO;
        String lastNameFirstChars = request.getParameter("lastNameFirstChars");

        try {
            // Gets the customers for a name
            Trace.finest(getCname(), mname, "lastName First Chars Pattern=" + lastNameFirstChars);
            if (lastNameFirstChars == null || lastNameFirstChars.equals("")){
            	customersDTO = new CustomerDelegateFactory().createCustomerDelegate().findCustomers();
            } else {
            	customersDTO = new CustomerDelegateFactory().createCustomerDelegate().findCustomersWithNameStartingWith(lastNameFirstChars);
            }

            // puts the list of items into the request
            request.setAttribute("customersDTO", customersDTO);

            // Goes to the items page passing the request
            getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);

        } catch (ObjectNotFoundException e) {
            getServletContext().getRequestDispatcher("/error.jsp?exception=No customers found for letters " + lastNameFirstChars).forward(request, response);
        } catch (Exception e) {
            Trace.throwing(getCname(), mname, e);
            getServletContext().getRequestDispatcher("/error.jsp?exception=Cannot get the customer list").forward(request, response);
        }
    }
}
