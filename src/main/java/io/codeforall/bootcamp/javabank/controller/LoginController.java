package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.CustomerServiceImp;
import io.codeforall.bootcamp.javabank.view.LoginView;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {

    private Controller nextController;

    //private Bank bank;
    private CustomerServiceImp customerServiceImp;

    /**
     * Sets the next controller
     *
     * @param nextController the next controller to be set
     */
    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    /**
     * Sets the bank
     *
     * @param customerServiceImp the bank to be set
     */
    //public void setBank(Bank bank) {
     //   this.bank = bank;
    //}
    public void setBank(CustomerServiceImp customerServiceImp) {
        this.customerServiceImp = customerServiceImp;
    }

    /**
     * Identifies the logged in customer
     *
     * @param id the customer id
     */
    public void onLogin(int id) {

        //customerServiceImp.setLoginCustomer(id);

        nextController.init();
    }

}
