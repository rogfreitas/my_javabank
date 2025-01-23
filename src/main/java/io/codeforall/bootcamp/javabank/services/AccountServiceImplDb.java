package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.SavingsAccount;

import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An {@link AccountService} implementation
 */
public class AccountServiceImplDb implements AccountService {

    private Connection dbConnection;
    private AccountFactory accountFactory;
    private Map<Integer, Account> accountMap = new HashMap<>();

    public void setDbConnection(Connection dbConnection){
        this.dbConnection=dbConnection;
    }

    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    /**
     * Gets the next account id
     *
     * @return the next id
     */
   /* private Integer getNextId() {
        return accountMap.isEmpty() ? 1 : Collections.max(accountMap.keySet()) + 1;
    }*/

    /**
     * @see AccountService#add(Account)
     */
    public void add(Account account) {
        try {

            String query="INSERT INTO account (account_type,balance,customer_id)VALUES ('"+account.getAccountType().toString()+"',0,"+account.getCustomer().getId()+");";
            PreparedStatement statement= dbConnection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet resultSet=statement.getGeneratedKeys();

            if(resultSet.next()){

               account.setId(resultSet.getInt(1));
            }
            if(statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       addAccountToList(account);
    }

    /**
     * @see AccountService#deposit(int, double)
     */

    public void deposit(int id, double amount) {
        accountMap.get(id).credit(amount);
    }

    /**
     * @see AccountService#withdraw(int, double)
     */
    public void withdraw(int id, double amount) {

        Account account = accountMap.get(id);

        if (!account.canWithdraw()) {
            return;
        }

        accountMap.get(id).debit(amount);
    }

    public void addAccountToList(Account account){
        accountMap.put(account.getId(),account);
    }


   public Account getAccountById(int id){
        Account account;

       try {
           Statement statement = dbConnection.createStatement();
           String query = "SELECT id,account_type,balance,customer_id FROM account WHERE id="+id+";";
           ResultSet resultSet = statement.executeQuery(query);
           if (resultSet.next()) {

               int accountId = resultSet.getInt("id");

                   account=accountServiceImplDb.getAccountById(accountId);
                   accountServiceImplDb.addAccountToList(account);
                   customer.addAccount( account);


           }
           if (statement != null) {
               statement.close();
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }


        return new SavingsAccount();
   }

    /**
     * @see AccountService#transfer(int, int, double)
     */
    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
}
