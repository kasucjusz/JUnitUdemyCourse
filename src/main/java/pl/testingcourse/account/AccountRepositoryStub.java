package pl.testingcourse.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository {

  @Override
  public List<Account> getAllAccounts() {
    Address address= new Address("Adres testowy ", "23");
    Address address2= new Address("Adres testowy ", "24");
    Account account = new Account(address);
    Account account2 = new Account(address2);
    Account account3 = new Account();

    return Arrays.asList(account, account2, account3);

  }
}
