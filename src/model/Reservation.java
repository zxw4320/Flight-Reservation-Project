package model;

/**
 * representation of a reservation as an object. links a passenger to an itinerary.
 */
public class Reservation {
    
    private String passenger;
    private Itinerary itinerary;
    
    
    /**
     * Constructor
     */
    public Reservation(String passenger, Itinerary itinerary) {
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    @Override
    public String toString() {
        return itinerary.toString();
    }
    
    /*** getters ***/
    
    public String getPassenger() {
        return passenger;
    }
    
    public Itinerary getItinerary() {
        return itinerary;
    }
    
    public Airport getOrigin() {
        return itinerary.getOrigin();
    }
    
    public Airport getDestination() {
        return itinerary.getDestination();
    }

}
