package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.AuthServiceImp;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImp;
import io.codeforall.bootcamp.javabank.view.LoginView;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {

    private Controller nextController;
    private AuthServiceImp authServiceImp;

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


    public void setAuthServiceImp(AuthServiceImp authServiceImp){
        this.authServiceImp=authServiceImp;
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
        if (authServiceImp.authenticate(id)){
            nextController.init();
        }else{
            init();
        }

    }

}
