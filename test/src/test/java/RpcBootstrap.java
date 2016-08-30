import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by paji on 16/8/30
 */
public class RpcBootstrap {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("server-spring.xml");
    }
}
