package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.persistence.daos.jdbc.JBDCAccountDao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCTransactionManager;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.jdbc.JdbcAccountService;
import io.codeforall.bootcamp.javabank.services.jdbc.JdbcCustomerService;
import io.codeforall.bootcamp.javabank.persistence.daos.jdbc.JDBCCustomerDao;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        //ConnectionManager connectionManager = new ConnectionManager();

        JDBCTransactionManager jdbcTransactionManager=new JDBCTransactionManager();
        AccountFactory accountFactory = new AccountFactory();
        JDBCSessionManager jdbcSessionManager= new JDBCSessionManager();
        JdbcAccountService accountService = new JdbcAccountService(jdbcSessionManager,jdbcTransactionManager, accountFactory);



        JDBCCustomerDao jdbcCustomerDao= new JDBCCustomerDao();
        JBDCAccountDao jbdcAccountDao= new JBDCAccountDao();
        jdbcCustomerDao.setJdbcSessionManager(jdbcSessionManager);
        jdbcCustomerDao.setJdbcAccountDao(jbdcAccountDao);


        jdbcTransactionManager.setSm(jdbcSessionManager);

        JdbcCustomerService customerService = new JdbcCustomerService(jdbcCustomerDao, jdbcTransactionManager);

        customerService.setAccountService(accountService);


        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);
        bootstrap.setAccountFactory(accountFactory);
        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();

        jdbcSessionManager.startSession();
    }
}
