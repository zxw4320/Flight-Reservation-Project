package Itinerary;

import java.util.Comparator;

public class DepartingComparator implements Comparator<FlightInterface> {
    @Override
    public int compare(FlightInterface o1, FlightInterface o2) {
        String[] t1 = o1.getDepartureTime().split(":");
        String[] t2 = o2.getDepartureTime().split(":");
        int time1 = TimeHelper.calculateMinutes(t1);
        int time2 = TimeHelper.calculateMinutes(t2);
        return TimeHelper.compareHelper(time1,time2);
    }
}
