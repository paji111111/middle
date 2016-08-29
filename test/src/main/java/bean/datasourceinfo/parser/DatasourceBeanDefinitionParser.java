package bean.datasourceinfo.parser;

import com.jason.middletest.bean.datasourceinfo.DataSourceInfo;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by paji on 16/8/18
 */
public class DatasourceBeanDefinitionParser implements BeanDefinitionParser {
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        RootBeanDefinition def = new RootBeanDefinition();

        // 设置Bean Class
        def.setBeanClass(DataSourceInfo.class);
        // 注册ID属性
        String id = element.getAttribute("id");

        BeanDefinitionHolder idHolder = new BeanDefinitionHolder(def, id);
        BeanDefinitionReaderUtils.registerBeanDefinition(idHolder,
                parserContext.getRegistry());

        // 注册属性
        String url = element.getAttribute("url");
        String userName = element.getAttribute("userName");
        String password = element.getAttribute("password");
        String interfaze = element.getAttribute("interface");
        String ref = element.getAttribute("ref");


        BeanDefinitionHolder urlHolder = new BeanDefinitionHolder(def, url);
        BeanDefinitionHolder userNameHolder = new BeanDefinitionHolder(def,
                userName);
        BeanDefinitionHolder passwordHolder = new BeanDefinitionHolder(def,
                password);
        BeanDefinitionHolder interfazeHolder = new BeanDefinitionHolder(def,
                interfaze);
        BeanDefinitionHolder refeHolder = new BeanDefinitionHolder(def,
                ref);


        BeanDefinitionReaderUtils.registerBeanDefinition(urlHolder,
                parserContext.getRegistry());
        BeanDefinitionReaderUtils.registerBeanDefinition(userNameHolder,
                parserContext.getRegistry());
        BeanDefinitionReaderUtils.registerBeanDefinition(passwordHolder,
                parserContext.getRegistry());
        BeanDefinitionReaderUtils.registerBeanDefinition(interfazeHolder,
                parserContext.getRegistry());
        BeanDefinitionReaderUtils.registerBeanDefinition(refeHolder,
                parserContext.getRegistry());



        def.getPropertyValues().addPropertyValue("url", url);
        def.getPropertyValues().addPropertyValue("userName", userName);
        def.getPropertyValues().addPropertyValue("password", password);
        def.getPropertyValues().addPropertyValue("interfaze", interfaze);
        def.getPropertyValues().addPropertyValue("ref", parserContext.getRegistry().getBeanDefinition(ref));


        return def;
    }
}
