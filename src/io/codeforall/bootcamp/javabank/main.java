package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
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

        System.out.println("Welcome to Java Bank");

        Prompt prompt = new Prompt(System.in, System.out);

        IntegerInputScanner question1 = new IntegerInputScanner();
        question1.setMessage("\nPlease insert your customer number: ");
        int customerNumber = prompt.getUserInput(question1);

        String[] options = {"View Balance", "Make deposit", "Make Withdrawal", "Open account", "Quit"};

        Customer customer = bank.getCustomers(customerNumber);
        while (true) {
            // create a menu with those options and set the message
            MenuInputScanner scanner = new MenuInputScanner(options);
            scanner.setMessage("Please choose one option -> " + customer.getName());

            // show the menu to the user and get the selected answer
            int answerIndex = prompt.getUserInput(scanner);


            SortedMap<Integer, Handler> handlers = setupHandlers(answerIndex, customer);
           // MenuInputScanner mainMenu = setupMainMenu(handlers);

            handlers.get(answerIndex).execute();
        }

    }

        private static SortedMap<Integer, Handler> setupHandlers(Integer options, Customer customer) {
            SortedMap<Integer, Handler> handlers = new TreeMap<>();
            int handlerCount = 1;

            ViewBalance viewBalance = new ViewBalance(customer);
            handlers.put(handlerCount++, viewBalance);

            MakeDeposit makeDeposit = new MakeDeposit(customer);
            handlers.put(handlerCount++, makeDeposit);

            MakeWithdrawal makeWithdrawal = new MakeWithdrawal(customer);
            handlers.put(handlerCount++, makeWithdrawal);

            OpenAccount openAccount = new OpenAccount(customer);
            handlers.put(handlerCount++, openAccount);

            Quit quitHandler = new Quit();
            handlers.put(handlerCount, quitHandler);

            return handlers;
        }

    }

