package com.yaps.petstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaps.petstore.common.delegate.CustomerDelegateFactory;
import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.logging.Trace;

/**
 * This servlet serves for logon procedure.
 */
public class SignOnServlet extends AbstractServlet {

	
    // ======================================
    // =         Entry point method         =
    // ======================================
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String mname = "service";
        Trace.entering(getCname(), mname);
        
        // Creates the HTTPSession
        Trace.finest(getCname(), mname, "http session create");
       
        String customerId = (String)request.getSession().getAttribute("customerId");
        String password = (String)request.getSession().getAttribute("password");
        CustomerDTO customerDTO;
		try {
			customerDTO = new CustomerDelegateFactory().createCustomerDelegate().authenticate(customerId, password);
		} catch (FinderException | CheckException e) {
			customerDTO = null;
		}
		
		String id = (String)request.getSession().getAttribute("id");
		String password2 = (String)request.getSession().getAttribute("password2");
		if (password2.equals(password) && id != null){
			getServletContext().getRequestDispatcher("/createcustomer.jsp").forward(request, response);
		}
        
        // puts the list of items into the request
        request.getSession().setAttribute("customerDTO", customerDTO);
        
        // Goes to the index page passing the request
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
