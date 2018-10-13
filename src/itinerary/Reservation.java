package itinerary;

public class Reservation {
    // TODO: 10/11/18 documentation  
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

    public String getOrigin() {
        return itinerary.getOrigin();
    }

    public String getDestination() {
        return itinerary.getDestination();
    }


}
//