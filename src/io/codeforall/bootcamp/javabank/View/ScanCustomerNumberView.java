package io.codeforall.bootcamp.javabank.View;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;

import java.util.Set;

public class ScanCustomerNumberView {

    private Prompt prompt;

    public ScanCustomerNumberView(Prompt prompt){
        this.prompt=prompt;
    }

    public int scanCustomerId(Set<Integer> customerIds) {

       // IntegerSetInputScanner scanner = new IntegerSetInputScanner(customerIds);
        IntegerSetInputScanner scanner = new IntegerSetInputScanner();
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);
        int p=prompt.getUserInput(scanner);
        return p;
    }
}
