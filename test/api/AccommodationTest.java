package api;

import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AccommodationTest {

    private Host h;
    private Facilities f;
    private Accommodation a;

    @Before
    public void setUp() throws Exception {
        h = new Host("name", "surname", "hostToTestAccommodations", "pass");
        Register r = new Register(h, "pass");
        r.addUser();

        f = new Facilities();
        f.addView("Θέα σε πισίνα");
        f.addView("Θέα στο βουνό");
        f.addBathroom("Πιστολάκι μαλλιών");
        f.addAirConditioning("Εσωτερικό τζάκι");
        f.addOutdoors("Αυλή");
        f.addInternet("Ethernet");
        f.addKitchen("Ψυγείο");
        f.addEntertainment("Τηλεόραση");
        f.addLaundry("Στεγνωτήριο");
        f.addParking("Δωρεάν χώρος στάθμεσης στην ιδιοκτησία");

        a = new Accommodation(h, "accTest", AccommodationConstants.Διαμέρισμα.getI(), "address", "city", 1111, "h perigrafi mou", f);
        try {
            a.addAccommodation();
        } catch (EmptyInputException | UnexpectedError e){

        }
    }

    @Test
    public void addAccommodationWithEmptyFields(){
        f = new Facilities();
        f.addView("Θέα σε πισίνα");
        f.addView("Θέα στο βουνό");
        f.addBathroom("Πιστολάκι μαλλιών");
        f.addAirConditioning("Εσωτερικό τζάκι");
        f.addOutdoors("Αυλή");

        boolean b = true;

        a = new Accommodation(h, "", AccommodationConstants.Διαμέρισμα.getI(), "", "city", 1111, "h perigrafi mou", f);

        try {
            b = a.addAccommodation();
        } catch (EmptyInputException e){
            assertTrue(true);
        } catch (UnexpectedError e){

        }
        assertTrue(b);
    }

    @Test
    public void addAccommodation() {

        assertTrue(Database.getAccommodations().contains(a));

    }


    @Test
    public void editAccommodation() {
        f = new Facilities();
        f.addView("Θέα σε πισίνα");
        f.addView("Θέα στο βουνό");
        f.addBathroom("Πιστολάκι μαλλιών");
        f.addAirConditioning("Εσωτερικό τζάκι");
        f.addOutdoors("Αυλή");

        Accommodation b = new Accommodation(h, "edittedAcc", AccommodationConstants.Μεζονέτα.getI(), "address", "city", 1111, "nea perigrafi", f);

        boolean bool = false;
        try {
            bool = b.editAccommodation(a);
        } catch (EmptyInputException | UnexpectedError e){
            assertTrue(false);
        }

        if (bool){
            if (a.getName().equals("edittedAcc"))
                assertTrue(true);
        }
    }


    @Test
    public void deleteAccommodation() {
        boolean b = false;

        try {
            b = a.deleteAccommodation();
        } catch (UnexpectedError e){

        }
        assertTrue(b);
    }

    @Test
    public void getHost() {
        assertSame(a.getHost(), h);
    }

    @Test
    public void getType() {
        assertEquals(a.getType(), AccommodationConstants.Διαμέρισμα.getI());
    }

    @Test
    public void getAddress() {
        assertEquals("address", a.getAddress());
    }

    @Test
    public void getCity() {
        assertEquals("city", a.getCity());
    }

    @Test
    public void getPostalCode() {
        assertEquals(1111, a.getPostalCode());
    }

    @Test
    public void getDescription() {
        assertEquals("h perigrafi mou",a.getDescription());
    }

}