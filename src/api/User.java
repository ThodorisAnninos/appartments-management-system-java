package api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public abstract class User implements Serializable {
    private String name;
    private String surname;
    private String username;
    private String password;
    /**
     * An integer value that stores:
     * 1: simple user
     * 2: host
     */
    private int role;

    public User(String name, String surname, String username, String password, int role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public boolean isHost() {
        return role==2;
    }

    public boolean isUser() {
        return role==1;
    }

    /**
     * These  two methods (equals and hashCode) are autogenerated from IntelliJ IDE to override equals comparison method in set data structure.
     * It compares usernames, because they have to be unique in our "Database".
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    /**
     * These two methods (equals and hashCode) are autogenerated from IntelliJ IDE to override equals comparison method in set data structure.
     * It compares usernames, because they have to be unique in our "Database".
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }


//    @Override
//    public boolean equals(Object obj) {
//        User u = (User)obj;
//        if (username.equals(u.getUsername()))
//            return true;
//        return false;
//    }


    /**
     * This method checks if a username already exists.
     * @param username the username to check.
     * @return a copy of that object with all user data or null if condition is false.
     */
    public User usernameExists(String username) {
        if (this.username.equals(username)){
            return this;
        }
            //return new User(name, surname, username, password, role);
        return null;
    }

    public boolean emptyField() {
        if (this.name.equals("") || this.surname.equals("") || this.username.equals("") || this.password.equals("") || role == 0)
            return true;
        return false;
    }

    public abstract ArrayList<Accommodation> getMyAccommodations();

    public abstract float getMyScore();

}
