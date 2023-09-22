package api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Helper class to save facilities of an accommodation.
 */
public class Facilities implements Serializable {
    private ArrayList<String> view;
    private ArrayList<String> bathroom;
    private ArrayList<String> laundry;
    private ArrayList<String> entertainment;
    private ArrayList<String> airConditioning;
    private ArrayList<String> internet;
    private ArrayList<String> kitchen;
    private ArrayList<String> outdoors;
    private ArrayList<String> parking;


    private boolean isEmpty(ArrayList<String> array){
        return (array==null);
    }

    public void addView(String view) {
        if (isEmpty(this.view)){
            this.view = new ArrayList<>();
            this.view.add(view);
        }
        else{
            this.view.add(view);
        }
    }

    public void addBathroom(String bathroom) {
        if (isEmpty(this.bathroom)){
            this.bathroom = new ArrayList<>();
            this.bathroom.add(bathroom);
        }
        else{
            this.bathroom.add(bathroom);
        }
    }

    public void addLaundry(String laundry) {
        if (isEmpty(this.laundry)){
            this.laundry = new ArrayList<>();
            this.laundry.add(laundry);
        }
        else{
            this.laundry.add(laundry);
        }
    }

    public void addEntertainment(String entertainment) {
        if (isEmpty(this.entertainment)){
            this.entertainment = new ArrayList<>();
            this.entertainment.add(entertainment);
        }
        else{
            this.entertainment.add(entertainment);
        }
    }

    public void addAirConditioning(String airConditioning) {
        if (isEmpty(this.airConditioning)){
            this.airConditioning = new ArrayList<>();
            this.airConditioning.add(airConditioning);
        }
        else{
            this.airConditioning.add(airConditioning);
        }
    }

    public void addInternet(String internet) {
        if (isEmpty(this.internet)){
            this.internet = new ArrayList<>();
            this.internet.add(internet);
        }
        else{
            this.internet.add(internet);
        }
    }

    public void addKitchen(String kitchen) {
        if (isEmpty(this.kitchen)){
            this.kitchen = new ArrayList<>();
            this.kitchen.add(kitchen);
        }
        else{
            this.kitchen.add(kitchen);
        }
    }

    public void addOutdoors(String outdoors) {
        if (isEmpty(this.outdoors)){
            this.outdoors = new ArrayList<>();
            this.outdoors.add(outdoors);
        }
        else{
            this.outdoors.add(outdoors);
        }
    }

    public void addParking(String parking) {
        if (isEmpty(this.parking)){
            this.parking = new ArrayList<>();
            this.parking.add(parking);
        }
        else{
            this.parking.add(parking);
        }
    }

    public ArrayList<String> getView() {
        return view;
    }

    public ArrayList<String> getBathroom() {
        return bathroom;
    }

    public ArrayList<String> getLaundry() {
        return laundry;
    }

    public ArrayList<String> getEntertainment() {
        return entertainment;
    }

    public ArrayList<String> getAirConditioning() {
        return airConditioning;
    }

    public ArrayList<String> getInternet() {
        return internet;
    }

    public ArrayList<String> getKitchen() {
        return kitchen;
    }

    public ArrayList<String> getOutdoors() {
        return outdoors;
    }

    public ArrayList<String> getParking() {
        return parking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facilities that = (Facilities) o;
        return view.equals(that.view) && bathroom.equals(that.bathroom) && laundry.equals(that.laundry) && entertainment.equals(that.entertainment) && airConditioning.equals(that.airConditioning) && internet.equals(that.internet) && kitchen.equals(that.kitchen) && outdoors.equals(that.outdoors) && parking.equals(that.parking);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(view, bathroom, laundry, entertainment, airConditioning, internet, kitchen, outdoors, parking);
//    }
}
