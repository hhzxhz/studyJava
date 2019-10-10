package com.hhzxhz.selflistner;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public class SelfListner implements ApplicationListener<SelfEvent> {

    public void onApplicationEvent(SelfEvent event) {
        System.out.println("哈哈");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
        configurableApplicationContext.addApplicationListener(new SelfListner());
        configurableApplicationContext.publishEvent(new SelfEvent("a"));

    }
}
