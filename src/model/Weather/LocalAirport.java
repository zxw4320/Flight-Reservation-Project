package model.Weather;

import model.Airport;

/**
 * Weather object generated from the local files. Represents a concreteStrategy in the Strategy
 * design pattern.
 */
public class LocalAirport implements Airport {
   
    private String airportcode;
    private String name;
    private int delaytime;
    private String[] weather;
    private int weatherIndex = 0;
    
    
    /**
     * Constructor
     */
    public LocalAirport(String airportcode, String name, int delaytime, String[] weather) {
        this.weather = weather;
        this.airportcode = airportcode;
        this.name = name;
        this.delaytime = delaytime;
    }
    
    public String getInfo() {
        return name + " (" + airportcode + ")"+ " is currently "
            + getWeather() + " with " +
            Integer.toString(delaytime) + " minute delays.";
    }
    
    /*** getters ***/
    
    public String getAirportcode() {
        return airportcode;
    }
    
    public String getName() {
        return name;
    }
    
    public int getDelaytime() {
        return delaytime;
    }

    
    /**
     * Gets the weather as a pretty string. Iterates the weather on call to next from DB.
     *
     * @return weather stored in local files
     */
    private String getWeather() {
        // reset before out of bounds
        if (weatherIndex + 2 >= weather.length) {
            weatherIndex = 0;
        }
        String currentWeather = weather[weatherIndex + 1] + ", " + weather[weatherIndex + 2] + "F";
        weatherIndex = (weatherIndex + 2) % weather.length;
        return currentWeather;
    }
}
