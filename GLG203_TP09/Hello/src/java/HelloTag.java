package hello;

import java.io.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

public class HelloTag extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        for (int i = 0; i < 5; i++) {
            out.println("Hello Petstore!<BR>");
        }
    }
}
