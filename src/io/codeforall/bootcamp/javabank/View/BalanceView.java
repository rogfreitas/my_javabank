package io.codeforall.bootcamp.javabank.View;

import io.codeforall.bootcamp.javabank.Model.Customer;
import io.codeforall.bootcamp.javabank.Model.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceView {

    private DecimalFormat df = new DecimalFormat("#.##");
    public void execute(Customer customer) {

        System.out.println("\n" + customer.getName() + Messages.BALANCE_MESSAGE + "\n");

        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));
    }

}
