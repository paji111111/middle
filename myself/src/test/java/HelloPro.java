import com.jason.myself.framework.MyFramework;
import com.test.jason.HelloService;

/**
 * Created by paji on 16/9/7
 */
public class HelloPro {

    public static void main(String[] args) throws Exception {
        HelloService hello = new HelloServiceImpl();
        MyFramework.register(hello, 1717, "127.0.0.1");
    }
}