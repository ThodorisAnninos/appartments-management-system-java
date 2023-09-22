package api;

import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;

import java.io.Serializable;
import java.util.ArrayList;
import static api.Database.isEmpty;

public class Rating implements Serializable {
    private int stars;
    private String description;
    private Accommodation accommodation;
    private Customer customer;

    public Rating(Customer customer, Accommodation accommodation, int stars, String description) {
        this.customer = customer;
        this.accommodation = accommodation;
        this.stars = stars;
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public int getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }

    /**
     * This method checks if any of the required fields user submitted is empty.
     * @return boolean.
     */
    private boolean checkForEmptyFields(){
        return (isEmpty(stars) || isEmpty(description));
    }

    /**
     * This method adds a rating to a specific accommodation.
     * @return boolean if rating added or not.
     */
    public boolean addRating() throws EmptyInputException, UnexpectedError {
        if (checkForEmptyFields()) throw new EmptyInputException("Κάποιο από τα υποχρεωτικά πεδία είναι κενά.");
        if (Database.insert(this))
            return true;
        throw new UnexpectedError("Σφάλμα κατά την προσθήκη αξιολόγησης.");
    }

    /**
     * This method edits a rating by overriding the old one.
     * @param a the rating before edit.
     * @return boolean if edited or not.
     */
    public boolean editRating(Rating a) throws EmptyInputException, UnexpectedError {
        int index;
        if (checkForEmptyFields()) throw new EmptyInputException("Κάποιο από τα υποχρεωτικά πεδία είναι κενά.");
        if (Database.getRatings().contains(a)){
            index = Database.getRatings().indexOf(a);
            Database.getRatings().set(index, this);
            return true;
        }
        throw new UnexpectedError("Σφάλμα κατά την επεξεργασία καταλύματος");
    }

    /**
     * This method deletes the specific rating object.
     * @return boolean if deleted or not.
     */
    public boolean deleteRating() throws UnexpectedError {
        if (Database.getRatings().contains(this)){
            Database.getRatings().remove(this);
            return true;
        }
        throw new UnexpectedError("Σφάλμα κατά τη διαγραφή αξιολόγησης.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (stars != rating.stars) return false;
        if (!description.equals(rating.description)) return false;
        if (!accommodation.equals(rating.accommodation)) return false;
        return customer.equals(rating.customer);
    }

}