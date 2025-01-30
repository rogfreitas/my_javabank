package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.controller.LoginController;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles(getProfile());
        ctx.load(Config.SPRING_CONFIG);
        ctx.refresh();

        Controller controller = ctx.getBean(LoginController.class);
        controller.init();
    }

    private String getProfile() {

        String target = System.getenv(Config.SPRING_PROFILE_ENV_VAR);
        return target == null ? Config.SPRING_DEFAULT_PROFILE : target;
    }
}
