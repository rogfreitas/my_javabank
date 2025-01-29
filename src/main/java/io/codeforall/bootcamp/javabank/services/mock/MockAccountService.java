package io.codeforall.bootcamp.javabank.services.mock;

import io.codeforall.bootcamp.javabank.model.account.AbstractAccount;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.services.AccountService;

/**
 * A mock {@link AccountService} implementation
 */
public class MockAccountService extends AbstractMockService<AbstractAccount> implements AccountService {

    /**
     * @see AccountService#get(Integer)
     */
    @Override
    public AbstractAccount get(Integer id) {
        return modelMap.get(id);
    }

    /**
     * @see AccountService#add(AbstractAccount)
     */
    @Override
    public Integer add(AbstractAccount account) {

        if (account.getId() == null) {
            account.setId(getNextId());
        }

        modelMap.put(account.getId(), account);

        return account.getId();
    }

    /**
     * @see AccountService#deposit(Integer, double)
     */
    public void deposit(Integer id, double amount) {
        modelMap.get(id).credit(amount);
    }

    /**
     * @see AccountService#withdraw(Integer, double)
     */
    public void withdraw(Integer id, double amount) {

        AbstractAccount account = modelMap.get(id);
        if (account.getAccountType() == AccountType.SAVINGS) {
            return;
        }

        modelMap.get(id).debit(amount);
    }

    /**
     * @see AccountService#transfer(Integer, Integer, double)
     */
    public void transfer(Integer srcId, Integer dstId, double amount) {

        AbstractAccount srcAccount = modelMap.get(srcId);
        AbstractAccount dstAccount = modelMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
}
