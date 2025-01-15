package io.codeforall.bootcamp.javabank.application.operations;

import io.codeforall.bootcamp.javabank.Controller.FlowController;
import io.codeforall.bootcamp.javabank.Controller.Operation;
import io.codeforall.bootcamp.javabank.View.Messages;
import io.codeforall.bootcamp.javabank.Controller.UserOptions;
import io.codeforall.bootcamp.javabank.Model.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * A bank operation to check the balance
 * @see AbstractBankOperation
 * @see UserOptions#GET_BALANCE
 */
public class BalanceOperation extends AbstractBankOperation {

    private DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Creates a new {@code BalanceOperation}
     *
     * @see AbstractBankOperation#AbstractBankOperation(FlowController)
     */
    public BalanceOperation(FlowController flowController) {
        super(flowController);
    }

    /**
     * Executes this bank operation, printing the balance for the customer accounts
     *
     * @see Operation#execute()
     */
    @Override
    public void execute() {

        System.out.println("\n" + customer.getName() + Messages.BALANCE_MESSAGE + "\n");

        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));
    }
}
