package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.Customer;

import java.util.Optional;

public class AuthServiceImp implements AuthService{

    private int loginCustomer;
    private CustomerServiceImp customerServiceImp;

    @Override
    public boolean authenticate(Integer id) {
        Optional <Customer> customer = customerServiceImp.list().stream().filter(x->x.getId()==id).findAny();
        if(customer.isEmpty()){
            return false;
        } else{
            this.loginCustomer = id;
            return true;
        }
    }

    @Override
    public Customer getAccessingCustomer() {
        return customerServiceImp.get(loginCustomer);
    }

    public void set(CustomerServiceImp customerServiceImp){
        this.customerServiceImp=customerServiceImp;
    }
}
