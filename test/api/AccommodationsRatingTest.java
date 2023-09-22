package api;

import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AccommodationsRatingTest {

    private Host h;
    private Customer c1, c2;
    private Facilities f;
    private Accommodation a;
    private Rating rt1, rt2;

    @Before
    public void setUp() throws UnexpectedError, EmptyInputException {
        h = new Host("name", "surname", "hostToTestRatingsScore", "pass");
        Register r1 = new Register(h, "pass");
        r1.addUser();

        f = new Facilities();
        f.addView("Θέα σε πισίνα");
        f.addView("Θέα στο βουνό");
        f.addBathroom("Πιστολάκι μαλλιών");
        f.addAirConditioning("Εσωτερικό τζάκι");
        f.addOutdoors("Αυλή");

        a = new Accommodation(h, "accTestRatingsScore", AccommodationConstants.Διαμέρισμα.getI(), "address", "city", 1111, "h perigrafi mou", f);
        try {
            a.addAccommodation();
        } catch (EmptyInputException | UnexpectedError e){

        }
        c1 = new Customer("name", "surname", "customerToTestRatings1", "pass");
        Register r2 = new Register(c1, "pass");
        r2.addUser();
        c2 = new Customer("name", "surname", "customerToTestRatings2", "pass");
        Register r3 = new Register(c2, "pass");
        r3.addUser();

        rt1 = new Rating(c1, a, 3, "desc1");
        rt2 = new Rating(c2, a, 5, "desc2");

        rt1.addRating();
        rt2.addRating();

    }

    @After
    public void tearDown() throws Exception {
        rt1.deleteRating();
        rt2.deleteRating();
    }

    @Test
    public void getRatings() throws UnexpectedError {

        ArrayList<Rating> rs = new ArrayList<>();
        rs.add(rt1);
        rs.add(rt2);

        assertEquals(rs, a.getRatings());
    }

    @Test
    public void getAccScore() throws UnexpectedError {
        assertEquals((3+5)/2.0, a.getAccommodationRating(), 0.1);
    }

}
