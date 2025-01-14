package io.codeforall.bootcamp.javabank.domain.handler;

import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.account.Account;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;

import java.util.Map;

public class MakeWithdrawal implements Handler{

    Customer customer;
    public MakeWithdrawal(Customer customer){
        this.customer=customer;
    }



    @Override
    public void execute() {
        Map<Integer, Account> accounts = customer.getAccounts();
        int accountNumber=0;
        if (accounts.isEmpty()) {
            System.out.println("The client need to open an account...");
            return;
        } else if (accounts.size()>1){
            for(Account account:accounts.values()){
                System.out.println("Account number -> " + account.getId());
            }

            Prompt prompt = new Prompt(System.in, System.out);
            IntegerInputScanner question1 = new IntegerInputScanner();
            question1.setMessage("\nPlease insert your account number: ");
            accountNumber = prompt.getUserInput(question1);
        }else{
            for(Account account:accounts.values()){
                accountNumber=account.getId();
            }

        }

        Prompt prompt = new Prompt(System.in, System.out);
        IntegerInputScanner question1 = new IntegerInputScanner();
        question1.setMessage("\nPlease insert amount to withdraw-> ");
        int amount = prompt.getUserInput(question1);
        customer.getAccountManager().withdraw(accountNumber, amount);
        //customer.getAccountManager().deposit(accountNumber, amount);

    }

    @Override
    public String getMenuText() {
        return "";
    }
}
