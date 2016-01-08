import javax.ejb.Remote;

@Remote
public interface HelloService {
    String JNDI_NAME = "java:global/hello/hello-ejb/HelloSB!HelloService";

    void addHello(String hello);
    String sayHello() ;
}
