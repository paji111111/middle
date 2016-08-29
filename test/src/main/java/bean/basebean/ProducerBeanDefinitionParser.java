package bean.basebean;

import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by paji on 16/8/25
 */
public class ProducerBeanDefinitionParser extends AbstractBeanDefinitionParser {

    public ProducerBeanDefinitionParser() {
        super(Producer.class);
    }

    @Override
    void beanDefinitionParser(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

        String interfaceName = element.getAttribute("interface");
        String targetBean = element.getAttribute("targetBean");

        builder.addPropertyValue("interfaze", interfaceName);
        builder.addPropertyValue("targetBean", new RuntimeBeanReference(targetBean));
    }

}
