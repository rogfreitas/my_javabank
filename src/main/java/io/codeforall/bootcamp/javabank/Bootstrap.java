package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.*;
import io.codeforall.bootcamp.javabank.controller.transaction.DepositController;
import io.codeforall.bootcamp.javabank.controller.transaction.WithdrawalController;
import io.codeforall.bootcamp.javabank.managers.AccountManager;
import io.codeforall.bootcamp.javabank.model.Bank;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImp;
import io.codeforall.bootcamp.javabank.view.*;
import org.academiadecodigo.bootcamp.Prompt;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for wiring the objects dependencies
 */
public class Bootstrap {

    /**
     * Creates a {@code Bank} and populates it with data
     *
     * @return the bank
     */
    public Bank generateTestData() {

        Bank bank = new Bank();
        AccountManager accountManager = new AccountManager();
        bank.setAccountManager(accountManager);


        CustomerServiceImp customerServiceImp=new CustomerServiceImp();



        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");


        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);

        customerServiceImp.add(c1);
        customerServiceImp.add(c2);
        customerServiceImp.add(c3);

        return bank;
    }

    /**
     * Wires the necessary object dependencies
     *
     * @param bank the bank to wire
     * @return the login controller
     */
    public LoginController wireObjects(Bank bank) {

        // attach all input to standard i/o
        Prompt prompt = new Prompt(System.in, System.out);

        // wire login controller and view
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginController.setBank(bank);
        loginView.setBank(bank);
        loginView.setLoginController(loginController);
        loginView.setPrompt(prompt);

        // wire main controller and view
        MainController mainController = new MainController();
        MainView mainView = new MainView();
        mainView.setBank(bank);
        mainView.setPrompt(prompt);
        mainView.setMainController(mainController);
        mainController.setView(mainView);
        loginController.setNextController(mainController);

        // wire balance controller and view
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        balanceController.setView(balanceView);
        balanceView.setBank(bank);

        // wire new account controller and view
        NewAccountView newAccountView = new NewAccountView();
        NewAccountController newAccountController = new NewAccountController();
        newAccountController.setBank(bank);
        newAccountController.setView(newAccountView);
        newAccountView.setNewAccountController(newAccountController);

        // wire account transactions controllers and views
        DepositController depositController = new DepositController();
        WithdrawalController withdrawalController = new WithdrawalController();
        AccountTransactionView depositView = new AccountTransactionView();
        AccountTransactionView withdrawView = new AccountTransactionView();
        depositController.setBank(bank);
        depositController.setView(depositView);
        withdrawalController.setBank(bank);
        withdrawalController.setView(withdrawView);
        depositView.setBank(bank);
        depositView.setPrompt(prompt);
        depositView.setTransactionController(depositController);
        withdrawView.setBank(bank);
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
