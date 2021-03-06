package project.ece301.mantracker;

import org.junit.Test;

import project.ece301.mantracker.Account.Username;

import static org.junit.Assert.assertEquals;

/**
 * The type Username test.
 */
public class UsernameTest {
    /**
     * Is valid test.
     */
    @Test
    public void isValidTest() {
        assertEquals(false, Username.isValid("the8"));
        assertEquals(false, Username.isValid("thename 8"));
        assertEquals(false, Username.isValid("thesfse?8"));
        assertEquals(false, Username.isValid("the<>eee8"));
    }

    /**
     * Is unique test.
     */
    @Test
    public void isUniqueTest(){
        boolean valid = true;

        try {
            Username name = new Username("thename8");
            valid = Username.isValid("thename8");
        } catch (Username.InvalidUsernameException e) {

        }
        assertEquals(false, valid);
    }
}

