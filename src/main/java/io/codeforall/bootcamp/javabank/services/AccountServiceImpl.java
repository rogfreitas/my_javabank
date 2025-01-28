package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.persistence.TransactionException;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import io.codeforall.bootcamp.javabank.model.account.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void setAccountDAO(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account get(Integer id) {
        EntityManager em=null;
        try {
            em= emf.createEntityManager();
            accountDao.setEm(em);
            em.getTransaction().begin();

            return accountDao.findById(id);
        } finally {
            em.getTransaction().commit();
            if(em!=null) em.close();
        }

    }

    @Override
    public void add(Account account) {
        EntityManager em=null;
        try {
            em= emf.createEntityManager();
            accountDao.setEm(em);
            em.getTransaction().begin();
            accountDao.saveOrUpdate(account);
            em.getTransaction().commit();
        } catch (TransactionException e) {
            em.getTransaction().rollback();
            if(em!=null) em.close();
        }

    }

    @Override
    public void deposit(int id, double amount) {
        EntityManager em=null;
        try {
            em= emf.createEntityManager();
            accountDao.setEm(em);
            em.getTransaction().begin();

            Account account = accountDao.findById(id);

            if (account == null) {
                throw new IllegalArgumentException("invalid account id");
            }

            account.credit(amount);
            accountDao.saveOrUpdate(account);
            em.getTransaction().commit();
        } catch (TransactionException e) {
            em.getTransaction().rollback();
            if(em!=null) em.close();
        }
    }

    @Override
    public void withdraw(int id, double amount) {
        EntityManager em=null;
        try {
            em= emf.createEntityManager();
            accountDao.setEm(em);
            em.getTransaction().begin();
            Account account = accountDao.findById(id);

            if (account == null) {
                throw new IllegalArgumentException("invalid account id");
            }

            account.debit(amount);
            accountDao.saveOrUpdate(account);
            em.getTransaction().commit();
        } catch (TransactionException e) {
            em.getTransaction().rollback();
            if(em!=null) em.close();
        }
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {
        EntityManager em=null;
        try {
            em= emf.createEntityManager();
            accountDao.setEm(em);
            em.getTransaction().begin();
            Account srcAccount = accountDao.findById(srcId);
            Account dstAccount = accountDao.findById(dstId);

            if (srcAccount == null || dstAccount == null) {
                throw new IllegalArgumentException("invalid account id");
            }

            if (srcAccount.canDebit(amount)) {
                srcAccount.debit(amount);
                dstAccount.credit(amount);

                accountDao.saveOrUpdate(srcAccount);
                accountDao.saveOrUpdate(dstAccount);
            }
            em.getTransaction().commit();
        } catch (TransactionException e) {
            em.getTransaction().rollback();
            if(em!=null) em.close();
        }
    }


}
