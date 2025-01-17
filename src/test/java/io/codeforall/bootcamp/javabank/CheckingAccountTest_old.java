package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.CheckingAccount;

public class CheckingAccountTest_old {

    public boolean test() {

        Account account = new CheckingAccount();
        account.setId(1);

        // initial balance should be zero
        if (account.getBalance() != 0) {
            return false;
        }

        // should not be possible to credit or debit negative values
        account.credit(-1);
        account.debit(-2);
        if (account.getBalance() != 0) {
            return false;
        }

        // should be possible to credit account with positive value
        account.credit(10);
        if (account.getBalance() != 10) {
            return false;
        }

        // should not be possible to debit account if no sufficient funds
        account.debit(11);
        if (account.getBalance() != 10) {
            return false;
        }

        // should be possible to debit account if sufficient funds
        account.debit(8);
        if (account.getBalance() != 2) {
            return false;
        }

        return true;
    }
}
