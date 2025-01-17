package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.controller.LoginController;
import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


public class LoginControllerTest {

    private LoginController loginController;
    private AuthService authService;
    private Controller nextController;
    private View loginView;

    @BeforeEach
    public void setUp() {
        loginController = new LoginController();

        authService = mock(AuthService.class);
        loginView = mock(View.class);
        nextController = mock(Controller.class);

        loginController.setAuthService(authService);
        loginController.setView(loginView);
        loginController.setNextController(nextController);
    }

    @Test
    public  void initMethodShouldShowView(){
        //exercise
        loginController.init();

        //verify
        verify(loginView).show();
    }


    @Test
    public void onLoginShouldFailWithWrongId() {
        //setup
        int fakeId = 123;
        when(authService.authenticate(anyInt())).thenReturn(false);

        //exercise
        loginController.onLogin(fakeId);

        //verify
        verify(authService).authenticate(fakeId);
        verify(nextController, never()).init();
        verify(loginView).show();
    }


}
