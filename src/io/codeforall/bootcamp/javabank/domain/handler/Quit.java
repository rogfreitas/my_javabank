package io.codeforall.bootcamp.javabank.domain.handler;

public class Quit implements Handler{
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String getMenuText() {
        return "";
    }
}
