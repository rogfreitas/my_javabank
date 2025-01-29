package io.codeforall.bootcamp.javabank.model.account;

import javax.persistence.Entity;

@Entity
public class CheckingAccount extends AbstractAccount {

    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
