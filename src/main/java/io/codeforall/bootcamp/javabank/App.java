package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.LoginController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springConfig/masterConfig.xml");
        // retrieve configured instance
         LoginController loginController = context.getBean("loginController", LoginController.class);
         loginController.init();
    }

}
