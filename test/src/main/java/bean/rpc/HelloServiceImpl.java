package bean.rpc;

/**
 * Created by paji on 16/8/26
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

}
