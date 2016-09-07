import com.jason.myself.framework.MyFramework;

/**
 * Created by paji on 16/9/7
 */
public class HelloClient {

    public static void main(String[] args) throws Exception {
        final HelloService helloService = MyFramework.refer(HelloService.class, "127.0.0.1", 1717);

        System.out.println("kakakak ====== "+helloService.SayHello("XL"));
        System.out.println("kakakak ====== "+helloService.SayHello("SS"));
        System.out.println("kakakak ====== "+helloService.SayHello("MM"));
    }
}
