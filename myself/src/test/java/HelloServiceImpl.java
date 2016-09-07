/**
 * Created by paji on 16/9/7
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String SayHello(String xl) {
        String mm = "you say : "+xl;
        System.out.println(mm);
        return mm ;
    }
}
