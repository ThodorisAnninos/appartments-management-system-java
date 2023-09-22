package api;

import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;

import java.io.Serializable;
import java.util.ArrayList;

import static api.Database.isEmpty;

/**
 * This class includes all the accommodation's functions.
 * Creates, edits, deletes an accommodation object.
 */
public class Accommodation implements Serializable {
    private String name;
    /**
     * 1: ξενοδοχείο
     * 2: διαμέρισμα
     * 3: μεζονέτα
     * You can use AccommodationConstants instead.
     */
    private int type;
    private String address;
    private String city;
    private int postalCode;
    private String description;
    private Facilities facilities;
    private Host host;
    private ArrayList<Rating> ratings;

    public Accommodation(Host host, String name, int type, String address, String city, int postalCode, String description, Facilities facilities) {
        this.host = host;
        this.name = name;
        this.type = type;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.description = description;
        this.facilities = facilities;

    }

    public Host getHost() {
        return host;
    }

    /**
     * This method returns the ratings of a specific accommodation.
     * @return the ArrayList with the ratings.
     */
    public ArrayList<Rating> getRatings(){
        ratings = new ArrayList<>();
        for (Rating rating : Database.getRatings()) {
            if (rating.getAccommodation().equals(this)){
                ratings.add(rating);
            }
        }
        return ratings;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getDescription() {
        return description;
    }

    public Facilities getFacilities() {
        return facilities;
    }

    /**
     * This method checks if any of the required fields user submitted is empty.
     * @return boolean.
     */
    private boolean checkForEmptyFields(){
        return (isEmpty(name) || isEmpty(type) || isEmpty(address) || isEmpty(city) || isEmpty(postalCode)  || isEmpty(description));
    }

    /**
     * This method adds the accommodation object to the database.
     * @return boolean if added or not.
     */
    public boolean addAccommodation() throws EmptyInputException, UnexpectedError {
        if (checkForEmptyFields()) throw new EmptyInputException("Κάποιο από τα υποχρεωτικά πεδία είναι κενά.");
        if (Database.insert(this))
            return true;
        throw new UnexpectedError("Σφάλμα κατά την προσθήκη καταλύματος.");
    }

    /**
     * This method returns the average rating of the specific accommodation.
     * @return the average rating.
     */
    public float getAccommodationRating(){
        float sum = 0;
        int count = 0;
        for (Rating rating : Database.getRatings()) {
            if (rating.getAccommodation().equals(this)){
                sum += rating.getStars();
                count++;
            }
        }
        if (count != 0) return sum/count;
        else return 0;
    }

    /**
     * This method finds the accommodation object and overrides it with the new one.
     * @param a the old accommodation object.
     * @return boolean if edited or not.
     */
    public boolean editAccommodation(Accommodation a) throws EmptyInputException, UnexpectedError {
        if (checkForEmptyFields()) throw new EmptyInputException("Κάποιο από τα υποχρεωτικά πεδία είναι κενά.");
        int index;
        if (Database.getAccommodations().contains(a)){
            index = Database.getAccommodations().indexOf(a);
            Database.getAccommodations().set(index, this);
            for (Rating rating : Database.getRatings()) {
                if (rating.getAccommodation().equals(a)){
                    rating.setAccommodation(this);
                }
            }
            return true;
        }
        throw new UnexpectedError("Σφάλμα κατά την επεξεργασία καταλύματος.");
    }

    /**
     * This method deletes the accommodation object from the database.
     * @return boolean if deleted or not.
     */
    public boolean deleteAccommodation() throws UnexpectedError {
        if (Database.delete(this)){
            return true;
        }
        throw new UnexpectedError("Σφάλμα κατά τη διαγραφή καταλύματος.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accommodation that = (Accommodation) o;
        return type == that.type && postalCode == that.postalCode && name.equals(that.name) && address.equals(that.address) && city.equals(that.city) && description.equals(that.description) && host.equals(that.host);
    }

}
