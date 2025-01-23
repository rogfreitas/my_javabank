package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.services.AccountServiceImplDb;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImplDb;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new AccountServiceImplDb());
        bootstrap.setCustomerService(new CustomerServiceImplDb());


        Controller controller = bootstrap.wireObjects();
        bootstrap.loadCustomers();
        // start application
        controller.init();
    }
}
