package io.codeforall.bootcamp.javabank.Controller;

import io.codeforall.bootcamp.javabank.Model.Customer;
import io.codeforall.bootcamp.javabank.View.BalanceView;

public class BalanceControler {

    public void start(Customer customer){
        BalanceView showBalance= new BalanceView();
        showBalance.execute(customer);
    }


}
