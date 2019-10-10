package com.hhzxhz.selflistner;

import org.springframework.context.ApplicationEvent;

public class SelfEvent extends ApplicationEvent {
    public SelfEvent(Object source) {
        super(source);
    }
}
