package model;

/**
 *
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

    public String toString(){
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
