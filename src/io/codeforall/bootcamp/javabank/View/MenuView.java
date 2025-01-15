package io.codeforall.bootcamp.javabank.View;

import io.codeforall.bootcamp.javabank.Controller.UserOptions;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.util.Set;

public class MenuView {
    private Prompt prompt;
    private MenuInputScanner mainMenu;


    public MenuView(Prompt prompt){
        this.prompt=prompt;
    }


    public int menuLoop() {
        int userChoice = prompt.getUserInput(mainMenu);
        return userChoice;
    }



    public MenuInputScanner buildMainMenu() {

        mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;
    }


}
