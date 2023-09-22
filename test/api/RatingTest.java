package api;

import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RatingTest {

    private Host h;
    private Customer c;
    private Facilities f;
    private Accommodation a;
    private Rating r;
    @Before
    public void setUp() throws Exception {

        h = new Host("name", "surname", "hostToTestAccommodations", "pass");
        Register r1 = new Register(h, "pass");
        r1.addUser();

        f = new Facilities();
        f.addView("Θέα σε πισίνα");
        f.addView("Θέα στο βουνό");
        f.addBathroom("Πιστολάκι μαλλιών");
        f.addAirConditioning("Εσωτερικό τζάκι");
        f.addOutdoors("Αυλή");

        a = new Accommodation(h, "accTest", AccommodationConstants.Διαμέρισμα.getI(), "address", "city", 1111, "h perigrafi mou", f);
        try {
            a.addAccommodation();
        } catch (EmptyInputException | UnexpectedError e){

        }
        c = new Customer("name", "surname", "customerToTestRatings", "pass");
        Register r2 = new Register(c, "pass");
        r2.addUser();

    }

    @Test
    public void addRating(){
        r = new Rating(c, a, 3, "perigrafi");
        try {
            r.addRating();
        } catch (EmptyInputException | UnexpectedError e){
            assertTrue(false);
        }

        assertTrue(a.getRatings().contains(r));
    }

    @Test
    public void editRating(){
        Rating r2 = new Rating(c, a, 4, "new desc");
        boolean b = false;
        try {
            b = r2.editRating(r);
        } catch (EmptyInputException | UnexpectedError e){

        }
        if (b){
            if (r.getStars() == 4 && r.getDescription().equals("new desc")){
                assertTrue(true);
            }
        }
    }

    @Test
    public void deleteRating(){
        addRating();
        boolean b = false;
        try{
            b = r.deleteRating();
        } catch (UnexpectedError e){

        }
        assertTrue(b);
    }
}