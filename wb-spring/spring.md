# Spring 学习文档---自定义XSD文件

## 自定义`XSD`文件

源码目录：`springStudy/selfXSD`

### 第一步：设计配置属性`javabean`

创建文件`UserConfig.jara`

```java
package com.hhzxhz.bean;

import lombok.Data;

@Data
public class UserConfig {
    private Integer id;
    private String name;
}
```



### 第二步：编写`xsd`文件

自定义xsd文件`selfxsd.xsd`,保存到目录 `resources/META-INF`

注意此处命名空间定义：targetNamespace

```xsd
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<xsd:schema xmlns="http://hhzxhx.com/schema/selfxsd"
            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
            targetNamespace="http://hhzxhx.com/schema/selfxsd"
            elementFormDefault="qualified">

    <xsd:import namespace="http://www.w3.org/XML/1998/namespace"></xsd:import>

    <xsd:element name="userConfig">
        <xsd:complexType>
            <xsd:attribute name="id" type="xsd:int"></xsd:attribute>
            <xsd:attribute name="name" type="xsd:string"></xsd:attribute>
        </xsd:complexType>
    </xsd:element>
</xsd:schema>
```



### 第三部：编写NamespaceHandlerSupport和BeanDefinitionParser

#### 编写xsd解析文件

```java
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
```



#### 编写解析文件注册类

```java
package com.hhzxhz.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SelfNamespaceHandlerSupport extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("userConfig", new UserConfigParserDefinitions());
    }
}

```



### 第四部：在`spring.schemas`和`spring.handlers`中填入关系，使之能关联起来

#### 文件 spring.schemas

格式为： Key=Value

Key：xsd文件名别命（一般为名字空间名+xsd文件名）

Value：xsd文件所在路径

```
http\://hhzxhx.com/schema/selfxsd/selfxsd.xsd=META-INF/selfxsd.xsd
```



#### 文件 spring.handlers

格式为： Key=Value

Key：名字空间名

Value：解析器类名

```
http\://hhzxhx.com/schema/selfxsd=com.hhzxhz.schema.SelfNamespaceHandlerSupport
```

### 第五部：验证

``` java
package com.hhzxhz;

import com.hhzxhz.bean.UserConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SelfXSDApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("xml-config/spring-bean.xml");
        UserConfig userconfig = (UserConfig) context.getBean("userConfig");
        System.out.println(userconfig);
    }
}

```

