package io.codeforall.bootcamp.javabank;

public class Test_old {

    public static void main(String[] args) {

        Test_old test = new Test_old();
        test.testAccount();
        test.testAuthService();
        test.testCustomerService();
        test.testAccountService();

    }

    private static String getResult(boolean result) {
        return result ? "OK" : "FAIL";
    }

    private void testAccount() {

        CheckingAccountTest_old checkingAccountTest = new CheckingAccountTest_old();
        SavingsAccountTest_old savingsAccountTest = new SavingsAccountTest_old();
        System.out.println("Checking Account: " + Test_old.getResult(checkingAccountTest.test()));
        System.out.println("Savings Account: " + Test_old.getResult(savingsAccountTest.test()));

    }

    private void testAuthService() {
        AuthServiceTest_old authServiceTest = new AuthServiceTest_old();
        System.out.println("AuthService: " + Test_old.getResult(authServiceTest.test()));
    }

    private void testCustomerService() {
        CustomerServiceTest_old customerServiceTest = new CustomerServiceTest_old();
        System.out.println("Customer: " + Test_old.getResult(customerServiceTest.test()));
    }

    private void testAccountService() {
        AccountServiceTest_old accountServiceTest = new AccountServiceTest_old();
        System.out.println("AccountService: " + Test_old.getResult(accountServiceTest.test()));
    }
}
