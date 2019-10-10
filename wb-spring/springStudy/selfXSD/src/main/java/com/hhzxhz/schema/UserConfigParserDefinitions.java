package com.hhzxhz.schema;

import com.hhzxhz.bean.UserConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class UserConfigParserDefinitions implements BeanDefinitionParser {

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(UserConfig.class);
        beanDefinitionBuilder.addPropertyValue("id", element.getAttribute("id"));
        beanDefinitionBuilder.addPropertyValue("name", element.getAttribute("name"));

        parserContext.getRegistry().registerBeanDefinition("userConfig", beanDefinitionBuilder.getBeanDefinition()); // 注册bean到spring容器中

        return beanDefinitionBuilder.getBeanDefinition();
    }

}