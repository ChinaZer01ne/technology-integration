package com.github.order.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Env implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            System.err.println("=========================");
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            if ("config".equals(beanDefinitionName)) {
                System.err.println(beanDefinitionName);
            }
        }
            System.err.println("=========================");
    }
}
