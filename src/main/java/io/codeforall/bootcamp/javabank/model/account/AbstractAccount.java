package io.codeforall.bootcamp.javabank.model.account;

import io.codeforall.bootcamp.javabank.model.AbstractModel;
import io.codeforall.bootcamp.javabank.model.Customer;

import javax.persistence.*;

/**
 * A generic account model entity to be used as a base for concrete types of accounts
 * @see Account
 */
@Entity
@Table(name="account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="account_type",discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractAccount extends AbstractModel implements Account {
    private double balance = 0;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    //private Integer customerId;

    /**
     * @see Account#getBalance()
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @see Account#getAccountType()
     */
    public abstract AccountType getAccountType();

    /**
     * Credits account if possible
     *
     * @param amount the amount to credit
     * @see Account#credit(double)
     */
    public void credit(double amount) {
        if (canCredit(amount)) {
            balance += amount;
        }
    }

    /**
     * Debits the account if possible
     *
     * @param amount the amount to debit
     * @see Account#canDebit(double)
     */
    public void debit(double amount) {
        if (canDebit(amount)) {
            balance -= amount;
        }
    }

    /**
     * @see Account#canCredit(double)
     */
    public boolean canCredit(double amount) {
        return amount > 0;
    }

    /**
     * @see Account#canDebit(double)
     */
    public boolean canDebit(double amount) {
        return amount > 0 && amount <= balance;
    }

    /**
     * @see Account#canWithdraw()
     */
    public boolean canWithdraw() {
        return true;
    }

    @Override
    public Integer getCustomerId() {
        return customer.getId();
    }

    @Override
    public void setCustomer(Customer Customer) {
        this.customer = customer;
    }
}
