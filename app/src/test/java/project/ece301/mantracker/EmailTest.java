package project.ece301.mantracker;

import org.junit.Test;

import project.ece301.mantracker.Account.Email;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class EmailTest {
    @Test
    public void test_getEmail(){
        Email email = null;
        try {
            email = new Email("foo@example.com");
        } catch (Email.InvalidEmailException e) {
            e.printStackTrace();
        }

        assertEquals("foo@example.com", email.getEmail());
    }

    @Test
    public void isValidTest() {
        assertEquals(false, Email.isValid(".the8@gsg.c"));
    }
}

