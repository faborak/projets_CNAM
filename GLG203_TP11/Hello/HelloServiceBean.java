import javax.ejb.Stateful;

@Stateful (name="HelloSB")
public class HelloServiceBean implements HelloService {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String buffer = "";

    // ======================================
    // =            Constructors            =
    // ======================================
    public HelloServiceBean() {
	buffer = this.toString() + ": ";
    }

    // ======================================
    // =     Category Business Methods      =
    // ======================================
    public String sayHello() {
        return buffer;
    }

    public void addHello(String hello) {
        buffer += hello + " ";
    }
}
