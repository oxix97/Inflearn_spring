package hello.container;


import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class)
public class MyContainerInit_V2 implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext context) {
        System.out.println("MyContainerInit_V2.onStartup");
        System.out.println("clazz = " + set);
        System.out.println("context = " + context);

        for (Class<?> init : set) {
            try {
                // MEMO : new AppInitServlet_V1()과 동일한 코드
                AppInit appInit = (AppInit) init.getDeclaredConstructor().newInstance();
                appInit.onStartup(context);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
