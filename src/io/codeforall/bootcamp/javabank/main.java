package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.Menu;
import io.codeforall.bootcamp.javabank.domain.handler.*;
import io.codeforall.bootcamp.javabank.managers.AccountManager;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class main {
    public static void main(String[] args) {

        AccountManager accountManager = new AccountManager();
        Bank bank = new Bank(accountManager);
        bank.addCustomer(new Customer(1, "Rui"));
        bank.addCustomer(new Customer(2, "Rogério"));
        bank.addCustomer(new Customer(3, "Tiago"));
        bank.addCustomer(new Customer(4, "Ana"));

        Menu menu = new Menu();
        menu.start(bank);

    }
}


