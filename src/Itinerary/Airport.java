package Itinerary;

/**
 * Holds info and calculates weather for a specific airport.
 *
 * Created by Tyler Baldwin on 10/8/17.
 */
public class Airport {
    private String airportcode;
    private String name;
    private int delaytime;
    private String[] weather;
    private int weatherIndex = 0;

    public Airport(String airportcode, String name, int delaytime, String[] weather) {
        this.airportcode = airportcode;
        this.delaytime = delaytime;
        this.weather = weather;
        this.name = name;
    }

    /**
     * returns Airport code
     * @return airport code
     */
    public String getAirportcode() {
        return airportcode;
    }

    /**
     *
     * @return airport name
     */
    public String getName() {
        return name;
    }


    /**
     *
     * @return delay time
     */
    public int getDelaytime() {
        return delaytime;
    }

    /**
     * returns the current weather
     *
     * @return current weather
     */
    public String getWeather() {
        String currentWeather = weather[weatherIndex] + "," + weather[weatherIndex+1];
        weatherIndex = (weatherIndex+2)%weather.length;
        return currentWeather;
    }
}
