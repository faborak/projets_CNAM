package com.barkbank.verifier;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

import com.yaps.petstore.common.logging.Trace;

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
	protected void service(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final String mname = "service";
		Trace.entering(_cname, mname);

		Document document = DocumentHelper.createDocument();
		String xmlEncoded = request.getParameter("SERVLET_PARAMETER");
		String xmlDecoded = URLDecoder.decode(xmlEncoded, "UTF-8");
		InputStream in = new ByteArrayInputStream(xmlDecoded.getBytes());
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		String creditCardNumber = document.selectSingleNode(
				"/CreditCard/CreditCardNumber").getText();
		String creditCardExpiryDate = document.selectSingleNode(
				"/CreditCard/CreditCardType").getText();
		String month = document.selectSingleNode("/CreditCard/CreditCardExpiryDate").getText().valueOf("Month");
		String year = document.selectSingleNode("/CreditCard/CreditCardExpiryDate").getText().valueOf("Year");
		String status = VerificationAlgorithm.verify(creditCardNumber,creditCardExpiryDate, month, year);

		document.addElement("status").addText(status);

		// String xmlEncoded2 = URLEncoder.encode(document.asXML(), "UTF-8");

		response.setContentType("text/xml");
		ServletOutputStream out = response.getOutputStream();
		// xmlencoded = request.getParameter("param"); //La servlet de la
		// BarkBank intercepte le flux en entrée
		// if ((xmlencoded != null) && (!"".equals(xmlencoded))) { /*traitement
		// si il y a quelque chose */ }
		out.print(document.asXML());
	}
}
