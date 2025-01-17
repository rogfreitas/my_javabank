package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.LoginController;
import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.view.LoginView;
import io.codeforall.bootcamp.javabank.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class LoginViewTest {

    private LoginController loginController;
    private LoginView loginView;



    @BeforeEach
    public void setUp() {
        loginView = mock(LoginView.class);
        //loginView.show();
        loginController.setView(loginView);
    }


        @Test

    public void showMethodShouldShowMessageWhenNoAuthenticated() {


        when(loginController.isAuthFailed()).thenReturn(true);


        verify(loginController.isAuthFailed());

        //verify(showBankName, never()).init();
       // verify(showLoginPrompt,never()).init();

    }

    @Test
    public void showMethodShouldShowMessageWhenAuthenticated() {
        when(!loginController.isAuthFailed()).thenReturn(false);

    }

}
