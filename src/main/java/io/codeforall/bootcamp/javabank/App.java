package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.controller.LoginController;
import io.codeforall.bootcamp.javabank.services.AccountServiceImpl;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;
import io.codeforall.bootcamp.javabank.persistence.JpaBootstrap;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.dao.jpa.JpaAccountDao;
import io.codeforall.bootcamp.javabank.persistence.dao.jpa.JpaCustomerDao;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaSessionManager;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaTransactionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManagerFactory;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springConfig/masterConfig.xml");

        // retrieve configured instance
         LoginController loginController = context.getBean("loginController", LoginController.class);
         loginController.init();

        //JpaBootstrap jpa = new JpaBootstrap();  /NA
        //EntityManagerFactory emf = jpa.start(); //NA

        //JpaSessionManager sm = new JpaSessionManager(emf); // done with notation
        //TransactionManager tx = new JpaTransactionManager(sm);

        //App app = new App();
        //app.bootStrap(tx, sm);

        //jpa.stop();

    }
/*
    private void bootStrap(TransactionManager tx, JpaSessionManager sm) {

        //AccountServiceImpl accountService = new AccountServiceImpl();
        //accountService.setAccountDao(new JpaAccountDao(sm));
        //accountService.setTransactionManager(tx);

        //CustomerServiceImpl customerService = new CustomerServiceImpl();
        //customerService.setCustomerDao(new JpaCustomerDao(sm));
        //customerService.setTransactionManager(tx);

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);

        Controller controller = bootstrap.wireObjects();

        // start application
        //controller.init();
    }*/
}
