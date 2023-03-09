package com.hhm.doorgod.core.listener;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

/**
 * @Author huanghm
 * @Date 2023/3/6
 */
public class HhmDoorGodApplicationStartListener implements ApplicationContextAware, ApplicationListener<ApplicationStartedEvent> {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

    }
}
