package com.yaps.petstore.server.domain;

import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.yaps.petstore.common.exception.CheckException;

/**
 * This class encapsulates all the data for a credit card.
 *
 * @see com.yaps.petstore.server.domain.customer.Customer
 * @see com.yaps.petstore.server.domain.order.Order
 */
public class CreditCard extends DomainObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String _creditCardNumber;
    private String _creditCardExpiryDate;
    private String _creditCardType;
	
    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (_creditCardNumber == null || "".equals(_creditCardNumber))
            throw new CheckException("Invalid city");
        if (_creditCardExpiryDate == null || "".equals(_creditCardExpiryDate))
            throw new CheckException("Invalid country");
        if (_creditCardType == null || "".equals(_creditCardType))
            throw new CheckException("Invalid street");
    }
	
    public Document toXML(){
    	Document document = DocumentHelper.createDocument();
        try {
            // Construit un document xml

             Element root = document.addElement("CreditCard"); 

             root.addElement("CreditCardNumber").addText(getCreditCardNumber());  
             root.addElement("CreditCardType").addText(getCreditCardType()); 
             Element CreditCardExpiryDate = root.addElement("CreditCardExpiryDate");
             CreditCardExpiryDate.addAttribute("Month", getCreditCardExpiryDate().substring(0,1))
                                 .addAttribute("Year", getCreditCardExpiryDate().substring(3,4));
             
        } catch (Exception e) {
             e.printStackTrace();
        }
    	return document;
    }
    
    // ======================================
    // =         Getters and Setters        =
    // ======================================
	
	public String getCreditCardNumber() {
		return _creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		_creditCardNumber = creditCardNumber;
	}
	public String getCreditCardExpiryDate() {
		return _creditCardExpiryDate;
	}
	public void setCreditCardExpiryDate(String creditCardExpiryDate) {
		_creditCardExpiryDate = creditCardExpiryDate;
	}
	public String getCreditCardType() {
		return _creditCardType;
	}
	public void setCreditCardType(String creditCardType) {
		_creditCardType = creditCardType;
	}
	
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("Credit Card{");
        buf.append("id=").append(getId());
        buf.append(",creditCardNumber=").append(getCreditCardNumber());
        buf.append(",creditCardExpiryDate=").append(getCreditCardExpiryDate());
        buf.append(",creditCardType=").append(getCreditCardType());
        buf.append('}');
        return buf.toString();
    }
	
}
