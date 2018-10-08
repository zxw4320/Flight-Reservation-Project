package Itinerary;

public class Reservation {

    private String passenger;
    private Itinerary itinerary;


    public Reservation(String passenger, Itinerary itinerary) {
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    public String getPassenger() {
        return passenger;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }
}
//