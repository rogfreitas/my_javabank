package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;

import java.sql.*;
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
        Connection connection = jdbcSessionManager.getCurrentSession();
        PreparedStatement statement = null;
        String query;
        if (account.getId()==null) {
            query = "INSERT INTO account(account_type, balance, customer_id) " + "VALUES (?, ?, ?);";
        }else {
            query = "UPDATE account SET account_type(?), balance(?), customer_id(?) WHERE id="+account.getId()+";";
        }


        try {

            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getAccountType().name());
            statement.setDouble(2, account.getBalance());
            statement.setInt(3, account.getCustomerId());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                account.setId(generatedKeys.getInt(1));
            }

            statement.close();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return account;
    }

    @Override
    public void delete(Integer account_id) {

    }
}
