import com.test.jason.HelloService;

/**
 * Created by acer-pc on 2016/10/13.
 */
public class SuperInterface {

    public static void main(String[] args) {

        HelloService hello = new HelloServiceImpl();

        Class [] classInterfaces = hello.getClass().getInterfaces();

        for (Class clasc:classInterfaces) {
            System.out.println(clasc.getName());
        }

        System.out.println(HelloService.class);
    }

}
