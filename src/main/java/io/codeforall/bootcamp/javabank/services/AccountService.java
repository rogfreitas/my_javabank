package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.account.Account;

public interface AccountService{

    void add(Account account);
    void deposit(int id, double amount);
    void withdraw(int id, double amount);
    void transfer(int srcId, int dstId, double amount);

    
}
