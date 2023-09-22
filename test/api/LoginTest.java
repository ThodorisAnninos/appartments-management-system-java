package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void tryingToLoginWithEmptyFields() {
        Login l = new Login("", "pass");
        assertNull(l.login());
        for (Error error : l.getErrors()) {
            assertEquals(0, error.getErrorCode());
        }
    }

    @Test
    public void tryingToLoginWithUserThatDoesntExists() {
        Login l = new Login("usernameThatDoesntExistsblablabla", "pass");
        assertNull(l.login());
        for (Error error : l.getErrors()) {
            assertEquals(1, error.getErrorCode());
        }
    }

    @Test
    public void tryingToLoginWithWrongPassword() {
        Host h = new Host("name", "surname", "username", "pass");
        Register r = new Register(h, "pass");
        r.addUser();
        Login l = new Login("username", "wrongPass");
        assertNull(l.login());
        for (Error error : l.getErrors()) {
            assertEquals(2, error.getErrorCode());
        }
    }

    @Test
    public void tryingToLogin(){
        Host h = new Host("name", "surname", "usernameToLog", "pass");
        Register r = new Register(h, "pass");
        r.addUser();
        Login l = new Login("usernameToLog", "pass");
        assertEquals(h, l.login());
    }
}