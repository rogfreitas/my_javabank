package io.codeforall.bootcamp.javabank.services;
import io.codeforall.bootcamp.javabank.model.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CustomerServiceImp implements CustomerService{

    private HashMap<Integer, Customer> customers;

    public void CustomerService(){
        customers = new HashMap<>();
    }

    @Override
    public Customer get(Integer id) {
        return customers.get(id);
    }

    @Override
    public List<Customer> list() {

        return customers.values().stream().toList();

    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        return customers.get(id).getAccountIds();
    }

    @Override
    public double getBalance(int customerId) {
        double balance = 0;

        for (Customer customer : customers.values()) {
            balance += customer.getBalance();
        }

        return balance;
    }

    @Override
    public void add(Customer customer) {
            customers.put(customer.getId(),customer);
    }
}
