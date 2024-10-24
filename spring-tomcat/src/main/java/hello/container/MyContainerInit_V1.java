package hello.container;


import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.util.Set;

public class MyContainerInit_V1 implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext context) throws ServletException {
        System.out.println("MyContainerInit_V1.onStartup");
        System.out.println("clazz = " + set);
        System.out.println("context = " + context);
    }
}
