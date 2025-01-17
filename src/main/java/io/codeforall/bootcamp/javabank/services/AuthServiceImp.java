package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.Customer;

public class AuthServiceImp implements AuthService{

    private int loginCustomer;
    private CustomerServiceImp customerServiceImp;
    private Customer logdedInCustomer;

    @Override
    public boolean authenticate(Integer id) {
        /*
        Optional <Customer> customer = customerServiceImp.list().stream().filter(x->x.getId()==id).findAny();
        if(customer.isEmpty()){
            return false;
        } else{
            this.loginCustomer = id;
            return true;
        }*/
        logdedInCustomer=customerServiceImp.get(id);
        return customerServiceImp.get(id)!=null;
    }

    @Override
    public Customer getAccessingCustomer() {
        return logdedInCustomer;
    }

    public void setCustomerServiceIml(CustomerServiceImp customerServiceImp){
        this.customerServiceImp=customerServiceImp;
    }
}
