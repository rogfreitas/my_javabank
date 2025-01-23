package io.codeforall.bootcamp.javabank.persistence.daos;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;

import java.util.List;

public interface AccountDao {


        // basic crud methods
        List<Account> findAll();
        Account findById(Integer account_id);
        Account saveOrUpdate(Account account);
        void delete(Integer account_id);

        // additional methods
       // User findByUsername(String username);
        //User findByEmail(String email);

}
