package io.codeforall.bootcamp.javabank.persistence.daos.hibernate;


import io.codeforall.bootcamp.javabank.model.account.AbstractAccount;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;

import io.codeforall.bootcamp.javabank.model.account.Account;

import io.codeforall.bootcamp.javabank.persistence.TransactionException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.sql.SQLException;

import java.util.List;

public class HibernateAccountDao implements AccountDao {

    private EntityManager em;


    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Account> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        // 2 - create a new CriteriaQuery instance for the Customer entity
        CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);

        // 3 - get the root of the query, from where all navigation starts
        Root<Account> root = criteriaQuery.from(Account.class);

        // 4 - specify the item that is to be returned in the query result
        criteriaQuery.select(root);
        return em.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public Account findById(Integer id) {
        return em.find(AbstractAccount.class, id);

    }

    @Override
    public Account saveOrUpdate(Account modelObject) {
        try {

            Integer id = null;

            if (modelObject.getId() != null && (modelObject.getId()) != null) {
                id = update(modelObject);
            } else {
                id = insert(modelObject);
            }

            //modelObject.setId(id);
            return modelObject;

        } catch (SQLException e) {
            throw new TransactionException();
        }
    }

    @Override
    public void delete(Integer id) {
        em.remove(findById(id));
    }

    private Integer update(Account account) throws SQLException {

        Account accountBD=em.merge(account);

        return accountBD.getId();

    }


    private Integer insert(Account account) throws SQLException {

        em.persist(account);
        return account.getId();

    }

}
