package com.barkbank.verifier;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * This servlet verify a credit card.
 */
public class VerifyCreditCardServlet extends HttpServlet {

	// ======================================
	// = Attributes =
	// ======================================
	// Used for logging
	private final transient String _cname = this.getClass().getName();

	// ======================================
	// = Entry point method =
	// ======================================
	protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

//		 final PrintWriter tout = response.getWriter();

//         tout.println("entree");
		
		String xmlEncoded = request.getParameter("param");
		//tout.println("xmlEncoded : "+  xmlEncoded);
		if ((xmlEncoded != null) && (!"".equals(xmlEncoded))) {
			try {
			String xmlDecoded = URLDecoder.decode(xmlEncoded, "UTF-8");
			//tout.println("xmlDecoded : "+  xmlDecoded);
			InputStream in = new ByteArrayInputStream(xmlDecoded.getBytes());
			SAXReader reader = new SAXReader();
			
			Document document = DocumentHelper.createDocument();
			
			document = reader.read(in);
	
			String creditCardNumber = document.selectSingleNode("/CreditCard/CardNumber").getText();
			String cardType = document.selectSingleNode("/CreditCard/CardType").getText();
			String month = document.selectSingleNode("/CreditCard/ExpiryDate").valueOf("@Month");
			String year = document.selectSingleNode("/CreditCard/ExpiryDate").valueOf("@Year");
			String status = VerificationAlgorithm.verify(creditCardNumber,cardType, year, month);
			//tout.println("creditCardNumber : "+  creditCardNumber+", CardType : "+  cardType+", month : "+  month+", year : "+  year+", status : "+  status); 
	
	    	Document documentRetour = DocumentHelper.createDocument();
	        // Construit un document xml
	         Element rootRetour = documentRetour.addElement("CreditCard").addAttribute("Status", status) ;
	
	         rootRetour.addElement("CardNumber").addText(creditCardNumber);  
	         rootRetour.addElement("CardType").addText(cardType); 
	         Element CreditCardExpiryDate = rootRetour.addElement("ExpiryDate");
	         CreditCardExpiryDate.addAttribute("Month", month)
	                             .addAttribute("Year", year);
	        // tout.println("documentRetour : "+  documentRetour); 
	         
			response.setContentType("text/xml");
			ServletOutputStream out = response.getOutputStream();
			out.print(documentRetour.asXML());
			}catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}
}
