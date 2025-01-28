package io.codeforall.bootcamp.javabank.model.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * A checking account with no restrictions
 * @see Account
 * @see AccountType#CHECKING
 */
@Entity
@DiscriminatorValue("CHECKING")
public class CheckingAccount extends AbstractAccount {

    /**
     * @see Account#getAccountType()
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
