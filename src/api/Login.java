package api;

import java.io.Serializable;
import java.util.ArrayList;

public class Login implements Serializable {
    private String username;
    private String password;
    private User correctUser;
    private ArrayList<Error> errors = new ArrayList<>();

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private boolean emptyInput(){
        if (username.equals("") || password.equals("")){
            //throw new EmptyInputException("Κάποιο από τα υποχρεωτικά πεδία είναι κενό");
            errors.add(new Error(0,"Κάποιο από τα υποχρεωτικά πεδία είναι κενό"));
            return true;
        }
        return false;
    }

    /**
     * This method checks if the username of the user who tries to login exists in "Database" and if yes it stores a copy of the correct user info.
     * @return boolean if username exists or not.
     */
    private boolean validateUsername() {
        for (User user : Database.getUsers()) {
            if (user.usernameExists(this.username) != null){
                correctUser = user;
                return true;
            }
        }
        //throw new DoesntExistsException("Το όνομα χρήστη που πληκτρολογήσατε δεν υπάρχει!");
        errors.add(new Error(1, "Το όνομα χρήστη που πληκτρολογήσατε δεν υπάρχει!"));
        return false;
    }

    /**
     * This method checks if the login password is the same as in the "Database" object.
     * @return boolean if password matches or not.
     */
    private boolean validatePassword() {
        if (correctUser != null && correctUser.getPassword().equals(this.password))
            return true;
        //throw new WrongInputException("Ο κωδικός που πληκτρολογήσατε είναι λανθασμένος!");
        errors.add(new Error(2, "Ο κωδικός που πληκτρολογήσατε είναι λανθασμένος!"));
        return false;
    }

    /**
     * This method is used to return all errors after user tries to login in the system with the appropriate message.
     */
    public ArrayList<Error> getErrors() {
        return errors;
    }

    /**
     * This method makes all validations and returns true or false if the user can login and access the dashboard or not.
     */
    public User login() {
        if (!emptyInput() && validateUsername() && validatePassword())
            return correctUser;
        return null;
    }
}
