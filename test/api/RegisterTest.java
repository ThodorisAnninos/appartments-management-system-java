package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    // + έλεγχος αν βάλουμε exceptions ή getError()

    @Test
    public void addingUserWithEmptyField() {
        Host h = new Host("name", "", "username", "pass");
        Register r = new Register(h, "pass");
        assertNull(r.addUser());
        for (Error error : r.getErrors()) {
            assertTrue(error.getErrorCode() == 1);
        }
    }

    @Test
    public void addingUserWithWrongRepeatedPassword() {
        Host h = new Host("name", "surname", "username", "pass");
        Register r = new Register(h, "passssss");
        assertNull(r.addUser());
        for (Error error : r.getErrors()) {
            assertTrue(error.getErrorCode() == 0);
        }
    }

    @Test
    public void addingUser() {
        Host h = new Host("name", "surname", "usernameToReg", "pass");
        Register r = new Register(h, "pass");
        User u = r.addUser();
        assertEquals(h, u);

    }

    @Test
    public void addingUsernameWithExistedUsername() {
        Host h = new Host("name", "surname", "username", "pass");
        Register r = new Register(h, "pass");
        assertNull(r.addUser());
        for (Error error : r.getErrors()) {
            assertTrue(error.getErrorCode() == 2);
        }
    }

}