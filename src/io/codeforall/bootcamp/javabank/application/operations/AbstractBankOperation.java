package io.codeforall.bootcamp.javabank.application.operations;

import io.codeforall.bootcamp.javabank.Controller.FlowController;
import io.codeforall.bootcamp.javabank.Controller.Operation;
import io.codeforall.bootcamp.javabank.Model.Customer;

/**
 * A generic bank operation to be used as a base for concrete types of bank operations
 * @see Operation
 */
public abstract class AbstractBankOperation implements Operation {

    protected FlowController flowController;
    protected Customer customer;

    /**
     * Initializes a new {@code AbstractBankOperation} given a bank application
     *
     * @param flowController the bank application
     */
    public AbstractBankOperation(FlowController flowController) {
        this.flowController = flowController;
        customer = flowController.getBank().getCustomer(flowController.getAccessingCustomerId());
    }
}
