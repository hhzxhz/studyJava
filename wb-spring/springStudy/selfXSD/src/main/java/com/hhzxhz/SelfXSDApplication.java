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
