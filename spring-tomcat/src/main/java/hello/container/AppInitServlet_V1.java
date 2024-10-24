package hello.container;

import hello.servlet.HelloServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

public class AppInitServlet_V1 implements AppInit {

    @Override
    public void onStartup(ServletContext context) {
        System.out.println("AppInitServlet_V1.onStartup");

        ServletRegistration.Dynamic helloServlet = context.addServlet("helloServlet", new HelloServlet());
        helloServlet.addMapping("/hello-servlet");
    }
}
