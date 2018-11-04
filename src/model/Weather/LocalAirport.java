package model.Weather;

import model.Airport;

/**
 * Weather object generated from the local files. Represents a concreteStrategy in the Strategy
 * design pattern.
 */
public class LocalAirport implements Airport {
   
    private String airportcode;
    private String name;
    private int delayTime;
    private String[] weather;
    private int weatherIndex = 0;
    
    
    /**
     * Constructor
     */
    public LocalAirport(String airportcode, String name, int delayTime, String[] weather) {
        this.weather = weather;
        this.airportcode = airportcode;
        this.name = name;
        this.delayTime = delayTime;
    }
    
    public String getInfo() {
        return name + "," + getWeather() + "," + delayTime;
    }
    
    /*** getters ***/
    
    public String getAirportcode() {
        return airportcode;
    }
    
    public String getName() {
        return name;
    }
    
    public int getDelayTime() {
        return delayTime;
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
        String currentWeather = weather[weatherIndex + 1] + "," + weather[weatherIndex + 2];
        weatherIndex = (weatherIndex + 2) % weather.length;
        return currentWeather;
    }
}
