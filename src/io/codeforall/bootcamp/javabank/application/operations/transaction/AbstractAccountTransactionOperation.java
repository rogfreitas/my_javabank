package io.codeforall.bootcamp.javabank.application.operations.transaction;

import io.codeforall.bootcamp.javabank.Controller.FlowController;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import io.codeforall.bootcamp.javabank.application.FlowController_;
import io.codeforall.bootcamp.javabank.View.Messages;
import io.codeforall.bootcamp.javabank.application.operations.AbstractBankOperation;
import io.codeforall.bootcamp.javabank.Model.AccountManager;

/**
 * A generic account transaction to be used as a base for concrete transaction implementations
 */
public abstract class AbstractAccountTransactionOperation extends AbstractBankOperation {

    protected AccountManager accountManager;
    private Prompt prompt;

    /**
     * Initializes a new {@code AbstractAccountTransactionOperation} given a bank application
     *
     * @see AbstractBankOperation#AbstractBankOperation(FlowController)
     */
    public AbstractAccountTransactionOperation(FlowController flowController) {
        super(flowController);
        prompt = flowController.getPrompt();
        accountManager = flowController.getBank().getAccountManager();
    }

    /**
     * Performs the transaction operation
     *
     * @see AbstractBankOperation#execute()
     * @see AbstractAccountTransactionOperation#hasAccounts()
     */
    @Override
    public void execute() {

        if (!hasAccounts()) {
            System.out.println("\n" + Messages.ERROR_NO_ACCOUNT);
            return;
        }

        System.out.println("\n" + Messages.OPEN_ACCOUNTS + buildAccountList());
    }

    /**
     * Checks if customer has accounts
     *
     * @return {@code true} if the customer has accounts
     */
    protected boolean hasAccounts() {
        return !customer.getAccountIds().isEmpty();
    }

    /**
     * Shows all the customer accounts
     *
     * @return the customer accounts
     */
    private String buildAccountList() {

        StringBuilder builder = new StringBuilder();

        for (Integer id : customer.getAccountIds()) {
            builder.append(id);
            builder.append(" ");
        }

        return builder.toString();
    }

    /**
     * Prompts the user for an account number
     *
     * @return the account id
     */
    protected int scanAccount() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(customer.getAccountIds());
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);

        return prompt.getUserInput(scanner);
    }

    /**
     * Prompts the user for a transaction amount
     *
     * @return the amount to be transactioned
     */
    protected double scanAmount() {
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(Messages.CHOOSE_AMOUNT);
        scanner.setError(Messages.ERROR_INVALID_AMOUNT);

        return prompt.getUserInput(scanner);
    }
}
