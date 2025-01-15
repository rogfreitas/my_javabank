package io.codeforall.bootcamp.javabank.application;

import io.codeforall.bootcamp.javabank.View.MenuView;
import io.codeforall.bootcamp.javabank.Controller.UserOptions;
import io.codeforall.bootcamp.javabank.View.ScanCustomerNumberView;
import io.codeforall.bootcamp.javabank.application.operations.BalanceOperation;
import io.codeforall.bootcamp.javabank.application.operations.NewAccountOperation;
import io.codeforall.bootcamp.javabank.Controller.Operation;
import io.codeforall.bootcamp.javabank.application.operations.transaction.DepositOperation;
import io.codeforall.bootcamp.javabank.application.operations.transaction.WithdrawOperation;
import org.academiadecodigo.bootcamp.Prompt;
import io.codeforall.bootcamp.javabank.Model.Bank;

import java.util.HashMap;
import java.util.Map;

/**
 * The bank application
 */
public class FlowController_ {

    private Prompt prompt;
    //private MenuInputScanner mainMenu;
    private Map<Integer, Operation> operationsMap;

    private Bank bank;
    private int accessingCustomerId;
    private MenuView menuView;


    /**
     * Creates a new instance of a {@code BankApplication}, initializes it with the given {@link Bank}
     *
     * @param bank the bank instance
     */
    public FlowController_(Bank bank) {
        //menuView = new MenuView(prompt);
        this.bank = bank;
        this.prompt = new Prompt(System.in, System.out);
    }

    /**
     * Gets the prompt used for the UI
     *
     * @return the prompt
     */
    public Prompt getPrompt() {
        return prompt;
    }

    /**
     * Gets the bank used by this application
     *
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Gets the id of the customer using the Bank Application
     *
     * @return the customer id
     */
    public int getAccessingCustomerId() {
        return accessingCustomerId;
    }

    /**
     * Starts the bank application
     */
    public void start() {

        menuView = new MenuView(prompt);
        //mainMenu =
        menuView.buildMainMenu();

     //   accessingCustomerId = ScanCustomerNumberView.scanCustomerId(bank.getCustomerIds());
        operationsMap = buildOperationsMap();
        menuLoop();
    }

    private void menuLoop() {

       // int userChoice = prompt.getUserInput(mainMenu);

        int userChoice = menuView.menuLoop();

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        operationsMap.get(userChoice).execute();
        menuLoop();
    }

/*
    private int scanCustomerId() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        return prompt.getUserInput(scanner);
    }
    */


/*
    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;
    }
*/


    private Map<Integer, Operation> buildOperationsMap() {

        Map<Integer, Operation> map = new HashMap<>();
 /*       map.put(UserOptions.GET_BALANCE.getOption(), new BalanceOperation(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation(this));
*/
        return map;
    }
}
