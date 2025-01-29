package io.codeforall.bootcamp.javabank.factories;


import io.codeforall.bootcamp.javabank.model.account.AbstractAccount;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.model.account.CheckingAccount;
import io.codeforall.bootcamp.javabank.model.account.SavingsAccount;

/**
 * A factory for creating accounts of different types
 */
public class AccountFactory {

    /**
     * Creates a new {@link AbstractAccount}
     *
     * @param accountType the account type
     * @return the new account
     */
    public AbstractAccount createAccount(AccountType accountType) {

        AbstractAccount newAccount;
        switch (accountType) {
            case CHECKING:
                newAccount = new CheckingAccount();
                break;
            case SAVINGS:
                newAccount = new SavingsAccount();
                break;
            default:
                newAccount = null;

        }

        return newAccount;
    }
}
