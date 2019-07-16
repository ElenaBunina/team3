package com.taskHandler;



import com.config.spring.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
}
}


