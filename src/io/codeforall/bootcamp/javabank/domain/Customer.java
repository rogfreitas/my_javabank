package io.codeforall.bootcamp.javabank.domain;

import io.codeforall.bootcamp.javabank.domain.account.AccountType;
import io.codeforall.bootcamp.javabank.managers.AccountManager;
import io.codeforall.bootcamp.javabank.domain.account.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * The customer domain entity
 */
public class Customer {

    private AccountManager accountManager;
    private Map<Integer, Account> accounts = new HashMap<>();
    private String name;
    private int idCustomer;

    public Customer(int idCustomer, String name){
        this.name = name;
        this.idCustomer=idCustomer;
    }

    public Customer(){
    }

    public int getIdCustomer(){
        return idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }


    /**
     * Sets the account manager
     *
     * @param accountManager the account manager to set
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Opens a new account
     *
     * @param accountType the account type to be opened
     * @return the new account id
     * @see AccountManager#openAccount(AccountType)
     */
    public int openAccount(AccountType accountType) {
        Account account = accountManager.openAccount(accountType);
        accounts.put(account.getId(), account);
        return account.getId();
    }

    public AccountManager getAccountManager(){
        return accountManager;
    }


    public Map<Integer, Account> getAccounts(){
        return accounts;
    }
    /**
     * Gets the balance of an {@link Account}
     *
     * @param id the id of the account
     * @return the account balance
     */
    public double getBalance(int id) {
        return accounts.get(id).getBalance();
    }

    /**
     * Gets the total customer balance
     *
     * @return the customer balance
     */
    public double getBalance() {

        double balance = 0;

        for (Account account : accounts.values()) {
            System.out.println("Account number: " + account.getId() + " - Balance: "  + account.getBalance() );
            balance += account.getBalance();

        }

        return balance;
    }

}
