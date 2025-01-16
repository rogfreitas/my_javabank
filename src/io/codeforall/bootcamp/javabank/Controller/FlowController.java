package io.codeforall.bootcamp.javabank.Controller;

import io.codeforall.bootcamp.javabank.Model.Bank;
import io.codeforall.bootcamp.javabank.View.MenuView;
import io.codeforall.bootcamp.javabank.View.ScanCustomerNumberView;
import io.codeforall.bootcamp.javabank.application.operations.BalanceOperation;
import io.codeforall.bootcamp.javabank.application.operations.NewAccountOperation;
import io.codeforall.bootcamp.javabank.application.operations.transaction.DepositOperation;
import io.codeforall.bootcamp.javabank.application.operations.transaction.WithdrawOperation;
import org.academiadecodigo.bootcamp.Prompt;

import java.util.HashMap;
import java.util.Map;

public class FlowController {
    private Prompt prompt;
    //private MenuInputScanner mainMenu;
    private Map<Integer, Operation> operationsMap;

    private Bank bank;
    private int accessingCustomerId;
    private MenuView menuView;

    public FlowController(Bank bank) {
        //menuView = new MenuView(prompt);
        this.bank = bank;
        this.prompt = new Prompt(System.in, System.out);
    }


    public Prompt getPrompt() {
        return prompt;
    }


    public Bank getBank() {
        return bank;
    }


    public int getAccessingCustomerId() {
        return accessingCustomerId;
    }


    public void start() {

        menuView = new MenuView(prompt);
        //mainMenu =
        menuView.buildMainMenu();

        accessingCustomerId = (new ScanCustomerNumberView(prompt)).scanCustomerId(bank.getCustomerIds());
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

    private Map<Integer, Operation> buildOperationsMap() {

        Map<Integer, Operation> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceControler(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation(this));

        return map;
    }


}

