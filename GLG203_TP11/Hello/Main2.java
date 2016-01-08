
import javax.ejb.EJB;

public class Main2 {
    @EJB 
    private static HelloService hello;
    /* 
     * Warning : NullPointerException at execution time
     * because only a container can inject the reference.
     * => should be executed in a ACC (Application Client Container)  : 
     *  %GLASSFISH_HOME%/bin/appclient Main2
     */
    
    public static void main(String[] args) {

        try {
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
