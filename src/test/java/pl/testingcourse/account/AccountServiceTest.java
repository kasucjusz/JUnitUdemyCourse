package pl.testingcourse.account;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AccountServiceTest {

  @Test
  void getAllActiveAccounts() {


    // given
    List<Account> accounts = prepareAccountData();
    AccountRepository accountRepository = mock(AccountRepository.class);
    AccountService accountService = new AccountService(accountRepository);
    //when(accountRepository.getAllAccounts()).thenReturn(accounts);
    given(accountRepository.getAllAccounts()).willReturn(accounts);



    // when
    List<Account> accountList = accountService.getAllActiveAccounts();

    // then
    assertThat(accountList, hasSize(2));
  }

  @Test
  void getNoActiveAccounts() {


    // given
    List<Account> accounts = prepareAccountData();
    AccountRepository accountRepository = mock(AccountRepository.class);
    AccountService accountService = new AccountService(accountRepository);
    //when(accountRepository.getAllAccounts()).thenReturn(accounts);
    given(accountRepository.getAllAccounts()).willReturn(Collections.emptyList());



    // when
    List<Account> accountList = accountService.getAllActiveAccounts();

    // then
    assertThat(accountList, hasSize(0));
  }

  private List<Account> prepareAccountData(){
    Address address= new Address("Adres testowy ", "23");
    Address address2= new Address("Adres testowy ", "24");
    Account account = new Account(address);
    Account account2 = new Account(address2);
    Account account3 = new Account();

    return Arrays.asList(account, account2, account3);

  }
}
