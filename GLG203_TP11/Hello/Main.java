import javax.naming.InitialContext;
import javax.naming.Context;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) {

        InitialContext ic = null;

        try {
            ic = new InitialContext();
            HelloService hello = (HelloService) ic.lookup(HelloService.JNDI_NAME);
            hello.addHello("Hello");
            System.out.println(hello.sayHello());
            hello.addHello("Petstore");
            System.out.println(hello.sayHello());
            hello.addHello("!");
            System.out.println(hello.sayHello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
