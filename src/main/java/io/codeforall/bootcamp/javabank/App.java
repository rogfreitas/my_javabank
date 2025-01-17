package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.LoginController;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImp;

public class App {

    //private Bank bank;
    private CustomerServiceImp customerServiceImp;

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();

        //bank = bootstrap.generateTestData();
        //bank = bootstrap.generateTestData();

        customerServiceImp=bootstrap.generateTestData();

        //LoginController loginController = bootstrap.wireObjects(bank);
        LoginController loginController = bootstrap.wireObjects(customerServiceImp);

        // start application
        loginController.init();
    }
}
