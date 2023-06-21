package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Account;

public class AccountTest {
    private Account account;

    @Before
    public void setup() {
        account = new Account("123456789", "John Doe", "1234", 1000.0f);
    }

    @Test
    public void testGettersAndSetters() {
        // Assert initial values
        Assert.assertEquals(0, account.getIduser());
        Assert.assertEquals("123456789", account.getAccountNumber());
        Assert.assertEquals(1000.0f, account.getBalance(), 0.001);
        Assert.assertEquals("John Doe", account.getName());
        Assert.assertEquals("1234", account.getPin());

        // Set new values
        account.setIduser(1);
        account.setAccountNumber("987654321");
        account.setBalance(2000.0f);
        account.setName("Jane Doe");
        account.setPin("5678");

        // Assert new values
        Assert.assertEquals(1, account.getIduser());
        Assert.assertEquals("987654321", account.getAccountNumber());
        Assert.assertEquals(2000.0f, account.getBalance(), 0.001);
        Assert.assertEquals("Jane Doe", account.getName());
        Assert.assertEquals("5678", account.getPin());
    }

    @Test
    public void testToString() {
        String expected = "Account [iduser=0, accountNumber=123456789, balance=1000.0, name=John Doe, pin=1234]";
        String actual = account.toString();
        Assert.assertEquals(expected, actual);
    }
}
