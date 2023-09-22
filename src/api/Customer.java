package api;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Child class of User with the functionalities of a simple type user.
 */
public class Customer extends User implements Serializable{
    private ArrayList<Rating>myRatings;
    private ArrayList<Accommodation> myRatedAccommodations;

    public Customer(String name, String surname, String username, String password) {
        super(name, surname, username, password, 1);
        myRatings = new ArrayList<>();
    }

    /**
     * This method fetches all ratings from the database and keeps in an ArrayList the ratings that the specific user has submitted.
     * @return the ArrayList of user's ratings.
     */
    public ArrayList<Rating> getMyRatings(){
        for (Rating rating : Database.getRatings()) {
            if (rating.getCustomer().equals(this)){
                myRatings.add(rating);
            }
        }
        return myRatings;
    }

    /**
     * This method fetches all accommodations from the database and keeps in an ArrayList the accommodation that the specific user has rated.
     * @return the ArrayList of user's rated accommodations.
     */
    @Override
    public ArrayList<Accommodation> getMyAccommodations(){
        myRatedAccommodations = new ArrayList<>();
        for (Rating rating : Database.getRatings()) {
            if (rating.getCustomer().equals(this)){
                myRatedAccommodations.add(rating.getAccommodation());
            }
        }
        return myRatedAccommodations;
    }

    /**
     * This method returns the rating that the current customer has submitted for a specific accommodation.
     * @param a the accommodation we want to get the rating.
     * @return the user's rating.
     */
    public Rating getMyRating(Accommodation a) {
        for (Rating rating : Database.getRatings()) {
            if (rating.getCustomer().equals(this)){
                if (rating.getAccommodation().equals(a)) return rating;}
        }
        return null;
    }

    /**
     * This method returns the average rating score that the current customer has submitted for accommodations.
     * @return that score or zero if the customer hasn't rated accommodations yet..
     */
    @Override
    public float getMyScore(){
        float sum = 0;
        int count = 0;
        for (Rating rating : Database.getRatings()) {
            if (rating.getCustomer().equals(this)){
                sum += rating.getStars();
                count++;
            }
        }
        if (count != 0) return sum/count;
        return 0;
    }
}
