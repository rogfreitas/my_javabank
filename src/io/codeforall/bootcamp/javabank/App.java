package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.application.FlowController_;
import io.codeforall.bootcamp.javabank.Model.Bank;
import io.codeforall.bootcamp.javabank.Model.Customer;
import io.codeforall.bootcamp.javabank.Model.AccountManager;

public class App {

    public static void main(String[] args) {

        Bank bank = new Bank();
        AccountManager accountManager = new AccountManager();
        bank.setAccountManager(accountManager);

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");
        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);

        FlowController_ flowController = new FlowController_(bank);
        flowController.start();
    }
}
