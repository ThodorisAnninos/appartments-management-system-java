package api;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Child class of User with the functionalities of a host type user.
 */
public class Host extends User implements Serializable {
    private ArrayList<Accommodation> myAccommodations;


    public Host(String name, String surname, String username, String password) {
        super(name, surname, username, password, 2);
    }


    /**
     * This method fetches all accommodations from the database and keeps in an ArrayList the accommodations that the specific user has submitted.
     * @return the ArrayList of user's accommodations.
     */
    @Override
    public ArrayList<Accommodation> getMyAccommodations() {
        myAccommodations = new ArrayList<>();
        for (Accommodation accommodation : Database.getAccommodations()) {
            if (accommodation.getHost().equals(this)){
                myAccommodations.add(accommodation);
            }
        }
        return myAccommodations;
    }

    /**
     * This method returns the average rating of all the ratings that a host owns.
     * @return the score as a float.
     */
    @Override
    public float getMyScore(){
        float sum = 0;
        int count = 0;
        for (Accommodation accommodation : Database.getAccommodations()) {
            if (accommodation.getHost().equals(this)){
                sum += accommodation.getAccommodationRating();
                count++;
            }
        }
        if (count != 0) return sum/count;
        return 0;
    }

    /**
     * This method counts the ratings of the accommodations that a host owns.
     * @return
     */
    public int getAmountOfRatings(){
        int count = 0;
        for (Accommodation accommodation : Database.getAccommodations()) {
            if (accommodation.getHost().equals(this)){
                count += accommodation.getRatings().size();
            }
        }
        return count;
    }

}
