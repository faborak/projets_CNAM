package com.yaps.petstore.web;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import com.yaps.petstore.AbstractTestCase;
import junit.framework.TestSuite;

/**
 * This class tests the findcustomers page
 */
public class VisualiseCustomersTest extends AbstractTestCase {

    private WebConversation webConversation = new WebConversation();
    private static final String URL_PETSTORE = "http://localhost:8080/petstore";

    public VisualiseCustomersTest(final String s) {
        super(s);
    }

    public static TestSuite suite() {
        return new TestSuite(VisualiseCustomersTest.class);
    }

    //==================================
    //=            Test cases          =
    //==================================
    public void testWebVisualiseAllCustomers() throws Exception {

        WebResponse page;;

        page = webConversation.getResponse(URL_PETSTORE + "/findcustomers");

        String contents = page.getText();
        String expected = "All customers";
        int pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        
        expected = "Credit card number";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);

        expected = "Bill Gates";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        expected = "1245 4897 4567 8417";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        expected = "1965";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);

        expected = "Steve Jobs";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        expected = "1952";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        
    }

    public void testWebVisualiseCustomersNamedGxxx() throws Exception {

        WebResponse page;;

        page = webConversation.getResponse(URL_PETSTORE + "/findcustomers?lastNameFirstChars=G");

        String contents = page.getText();
        String expected = "Customers matching last name";
        int pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        
        expected = "Credit card number";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        
        expected = "Bill Gates";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        expected = "1245 4897 4567 8417";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        expected = "1965";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
        expected = "54321 4897 4567 8417";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);

        expected = "Steve Jobs";
        pos = contents.indexOf(expected);
        assertFalse("Text found: " + expected, pos != -1);
        expected = "1952";
        pos = contents.indexOf(expected);
        assertFalse("Text found: " + expected, pos != -1);     
    }
}