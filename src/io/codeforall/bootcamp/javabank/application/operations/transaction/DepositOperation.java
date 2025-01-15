package io.codeforall.bootcamp.javabank.application.operations.transaction;

import io.codeforall.bootcamp.javabank.Controller.FlowController;

import io.codeforall.bootcamp.javabank.Controller.UserOptions;

/**
 * An account transaction used to deposit an amount
 * @see AbstractAccountTransactionOperation
 * @see UserOptions#DEPOSIT
 */
public class DepositOperation extends AbstractAccountTransactionOperation {

    /**
     * Initializes a new {@code DepositOperation}
     *
     * @see AbstractAccountTransactionOperation
     */
    public DepositOperation(FlowController flowController) {
        super(flowController);
    }

    /**
     * Deposit an amount into an account
     *
     * @see AbstractAccountTransactionOperation#execute()
     */
    @Override
    public void execute() {

        super.execute();

        if (!hasAccounts()) {
            return;
        }

        Integer accountId = scanAccount();
        Double amount = scanAmount();

        if (customer.getAccountIds().contains(accountId)) {
            accountManager.deposit(accountId, amount);
        }
    }
}
