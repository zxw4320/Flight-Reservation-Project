package Itinerary;

public abstract class TimeHelper {

    // TODO: 10/11/18 Documentaton

    public static int compareHelper(int time1, int time2){
        return Integer.compare(time1, time2);
    }


    public static int calculateMinutes(String[] t1){
        String minutes = t1[1].replaceAll("[^0-9.]", "");
        int result;
        int hours = Integer.parseInt(t1[0]);
        char ampm = t1[1].charAt(2);
        if(ampm == 'p' && hours != 12){
            hours += 12;
        }
        else if(hours == 12 && ampm == 'a'){
            hours = 0;
        }
        result = hours * 60 + Integer.parseInt(minutes);
        return result;
    }
}
