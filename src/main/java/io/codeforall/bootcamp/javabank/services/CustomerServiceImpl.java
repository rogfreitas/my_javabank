package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.persistence.TransactionException;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;

public class CustomerServiceImpl implements CustomerService {

    private EntityManagerFactory emf;
    private CustomerDao customerDao;


    public void setCustomerDAO(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public Customer get(Integer id) {
        EntityManager em=null;
        try {
            em= emf.createEntityManager();
            customerDao.setEm(em);
            em.getTransaction().begin();


                   Customer customerBD= customerDao.findById(id);

            return customerBD;
        } finally {
            em.getTransaction().commit();
            if(em!=null) em.close();
        }

    }

    @Override
    public List<Customer> list() {
        EntityManager em=null;
        try {
            em=emf.createEntityManager();
            customerDao.setEm(em);
            em.getTransaction().begin();
            return customerDao.findAll();
        } finally {
            em.getTransaction().commit();
            if(em!=null) em.close();
        }
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        Customer customer = get(id);

        if (customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        List<Account> accounts = customer.getAccounts();

        if (accounts.size() == 0) {
            return Collections.emptySet();
        }

        Set<Integer> customerAccountIds = new HashSet<>();

        for (Account account : accounts) {
            customerAccountIds.add(account.getId());
        }

        return customerAccountIds;
    }

    @Override
    public double getBalance(int id) {

        Customer customer = get(id);

        if (customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        List<Account> accounts = customer.getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;
    }

    @Override
    public void add(Customer customer) {
        EntityManager em=null;
        try {
            em=emf.createEntityManager();
            customerDao.setEm(em);
            em.getTransaction().begin();
            customerDao.saveOrUpdate(customer);
            em.getTransaction().commit();
        } catch (TransactionException e){
            em.getTransaction().rollback();
            if(em!=null) em.close();
        }

    }


}
