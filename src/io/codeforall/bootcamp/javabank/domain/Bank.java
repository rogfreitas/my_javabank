package io.codeforall.bootcamp.javabank.domain;

import io.codeforall.bootcamp.javabank.managers.AccountManager;

import java.util.HashSet;
import java.util.Set;

/**
 * The bank entity
 */
public class Bank {

    private AccountManager accountManager;
    private Set<Customer> customers = new HashSet<>();

    /**
     * Creates a new instance of Bank and initializes it with the given account manager
     *
     * @param accountManager the account manager
     */
    public Bank(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     * @see Customer#setAccountManager(AccountManager)
     */
    public void addCustomer(Customer customer) {

        customers.add(customer);
        customer.setAccountManager(accountManager);

    }

    public Customer getCustomers(int idCustomer){
        for(Customer customer:customers){
            if(customer.getIdCustomer() == idCustomer){
                return customer;
            }
        }
        return null;
    }

    /**
     * Gets the total balance of the bank
     *
     * @return the bank total balance
     */
    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers) {
            balance += customer.getBalance();
        }

        return balance;
    }
}
