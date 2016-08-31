import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.net.URL;

/**
 * Created by paji on 16/8/31
 */
public class ServerStartTest extends TestCase{

    public void testStartServer(){
        URL url = ServerStartTest.class.getClassLoader().getResource("kk-server.xml");
        String path = url.getPath();

        ApplicationContext context = new FileSystemXmlApplicationContext("/" + path);

        System.out.println("asgdfhagfsdhgafsgdfha");
    }
}
