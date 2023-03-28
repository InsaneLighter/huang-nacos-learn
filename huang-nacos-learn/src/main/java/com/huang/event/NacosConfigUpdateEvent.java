package com.huang.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Time 2023-03-28 20:43
 * Created by Huang
 * className: NacosConfigUpdateEvent
 * Description:
 */
public class NacosConfigUpdateEvent extends ApplicationEvent {
    public NacosConfigUpdateEvent(Object source) {
        super(source);
    }
}