package io.codeforall.bootcamp.javabank.view;

import io.codeforall.bootcamp.javabank.services.AccountServiceImp;
import io.codeforall.bootcamp.javabank.services.AuthServiceImp;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImp;
import org.academiadecodigo.bootcamp.Prompt;

/**
 * A generic view to be used as a base for concrete view implementations
 * @see View
 */
public abstract class AbstractView implements View {

    protected Prompt prompt;
    protected CustomerServiceImp customerServiceImp;
    protected AuthServiceImp authServiceImp;

    /**
     * Sets the prompt used for the UI
     *
     * @param prompt the prompt to set
     */
    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public void setCustomerServiceImp(CustomerServiceImp customerServiceImp){
        this.customerServiceImp=customerServiceImp;
    }

    public void setAuthServiceImp(AuthServiceImp authServiceImp){
        this.authServiceImp=authServiceImp;
    }


}
