package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.view.BalanceView;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.services.CustomerService;

/**
 * The {@link BalanceView} controller
 */
public class BalanceController extends AbstractController {

    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Gets the customer
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return authService.getAccessingCustomer();
    }

    /**
     * Gets the customer balance
     *
     * @return the customer balance
     */
    public double getCustomerBalance() {
        return customerService.getBalance(authService.getAccessingCustomer().getId());
    }
}
