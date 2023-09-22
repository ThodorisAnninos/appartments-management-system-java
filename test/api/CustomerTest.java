package api;

import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer c;
    private Accommodation a,b;
    private Rating rt1,rt2;
    private Host h;

    @Before
    public void setUp() throws Exception {
        h = new Host("name", "surname", "hostToTestRatingsScore", "pass");
        Register r1 = new Register(h, "pass");
        r1.addUser();

        a = new Accommodation(h, "accTestRatingsScore", AccommodationConstants.Διαμέρισμα.getI(), "address", "city", 1111, "h perigrafi mou", new Facilities());
        b = new Accommodation(h, "accTestRatingsScore", AccommodationConstants.Διαμέρισμα.getI(), "address", "city", 1111, "h perigrafi mou", new Facilities());
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
    }


    @Test
    public void getMyRatings() {
        ArrayList<Rating> rs = new ArrayList<>();
        rs.add(rt1);
        rs.add(rt2);

        assertEquals(rs, c.getMyRatings());
    }

    @Test
    public void getMyAccommodations() {
        assertTrue(c.getMyAccommodations().contains(a) && c.getMyAccommodations().contains(b));
    }

    @Test
    public void getMyRating() {
        assertEquals(2,c.getMyRating(a).getStars());
        assertEquals("desc",c.getMyRating(a).getDescription());
    }

    @Test
    public void getMyScore() {
        assertEquals(3.0,c.getMyScore(),0.1);
    }
}