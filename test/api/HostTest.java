package api;

import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HostTest {

    private Customer c;
    private Accommodation a,b;
    private Rating rt1,rt2;
    private Host h;

    @Before
    public void setUp() throws Exception {
        h = new Host("name", "surname", "hostTest", "pass");
        Register r1 = new Register(h, "pass");
        r1.addUser();

        a = new Accommodation(h, "accTestHost", AccommodationConstants.Διαμέρισμα.getI(), "address", "city", 1111, "h perigrafi mou", new Facilities());
        b = new Accommodation(h, "accTestSHost2", AccommodationConstants.Διαμέρισμα.getI(), "address", "city", 1111, "h perigrafi mou", new Facilities());
        try {
            a.addAccommodation();
            b.addAccommodation();
        } catch (EmptyInputException | UnexpectedError e){

        }
        c = new Customer("name", "surname", "customerToTestRatings1", "pass");
        Register r2 = new Register(c, "pass");
        r2.addUser();

        rt1 = new Rating(c, a, 2, "desc");
        rt2 = new Rating(c, b, 4, "desc");

        rt1.addRating();
        rt2.addRating();
    }

    @After
    public void tearDown() throws Exception {
        rt1.deleteRating();
        rt2.deleteRating();
        a.deleteAccommodation();
        b.deleteAccommodation();
    }

    @Test
    public void getMyAccommodations() {
        ArrayList<Accommodation> ac = new ArrayList<>();
        ac.add(a);
        ac.add(b);

        assertEquals(ac, h.getMyAccommodations());
    }

    @Test
    public void getMyScore() {
        assertEquals(3.0,h.getMyScore(),0.1);
    }

    @Test
    public void getAmountOfRatings() {
        assertEquals(2,h.getAmountOfRatings());
    }
}