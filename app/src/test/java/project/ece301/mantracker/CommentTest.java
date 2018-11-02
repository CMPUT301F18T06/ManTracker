package project.ece301.mantracker;

import org.junit.Test;

import java.util.Date;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Comment;
import project.ece301.mantracker.Account.Username;

import static org.junit.Assert.assertEquals;

/**
 * The type Comment test.
 */
public class CommentTest {

    @Test
    public void setDateTest() {
        Date date = new Date(2018, 4, 10, 8,30,30);
        Comment c = new Comment(date, new Account(), "I am a comment");
        c.setDate("2018-03-10 08:30:30"); //yyyy-MM-dd HH:mm:ss
        assertEquals("2018-03-10 08:30:30", c.getDateAsString());
    }

    @Test
    public void getDateAsStringTest() {
        Date date = new Date(2018-1900, 4-1, 10, 8,30,30);
        Comment c = new Comment(date, new Account(), "I am a comment");
        assertEquals("2018-04-10 08:30:30", c.getDateAsString());
    }

    @Test
    public void toStringTest() {
        Comment c = new Comment(new Account(), "I am a comment");
        c.setDate("2018-03-10 08:30:30"); //yyyy-MM-dd HH:mm:ss
        assertEquals("2018-03-10 08:30:30 | " + c.getUserID() + " | I am a comment", c.toString());
    }
}
