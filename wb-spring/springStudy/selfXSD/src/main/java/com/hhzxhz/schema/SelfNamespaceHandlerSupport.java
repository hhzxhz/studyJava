package com.hhzxhz.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SelfNamespaceHandlerSupport extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("userConfig", new UserConfigParserDefinitions());
    }
}
