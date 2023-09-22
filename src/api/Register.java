package api;

import java.io.Serializable;
import java.util.ArrayList;

public class Register implements Serializable {
    private User newUser;
    private String repeat_password;
    private ArrayList<Error> errors = new ArrayList<>();

    public Register(User newUser, String repeat_password) {
        this.newUser = newUser;
        this.repeat_password = repeat_password;
    }

    /**
     * This method checks if the password and repeated password have the same value.
     * @return
     */
    private boolean validatePassword() {
        if (newUser.getPassword().equals(repeat_password)) {
            return true;
        }
        //throw new WrongInputException("Ο κωδικός δεν ταυτίζεται!");
        errors.add(new Error(0, "Ο κωδικός δεν ταυτίζεται!"));
        return false;
    }

    private boolean emptyInput() { // θα δούμε
        if (newUser.emptyField() || repeat_password.equals("")) { // θέλει έλεγχο με την κενή συμβολοσειρά και όχι με null
            //throw new EmptyInputException("Κάποιο από τα υποχρεωτικά πεδίο είναι κενό");
            errors.add(new Error(1, "Κάποιο από τα υποχρεωτικά πεδίο είναι κενό"));
            return true;
        }
        return false;
    }

    /**
     * This method makes all validations and returns true or false if the user can register and access the dashboard or not.
     */
    public User addUser() {
        if (!emptyInput() && validatePassword()) {
            if (Database.insert(newUser)) {
                return newUser;
            }
            else {
                //throw new AlreadyExistsException("Το όνομα χρήστη χρησιμοποιείται ήδη!");
                errors.add(new Error(2, "Το όνομα χρήστη χρησιμοποιείται ήδη!"));
            }
        }
        return null;
    }

    /**
     * This method is used to return all errors after user tries to register in the system with the appropriate message.
     */
    public ArrayList<Error> getErrors() {
        return errors;
    }

}
