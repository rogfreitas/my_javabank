package io.codeforall.bootcamp.javabank.domain.handler;

import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.account.AccountType;

public class OpenAccount implements Handler{

    Customer customer;
    public OpenAccount(Customer customer){
        this.customer=customer;

    }

    @Override
    public void execute() {
        System.out.println("Number of Account " + customer.openAccount(AccountType.CHECKING));
    }

    @Override
    public String getMenuText() {
        return "";
    }
}
