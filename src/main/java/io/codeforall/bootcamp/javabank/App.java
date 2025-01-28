package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.persistence.daos.hibernate.HibernateAccountDao;
import io.codeforall.bootcamp.javabank.persistence.daos.hibernate.HibernateCustomerDao;

import io.codeforall.bootcamp.javabank.services.AccountServiceImpl;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {


        EntityManagerFactory emf= Persistence.createEntityManagerFactory("test");

        HibernateAccountDao HibernateAccountDao = new HibernateAccountDao();
        HibernateCustomerDao HibernateCustomerDao = new HibernateCustomerDao();

        HibernateCustomerDao.setAccountDAO(HibernateAccountDao);

        AccountServiceImpl accountService = new AccountServiceImpl();
        CustomerServiceImpl customerService = new CustomerServiceImpl();

        customerService.setCustomerDAO(HibernateCustomerDao);
        customerService.setEmf(emf);

        accountService.setAccountDAO(HibernateAccountDao);
        accountService.setEmf(emf);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);
        Controller controller = bootstrap.wireObjects();


        controller.init();
        emf.close();
    }
}
