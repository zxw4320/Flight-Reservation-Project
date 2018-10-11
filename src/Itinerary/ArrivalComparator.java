package Itinerary;

import java.util.Comparator;

public class ArrivalComparator implements Comparator<FlightInterface> {
    // TODO: 10/11/18 documentation 
    @Override
    public int compare(FlightInterface o1, FlightInterface o2) {
        String[] t1 = o1.getArrivalTime().split(":");
        String[] t2 = o2.getArrivalTime().split(":");
        int time1 = TimeHelper.calculateMinutes(t1);
        int time2 = TimeHelper.calculateMinutes(t2);
        return TimeHelper.compareHelper(time1,time2);
    }
}
