package io.codeforall.bootcamp.javabank.controller.transaction;

import io.codeforall.bootcamp.javabank.controller.AbstractController;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImp;

/**
 * A generic account transaction controller to be used as a base for concrete transaction controller implementations
 * @see AbstractController
 * @see AccountTransactionController
 */
public abstract class AbstractAccountTransactionController extends AbstractController implements AccountTransactionController {

    //protected Bank bank;
    protected CustomerServiceImp customerServiceImp;

    /**
     * Sets the bank
     *
     * @param customerServiceImp the bank to set
     */
    /*
    public void setBank(Bank bank) {
        this.bank = bank;
    }*/

    public void setCustomerServiceImp(CustomerServiceImp customerServiceImp){
        this.customerServiceImp=customerServiceImp;
    }
}
