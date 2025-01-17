package io.codeforall.bootcamp.javabank.view;

import io.codeforall.bootcamp.javabank.controller.LoginController;
import io.codeforall.bootcamp.javabank.model.Customer;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;

import java.util.HashSet;
import java.util.Set;

/**
 * A view shown at login
 *
 * @see LoginController
 */
public class LoginView extends AbstractView {

    private LoginController loginController;

    /**
     * Sets the controller responsible for rendering the view
     *
     * @param loginController the login controller to set
     */
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    /**
     * @see View#show()
     */
    @Override
    public void show() {
        showBankName();
        showLoginPrompt();
    }

    private void showBankName() {
        System.out.println("\n" + Messages.VIEW_LOGIN_WELCOME);
    }

    private void showLoginPrompt() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(new HashSet<>( customerServiceImp.list().stream().map(Customer::getId).toList()));
        scanner.setMessage("\n" + Messages.VIEW_LOGIN_MESSAGE);
        scanner.setError(Messages.VIEW_LOGIN_ERROR);
        loginController.onLogin(prompt.getUserInput(scanner));
    }
}
