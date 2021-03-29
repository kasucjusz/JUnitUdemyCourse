package pl.testingcourse.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pl.testingcourse.account.Account;
import pl.testingcourse.account.Address;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AccountTest {

    @Test
    public void newAccountShouldNotBeActiveAfterCreation(){
        //given
        //when
        Account account=new Account();

        //then
        assertFalse(account.isActive(), "check if new acc is now active");
        assertThat(account.isActive(), equalTo(false));
        assertThat(account.isActive(), is(false));

    }


    @Test
    public void accountShouldBeActiveAfterActivation(){
        //given
        Account account=new Account();

        //when
        account.activate();

        //then
        assertTrue(account.isActive());
        assertThat(account.isActive(), equalTo(true));

    }

    @Test
    void newlyCreateAccountShouldNotHaveDefaultDeliveryAddressSet(){

        //given
        Account account = new Account();

        //when
        Address address= account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address, nullValue());
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){

        //given
        Address address= new Address("Krakowska", "67 C");
        Account account=new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddress=account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultAddress);
        assertThat(address, notNullValue());
    }


    @RepeatedTest(5)
    void newAccountWithNotNUllAddressShouldBeActive(){
        //given
        Address address = new Address("Puławska", "46/6");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address==null, () -> {
            assertTrue(account.isActive());
        });
    }

}
