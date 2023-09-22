package api;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  This class handles the search function for the accommodations.
 */
public class AdvancedAccommodationSearch {

    private String name, city;
    private int type;
    private Facilities facilities;

    private List<Accommodation> results;

    public AdvancedAccommodationSearch(String name, int type, String city, Facilities facilities){
        this.name = name;
        this.type = type;
        this.city = city;
        this.facilities = facilities;
    }

    /**
     * Filters the search according to the user's input.
     */
    private void filteringFields(){
        Stream<Accommodation> tempStream = Database.getAccommodations().stream();

        if (!name.equals("")){
            tempStream= tempStream
                    .filter(accommodation -> accommodation.getName().equals(name));
        }
        if (type != 0){
            tempStream= tempStream
                    .filter(accommodation -> accommodation.getType()==type);
        }
        if (!city.equals("")){
            tempStream= tempStream
                    .filter(accommodation -> accommodation.getCity().equals(city));
        }
//        if (facilities != null){
//            tempStream= tempStream
//                    .filter(accommodation -> accommodation.getFacilities().equals(facilities));
//        }

        results = tempStream
                .collect(Collectors.toList());
        
    }

    /**
     * Filters the facilities according to the user's input.
     */
    private void filteringFacilities(){
        Iterator<Accommodation> it = results.iterator();
        while (it.hasNext()){
            Accommodation result = it.next();
            boolean flag = true;


            if (facilities.getView() != null) {
                if (result.getFacilities().getView() == null){
                    it.remove();
                    flag = false;
                } else {
                    for (String s : facilities.getView()) {
                        if (!result.getFacilities().getView().contains(s)) {
                            it.remove();
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) continue;

            if (facilities.getBathroom() != null) {
                if (result.getFacilities().getBathroom() == null){
                    it.remove();
                    flag = false;
                } else {
                    for (String s : facilities.getBathroom()) {
                        if (!result.getFacilities().getBathroom().contains(s)) {
                            it.remove();
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) continue;

            if (facilities.getLaundry() != null) {
                if (result.getFacilities().getLaundry() == null){
                    it.remove();
                    flag = false;
                } else {
                    for (String s : facilities.getLaundry()) {
                        if (!result.getFacilities().getLaundry().contains(s)) {
                            it.remove();
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) continue;

            if (facilities.getEntertainment() != null) {
                if (result.getFacilities().getEntertainment() == null){
                    it.remove();
                    flag = false;
                } else {
                    for (String s : facilities.getEntertainment()) {
                        if (!result.getFacilities().getEntertainment().contains(s)) {
                            it.remove();
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) continue;

            if (facilities.getAirConditioning() != null) {
                if (result.getFacilities().getAirConditioning() == null){
                    it.remove();
                    flag = false;
                } else {
                    for (String s : facilities.getAirConditioning()) {
                        if (!result.getFacilities().getAirConditioning().contains(s)) {
                            it.remove();
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) continue;

            if (facilities.getInternet() != null) {
                if (result.getFacilities().getInternet() == null){
                    it.remove();
                    flag = false;
                } else {
                    for (String s : facilities.getInternet()) {
                        if (!result.getFacilities().getInternet().contains(s)) {
                            it.remove();
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) continue;

            if (facilities.getKitchen() != null) {
                if (result.getFacilities().getKitchen() == null){
                    it.remove();
                    flag = false;
                } else {
                    for (String s : facilities.getKitchen()) {
                        if (!result.getFacilities().getKitchen().contains(s)) {
                            it.remove();
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) continue;

            if (facilities.getOutdoors() != null) {
                if (result.getFacilities().getOutdoors() == null) {
                    it.remove();
                    flag = false;
                } else {
                    for (String s : facilities.getOutdoors()) {
                        if (!result.getFacilities().getOutdoors().contains(s)) {
                            it.remove();
                            flag = false;
                        }
                    }
                }
            }
            if (!flag) continue;

            if (facilities.getParking() != null) {
                if (result.getFacilities().getParking() == null){
                    it.remove();
                    flag = false;
                } else {
                    for (String s : facilities.getParking()) {
                        if (!result.getFacilities().getParking().contains(s)) {
                            it.remove();
                            flag = false;
                        }
                    }
                }
            }
//            if (!flag) continue;


        }
    }

    public List<Accommodation> getResults(){
        filteringFields();
        filteringFacilities();
        return results;
    }
}
