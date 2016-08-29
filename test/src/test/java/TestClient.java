import bean.basebean.Producer;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.net.URL;

/**
 * Created by paji on 16/8/18
 */
public class TestClient extends TestCase {

//    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//            "classpath*:spring.xml");
//
//    public static void main(String[] args)
//    {
//        System.out.println(context.getBeanDefinitionCount());
//
//        DataSourceInfo d = (DataSourceInfo) context.getBean("myDataSourcce");
//        System.out.println(d);
//    }

    public void testApp() {
        try{

            URL url = TestClient.class.getClassLoader().getResource("spring.xml");
            String path = url.getPath();

            ApplicationContext context = new FileSystemXmlApplicationContext("/" + path);

            System.out.println(context.getBean("myDataSourcce"));

            Producer producer = (Producer)context.getBean("ii");
            System.out.println(producer);

            System.out.println(producer.getInterfaze());

            Class<?>[] interfaces = producer.getTargetBean().getClass().getInterfaces();

            boolean flag = false;

            for (int i =0;i<interfaces.length;i++){
                System.out.println(interfaces[i].toString());

                if (interfaces[i].equals(producer.getInterfaze())){
                    flag = flag||true;
                }
            }

            System.out.println(flag);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
