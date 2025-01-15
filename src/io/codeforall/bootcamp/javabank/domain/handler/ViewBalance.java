package io.codeforall.bootcamp.javabank.domain.handler;

import io.codeforall.bootcamp.javabank.domain.Customer;

public class ViewBalance implements Handler{
    Customer customer;
    public ViewBalance(Customer customer){
       this.customer=customer;
    }
    @Override
    public void execute() {
        System.out.println("\n\nTotal Balance: " + customer.getBalance());
    }

    @Override
    public String getMenuText() {
        return "";
    }
}
