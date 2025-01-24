package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.services.AccountService;
import io.codeforall.bootcamp.javabank.services.jdbc.JdbcAccountService;
import io.codeforall.bootcamp.javabank.view.NewAccountView;

/**
 * The {@link NewAccountView} controller
 */
public class NewAccountController extends AbstractController {

    private Integer newAccountId;
    private AccountService accountService;
    private AccountFactory accountFactory;

    /**
     * Gets the new account id
     *
     * @return the new account id
     */
    public Integer getNewAccountId() {
        return newAccountId;
    }

    /**
     * Sets the account service
     *
     * @param accountService the account service to set
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Sets the account factory
     *
     * @param accountFactory
     */
    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    /**
     * Creates a new {@link Account}
     *
     * @see Controller#init()
     * @see AccountFactory#createAccount(AccountType)
     */
    @Override
    public void init() {

        newAccountId = createAccount();
        //createAccount();
        super.init();
    }

    private int createAccount() {

        Account newAccount = accountFactory.createAccount(AccountType.CHECKING);
        Customer accessingCustomer = authService.getAccessingCustomer();

        accessingCustomer.addAccount(newAccount);
        accountService.add(newAccount);

        return newAccount.getId();
    }
}
