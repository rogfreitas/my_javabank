package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.model.account.AbstractAccount;
import io.codeforall.bootcamp.javabank.persistence.dao.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaSessionManager;

/**
 * A JPA {@link AccountDao} implementation
 */
public class JpaAccountDao extends GenericJpaDao<AbstractAccount> implements AccountDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(JpaSessionManager, Class)
     */
    public JpaAccountDao(JpaSessionManager sm) {
        super(sm, AbstractAccount.class);
    }
}
