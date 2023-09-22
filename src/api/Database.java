package api;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Static class that stores all data (users, accommodations, ratings)
 * It's like a simulation of a realistic database with tables as its fields.
 */
public class Database implements Serializable{
    private static HashSet<User> users = new HashSet<>();
    private static ArrayList<Accommodation> accommodations = new ArrayList<>();
    private static ArrayList<Rating> ratings = new ArrayList<>();
    private static boolean loaded = false;


    /**
     * Create
     * Read
     * Update
     * Delete
     */

    /**
     * Takes an object as argument and adds it to the suitable data table.
     * @param obj the object to add
     * @return boolean if added or not
     */
    public static boolean insert(Object obj){
        if (obj instanceof User){
            if (users.add(((User) obj))) return true;
        }
        else if(obj instanceof Accommodation){
            if (accommodations.add((Accommodation) obj)) return true;
        }
        else if (obj instanceof Rating){
            if (ratings.add((Rating) obj)) return true;
        }
        return false;
    }

    public static HashSet<User> getUsers() {
        return users;
    }
    public static ArrayList<Rating> getRatings() {
        return ratings;
    }

    public static ArrayList<Accommodation> getAccommodations() {
        return accommodations;
    }

    /**
     * Takes a table name as an argument and returns the suitable data table.
     * @param table name of table
     * @return table or null
     * @param <T>
     */
    public static <T> T get(String table){
        if (table.equals("users")){
            return (T) users;
        } else if (table.equals("ratings")){
            return (T) ratings;
        } else if (table.equals("accommodations")){
            return (T) accommodations;
        } else {
            return null;
        }
    }

    /**
     * Takes two objects and updates the suitable table with obj2.
     * @param obj1 the old object
     * @param obj2 the new object
     * @return boolean if updated or not
     */
    public static boolean update(Object obj1, Object obj2){
        int index;
        if (obj1 instanceof User && obj2 instanceof User) {
//            if (users.contains((User) obj1)){
//                index = users.indexOf((User) obj2);
//                users.set(index, (User) obj2);
//                return true;
//            }
        }
        else if(obj1 instanceof Accommodation && obj2 instanceof Accommodation){
            if (accommodations.contains((Accommodation) obj1)) {
                index = accommodations.indexOf((Accommodation) obj2);
                accommodations.set(index, (Accommodation) obj2);
                return true;
            }
        }
        else if (obj1 instanceof Rating && obj2 instanceof Rating){
                if (ratings.contains((Rating) obj1)){
                    index = ratings.indexOf((Rating) obj2);
                    ratings.set(index, (Rating) obj2);
                    return true;
                }
        }
        return false;
    }


    /**
     * Deletes object from suitable data table.
     * @param obj the object to delete
     * @return boolean if deleted or not
     */
    public static boolean delete(Object obj){
        if (obj instanceof User){
            if (users.contains((User) obj)){
                users.remove((User) obj);
                return true;
            }
        }
        else if(obj instanceof Accommodation){
            if (accommodations.contains((Accommodation) obj)){
                accommodations.remove((Accommodation) obj);
                return true;
            }
        }
        else if (obj instanceof Rating){
            if (ratings.contains((Rating) obj)){
                ratings.remove((Rating) obj);
                return true;
            }
        }
        return false;
    }

    /**
     * Depending on input checks whether the variable is considered empty or not.
     * If t is an object null is considered empty.
     * If t is a number it is considered empty.
     * @param t the variable
     * @return boolean if empty or not
     * @param <T>
     */
    public static <T> boolean isEmpty(T t) {
        if (t == null) {
            return true;
        }
        if (t instanceof Number) {
            return ((Number) t).doubleValue() == 0;
        }
        if (t instanceof String) {
            return ((String) t).isEmpty();
        }
        return false;
    }

    /**
     * Saves the data inside database.dat binary file.
     */
    public static void save(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database.dat"))){
            out.writeObject(users);
            out.writeObject(accommodations);
            out.writeObject(ratings);
            System.out.println("Επιτυχής αποθήκευση.");
        } catch (IOException e) {
            System.out.println("Σφάλμα κατά την αποθήκευση των δεδομένων.");
        }
    }

    /**
     * Loads the data from database.dat binary file if they have not been already loaded.
     */
    public static void load() {
        if (!loaded) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("database.dat"))) {
                HashSet<User> tempUsers = (HashSet<User>) in.readObject();
                users.addAll(tempUsers);
                ArrayList<Accommodation> tempAccommodations = (ArrayList<Accommodation>) in.readObject();
                accommodations.addAll(tempAccommodations);
                ArrayList<Rating> tempRatings = (ArrayList<Rating>) in.readObject();
                ratings.addAll(tempRatings);
                System.out.println("Επιτυχής φόρτωση.");
                loaded = true;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Σφάλμα κατά τη φόρτωση των δεδομένων.");
            }
        }
    }
}