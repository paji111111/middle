package bean.datasourceinfo.handler;

import bean.basebean.ProducerBeanDefinitionParser;
import bean.datasourceinfo.parser.DatesourceBeanDefinitionParseV1;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by paji on 16/8/18
 */
public class AtyNamespaceHandlerSupport extends NamespaceHandlerSupport {

    public void init() {
//        registerBeanDefinitionParser("datasource" , new DatasourceBeanDefinitionParser());

        registerBeanDefinitionParser("datasource" , new DatesourceBeanDefinitionParseV1());
        registerBeanDefinitionParser("producer" , new ProducerBeanDefinitionParser());



    }
}
