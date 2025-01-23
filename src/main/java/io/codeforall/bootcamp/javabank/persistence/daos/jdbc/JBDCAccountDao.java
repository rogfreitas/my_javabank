package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JBDCAccountDao implements AccountDao {

    AccountFactory accountFactory;
    JDBCSessionManager jdbcSessionManager;


    public void setJdbcSessionManager(JDBCSessionManager jdbcSessionManager) {
        this.jdbcSessionManager = jdbcSessionManager;

    }


    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    @Override
    public List<Account> findAll() {
        return List.of();
    }

    @Override
    public Account findById(Integer account_id) {
        Account account=null;
        try {

            String query = "SELECT id, account_type, customer_id, balance, version FROM account WHERE id=?";
            PreparedStatement statement = jdbcSessionManager.getCurrentSession().prepareStatement(query);

            statement.setInt(1, account_id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                AccountType accountType = AccountType.valueOf(resultSet.getString("account_type"));

                account = accountFactory.createAccount(accountType);
                account.setId(resultSet.getInt("id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.credit(resultSet.getInt("balance"));
                account.setVersion(resultSet.getInt("version"));
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;




    }

    @Override
    public Account saveOrUpdate(Account account) {
        return null;
    }

    @Override
    public void delete(Integer account_id) {

    }
}
