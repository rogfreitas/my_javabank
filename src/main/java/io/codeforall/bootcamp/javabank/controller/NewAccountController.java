package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImp;
import io.codeforall.bootcamp.javabank.view.NewAccountView;
import jdk.jfr.SettingDefinition;

import java.lang.annotation.Inherited;

/**
 * The {@link NewAccountView} controller
 */
public class NewAccountController extends AbstractController {

    //private Bank bank;
    private CustomerServiceImp customerServiceImp;
    private Integer newAccountId;

    public void setCustomerServiceImp(CustomerServiceImp customerServiceImp){
        this.customerServiceImp=customerServiceImp;
    }
    /**
     * Gets the new account id
     *
     * @return the new account id
     */
    public Integer getNewAccountId() {
        return newAccountId;
    }

    /**
     * Creates a new {@link Account}
     *
     * @see Controller#init()
     * @see AccountManager#openAccount(AccountType)
     */
    @Override
    public void init() {

        newAccountId = createAccount();
        super.init();
    }

    private int createAccount() {

        //Account newAccount = bank.getAccountManager().openAccount(AccountType.CHECKING);
        //bank.getLoginCustomer().addAccount(newAccount);

        Account newAccount = customerServiceImp.getAccountManager().openAccount(AccountType.CHECKING);

        return newAccount.getId();
    }
}
