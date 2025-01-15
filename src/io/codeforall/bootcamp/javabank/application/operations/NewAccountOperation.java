package io.codeforall.bootcamp.javabank.application.operations;

import io.codeforall.bootcamp.javabank.Model.account.AccountType;
import io.codeforall.bootcamp.javabank.Controller.FlowController;
import io.codeforall.bootcamp.javabank.View.Messages;
import io.codeforall.bootcamp.javabank.Controller.UserOptions;

/**
 * A bank operation to create new accounts
 * @see AbstractBankOperation
 * @see UserOptions#OPEN_ACCOUNT
 */
public class NewAccountOperation extends AbstractBankOperation {

    /**
     * Creates a new {@code NewAccountOperation}
     *
     * @see AbstractBankOperation#AbstractBankOperation(FlowController)
     */
    public NewAccountOperation(FlowController flowController) {
        super(flowController);
    }

    /**
     * Creates a new bank account
     *
     * @see AbstractBankOperation#execute()
     */
    @Override
    public void execute() {

        int accountId = customer.openAccount(AccountType.CHECKING);

        System.out.println("\n" + Messages.CREATED_ACCOUNT + customer.getName() + " : " + accountId);
    }
}
