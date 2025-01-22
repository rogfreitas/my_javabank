package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.controller.MainController;
import io.codeforall.bootcamp.javabank.view.UserOptions;
import io.codeforall.bootcamp.javabank.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MainControllerTest {
    private MainController mainController;
    private Map<Integer, Controller> controllerMap;
    private View loginView;
    private Controller controller;

    @BeforeEach

    public void setUp() {

        mainController= new MainController();

        MockitoAnnotations.openMocks(this);

        //controllerMap= mock(MainController.class);

        loginView = mock(View.class);

        mainController.setView(loginView);

    }


    @Test
    public  void initMethodShouldShowView(){
        //exercise
        mainController.init();

        //verify
        verify(loginView).show();
    }


    @Test
    public void onMenuSelectionQuitTest(){

        //setup

        int option=UserOptions.QUIT.getOption();

       // mainController= mock(MainController.class);

        //when((anyInt() == UserOptions.QUIT.getOption())).thenReturn(true);

        //exercise
        verify(mainController).onMenuSelection(option);

        //verify

        //verify(option == UserOptions.QUIT.getOption());
        verify(controllerMap,never()).get(anyInt());
        //verify(controller,never()).init();
        verify(loginView,never()).show();

    }



}
