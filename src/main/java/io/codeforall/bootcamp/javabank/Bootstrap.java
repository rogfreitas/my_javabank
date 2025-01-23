package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.*;
import io.codeforall.bootcamp.javabank.services.*;
import io.codeforall.bootcamp.javabank.view.*;
import org.academiadecodigo.bootcamp.Prompt;
import io.codeforall.bootcamp.javabank.controller.transaction.DepositController;
import io.codeforall.bootcamp.javabank.controller.transaction.WithdrawalController;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for wiring the objects dependencies
 */
public class Bootstrap {

    private AuthServiceImpl authService;
    private CustomerServiceImplDb customerService;
    private AccountServiceImplDb accountService;

    /**
     * Sets the authentication service
     *
     * @param authService the authentication service to set
     */
    public void setAuthService(AuthServiceImpl authService) {
        this.authService = authService;
    }

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerServiceImplDb customerService) {
        this.customerService = customerService;
    }

    /**
     * Sets the account service
     *
     * @param accountService the account service to set
     */
    public void setAccountService(AccountServiceImplDb accountService) {
        this.accountService = accountService;
    }



    /**
     * Creates a {@code CustomerService} and populates it with data
     */
    public void loadCustomers() {
/*
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        Customer c3 = new Customer();
        c1.setName("Rui");
        c2.setName("Sergio");
        c3.setName("Bruno");
        customerService.add(c1);
        customerService.add(c2);
        customerService.add(c3);*/
    }

    /**
     * Wires the necessary object dependencies
     *
     * @return the login controller
     */
    public Controller wireObjects() {

        // attach all input to standard i/o
        Connection dbConnection=null;
        String dbUrl="jdbc:mysql://localhost:3306/javabank";
        Prompt prompt = new Prompt(System.in, System.out);
        try {
            if(dbConnection==null) {
                dbConnection = DriverManager.getConnection(dbUrl, "root", "");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // wire services
        authService.setCustomerService(customerService);
        customerService.setAccountServiceImplDb(accountService);
        customerService.setDbConnection(dbConnection);
        accountService.setDbConnection(dbConnection);
        accountService.setAccountFactory(new AccountFactory());

        // wire login controller and view
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginController.setAuthService(authService);
        loginView.setLoginController(loginController);
        loginView.setPrompt(prompt);

        // wire main controller and view
        MainController mainController = new MainController();
        MainView mainView = new MainView();
        mainView.setPrompt(prompt);
        mainView.setMainController(mainController);
        mainController.setView(mainView);
        mainController.setAuthService(authService);
        loginController.setNextController(mainController);

        // wire balance controller and view
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        balanceView.setBalanceController(balanceController);
        balanceController.setView(balanceView);
        balanceController.setCustomerService(customerService);
        balanceController.setAuthService(authService);

        // wire new account controller and view
        NewAccountView newAccountView = new NewAccountView();
        NewAccountController newAccountController = new NewAccountController();
        newAccountController.setAccountService(accountService);
        newAccountController.setAuthService(authService);
        newAccountController.setAccountFactory(new AccountFactory());
        newAccountController.setView(newAccountView);
        newAccountView.setNewAccountController(newAccountController);

        // wire account transactions controllers and views
        DepositController depositController = new DepositController();
        WithdrawalController withdrawalController = new WithdrawalController();
        AccountTransactionView depositView = new AccountTransactionView();
        AccountTransactionView withdrawView = new AccountTransactionView();
        depositController.setAuthService(authService);
        depositController.setAccountService(accountService);
        depositController.setCustomerService(customerService);
        depositController.setView(depositView);
        withdrawalController.setAuthService(authService);
        withdrawalController.setCustomerService(customerService);
        withdrawalController.setAccountService(accountService);
        withdrawalController.setView(withdrawView);
        depositView.setPrompt(prompt);
        depositView.setTransactionController(depositController);
        withdrawView.setPrompt(prompt);
        withdrawView.setTransactionController(withdrawalController);

        // setup the controller map
        Map<Integer, Controller> controllerMap = new HashMap<>();
        controllerMap.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        controllerMap.put(UserOptions.OPEN_ACCOUNT.getOption(), newAccountController);
        controllerMap.put(UserOptions.DEPOSIT.getOption(), depositController);
        controllerMap.put(UserOptions.WITHDRAW.getOption(), withdrawalController);

        mainController.setControllerMap(controllerMap);

        return loginController;
    }
}
