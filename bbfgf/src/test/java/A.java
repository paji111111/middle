import com.jason.bbfgf.server.AnnotationServerBoot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.net.URL;

/**
 * Created by paji on 16/9/1
 */
public class A {
    public static void main(String[] args) {
        URL url = A.class.getClassLoader().getResource("kk-server.xml");
        String path = url.getPath();

        ApplicationContext context = new FileSystemXmlApplicationContext("/" + path);
        AnnotationServerBoot annotationServerBoot = (AnnotationServerBoot)context.getBean("start");

        annotationServerBoot.start();

    }
}
