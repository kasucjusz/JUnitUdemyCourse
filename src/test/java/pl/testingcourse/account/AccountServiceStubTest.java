package pl.testingcourse.account;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class AccountServiceStubTest {

  @Test
  void getAllActiveAccounts(){
    //given
    AccountRepository accountRepositoryStub=new AccountRepositoryStub();
    AccountService accountService = new AccountService(accountRepositoryStub);

    //when
    List<Account> accountList = accountService.getAllActiveAccounts();

    //then
    assertThat(accountList, hasSize(2));

  }

}