package project.ece301.mantracker;

import android.os.Debug;

import junit.framework.Assert;

import org.junit.Test;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest {
    @Test
    public void testSetEmail() {
        Account acc = new Account();
        try {
            Email email = new Email("Hellothere@gmail.com");
            acc.setEmail(email);
            assertEquals(acc.getEmail(), email);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception: " + e);
        }
    }

    @Test
    public void testSetPhone() {
        Account acc = new Account();
        String phoneNum = "780-123-4566";
        acc.setPhone(phoneNum);
        assertEquals(acc.getPhone(), phoneNum);
    }

    @Test
    public void testSetUsername() {
        try {
            Account acc = new Account();
            Username username = new Username("userid889089");
            acc.setUsername(username);
            assertEquals(acc.getUsername(), username);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception: " + e);
        }
    }

    @Test
    public void testGetEmail() {
        try {
            Email email = new Email("Hellothere@gmail.com");
            Account acc = new Account(email, new Username("user679896"), "123-456-7890");
            assertEquals(acc.getEmail(), email);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception: " + e);
        }
    }

    @Test
    public void testGetPhone() {
        try {
            String phString = "123-456-7890";
            Account acc = new Account(new Email("username@gmail.com"),
                    new Username("user5685897"), phString);
            assertEquals(acc.getPhone(), phString);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception: " + e);
        }
    }

    @Test
    public void testGetUsername() {
        try {
            Username username = new Username("userid3840ff");
            Account acc = new Account(new Email("Hi@gmail.com"), username, "123-456-7890");
            assertEquals(username, acc.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception: " + e);
        }
    }
}
