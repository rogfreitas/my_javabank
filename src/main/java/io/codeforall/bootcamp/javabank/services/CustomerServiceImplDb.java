package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.AccountType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * An {@link CustomerService} implementation
 */
public class CustomerServiceImplDb implements CustomerService {

    private AccountServiceImplDb accountServiceImplDb;
    private Connection dbConnection;
    //private Map<Integer, Customer> customerMap = new HashMap<>();


    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }


    public void setAccountServiceImplDb(AccountServiceImplDb accountServiceImplDb){
        this.accountServiceImplDb=accountServiceImplDb;
    }
    /**
     * Gets the next account id
     *
     * @return the next id
     */
    private Integer getNextId() {
        //return customerMap.isEmpty() ? 1 : Collections.max(customerMap.keySet()) + 1;
        int lastId = 0;
        try {
            Statement statement = dbConnection.createStatement();
            String query = "SELECT MAX(id) FROM customer;";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastId + 1;
    }

    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        Customer customer = null;

        try {
            Statement statement = dbConnection.createStatement();
            String query = "SELECT c.id as customer_id, c.first_name, a.id as account_id FROM customer c LEFT JOIN account a on c.id = a.customer_id WHERE c.id=" + id + ";";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("first_name"));
                int accountId = resultSet.getInt("account_id");
                if(!resultSet.wasNull())
                {
                    Account account=accountServiceImplDb.getAccountById(accountId);
                    accountServiceImplDb.addAccountToList(account);
                    customer.addAccount( account);
                }
                while (resultSet.next()){

                    accountId = resultSet.getInt("account_id");
                    Account account=accountServiceImplDb.getAccountById(accountId);
                    accountServiceImplDb.addAccountToList(account);
                    customer.addAccount( account);
                }
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    /**
     * @see CustomerService#list()
     */
    @Override
    public List<Customer> list() {

        List<Customer> customers = new ArrayList<>();

        try {
            Statement statement = dbConnection.createStatement();
            String query = "SELECT id  FROM customer;";
            ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    customers.add (get(resultSet.getInt(1)));
                }

            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customers;
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        Set<Integer> accountIds = new HashSet<>();

        try {
            Statement statement = dbConnection.createStatement();
            String query = "SELECT id FROM account WHERE customer_id=" + id + ";";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                accountIds.add(resultSet.getInt(1));
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return accountIds;
    }

    /**
     * @see CustomerService#getBalance(int)
     */
    @Override
    public double getBalance(int id) {
/*
        List<Account> accounts = customerMap.get(id).getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }
*/
        return 0;
    }

    /**
     * @see CustomerService#add(Customer)
     */
    @Override
    public void add(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(getNextId());
        }

        //customerMap.put(customer.getId(), customer);
    }
}
