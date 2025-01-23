package io.codeforall.bootcamp.javabank.persistence.daos;

import io.codeforall.bootcamp.javabank.model.Customer;

import java.util.List;

public interface CustomerDao{

    List<Customer> findAll();
    Customer findById(Integer customer_id);
    Customer saveOrUpdate(Customer customer);
    void delete(Integer customer_id);
}
