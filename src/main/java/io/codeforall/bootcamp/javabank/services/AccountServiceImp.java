package io.codeforall.bootcamp.javabank.services;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.services.AccountService;

import java.util.Map;

public class AccountServiceImp implements AccountService {

    //private AccountFactory accountFactory = new AccountFactory();
    private Map<Integer, Account> accountMap;

    @Override
    public void add(Account account) {
        accountMap.put(account.getId(), account);
    }

    @Override
    public void deposit(int id, double amount) {
        accountMap.get(id).credit(amount);
    }

    @Override
    public void withdraw(int id, double amount) {
        Account account = accountMap.get(id);

        if (!account.canWithdraw()) {
            return;
        }

        accountMap.get(id).debit(amount);
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {
        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
}
