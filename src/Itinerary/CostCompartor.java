package Itinerary;
import java.util.Comparator;

public class CostCompartor implements Comparator<FlightInterface> {

    /**
     * compares the price of 2 airfalers
     * @param o1 fight one
     * @param o2 flight two
     * @return the value 0 if o1 == o2; a value less than 0 if o1 < o2 and a value greater than 0 if o1 > o2
     */
    @Override
    public int compare(FlightInterface o1, FlightInterface o2) {
        return Integer.compare(o1.getAirfare(), o2.getAirfare());
    }
}
