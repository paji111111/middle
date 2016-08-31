package nettty.testrpc;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.net.URL;

/**
 * Created by paji on 16/8/31
 */
public class TestServiceTest extends TestCase {


    public void testApp() {
        try{

            URL url = TestServiceTest.class.getClassLoader().getResource("test-service.xml");
            String path = url.getPath();

            ApplicationContext context = new FileSystemXmlApplicationContext("/" + path);



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
