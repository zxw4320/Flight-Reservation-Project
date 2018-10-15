package model;

import ui.AFRSInterface;

import java.util.HashMap;
import java.util.List;

public class ItineraryHistory {

    HashMap<AFRSInterface, List<Itinerary>> itineraryHashMap;

    public ItineraryHistory(){
        itineraryHashMap = new HashMap<>();
    }

    public Itinerary getItinerary(AFRSInterface ui, int id){
        // get the list from the hashmap
        List<Itinerary> itineraryList = itineraryHashMap.get(ui);

        // return null if out of bounds
        if(id < 0 || id >= itineraryList.size()){
            return null;
        }
        else {
            return itineraryList.get(id);
        }
    }

    public void addItineraries(AFRSInterface ui, List<Itinerary> itineraryList){
        itineraryHashMap.put(ui,itineraryList);
    }

}
