package api;

import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdvancedAccommodationSearchTest {

    private Host h;
    private Facilities f1, f2, f;
    private Accommodation a, b;

    @Before
    public void setUp() throws Exception {
        h = new Host("name", "surname", "hostToTestRatingsScore", "pass");
        Register r1 = new Register(h, "pass");
        r1.addUser();

        f1 = new Facilities();
        f1.addView("Θέα σε πισίνα");
        f1.addView("Θέα στο βουνό");
        f1.addBathroom("Πιστολάκι μαλλιών");
        f1.addAirConditioning("Εσωτερικό τζάκι");
        f1.addOutdoors("Αυλή");

        a = new Accommodation(h, "accTestSearch1", AccommodationConstants.Διαμέρισμα.getI(), "address", "city", 1111, "h perigrafi mou", f1);

        f2 = new Facilities();
        f2.addView("Θέα σε πισίνα");
        f2.addBathroom("Πιστολάκι μαλλιών");
        f2.addOutdoors("Αυλή");

        b = new Accommodation(h, "accTestSearch2", AccommodationConstants.Διαμέρισμα.getI(), "address", "city", 1111, "h perigrafi mou", f2);
        try {
            a.addAccommodation();
            b.addAccommodation();
        } catch (EmptyInputException | UnexpectedError e){

        }
    }

    @After
    public void tearDown() throws Exception {
        a.deleteAccommodation();
        b.deleteAccommodation();
    }

    @Test
    public void searchByName(){
        AdvancedAccommodationSearch s = new AdvancedAccommodationSearch("accTestSearch1", 0, "", new Facilities());

        if (s.getResults().contains(a) && !s.getResults().contains(b))
            assertTrue(true);
        else
            assertTrue(false);
    }

    @Test
    public void searchByFacilities(){
        f = new Facilities();
        f.addView("Θέα σε πισίνα");
        f.addBathroom("Πιστολάκι μαλλιών");
        AdvancedAccommodationSearch s = new AdvancedAccommodationSearch("", 0, "", f);

        if (s.getResults().contains(a) && s.getResults().contains(b))
            assertTrue(true);
        else
            assertTrue(false);
    }


}