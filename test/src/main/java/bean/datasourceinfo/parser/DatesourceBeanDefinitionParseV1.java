package bean.datasourceinfo.parser;

import com.jason.middletest.bean.datasourceinfo.DataSourceInfo;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by paji on 16/8/24
 */
public class DatesourceBeanDefinitionParseV1 extends AbstractSingleBeanDefinitionParser {



    public void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

        String id = element.getAttribute("id");
        if ((id == null || id.length() == 0) ){
            String generatedBeanName = element.getAttribute("name");
            if (generatedBeanName != null && generatedBeanName.length() > 0){
                id = generatedBeanName;
            }
        }

        if (id != null && id.length() > 0){
            if (parserContext.getRegistry().containsBeanDefinition(id)){
                throw new IllegalStateException("Duplicate spring bean id:" + id + " defined!!");
            }
        }


        String url = element.getAttribute("url");
        String userName = element.getAttribute("userName");
        String password = element.getAttribute("password");
        String interfaceName = element.getAttribute("interface");
        String ref = element.getAttribute("ref");

        builder.addPropertyValue("url", url);
        builder.addPropertyValue("userName", userName);
        builder.addPropertyValue("password", password);
        builder.addPropertyValue("interfaze", interfaceName);

        if (!StringUtils.isEmpty(ref)){
            builder.addPropertyValue("ref", new RuntimeBeanReference(ref));
        }
    }

    protected Class<?> getBeanClass(Element element) {
        return  DataSourceInfo.class;
    }

}
