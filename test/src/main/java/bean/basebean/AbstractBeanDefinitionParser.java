package bean.basebean;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by paji on 16/8/25
 */
abstract class AbstractBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    protected final Class<?> beanClass;

    public AbstractBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * 自定义解析 解析所有字段
     * @param element
     * @param parserContext
     * @param builder
     */
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

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

        beanDefinitionParser(element, parserContext, builder);
    }

    abstract void beanDefinitionParser(Element element, ParserContext parserContext, BeanDefinitionBuilder builder);

    @Override
    protected Class<?> getBeanClass(Element element) {
        return beanClass;
    }


}
