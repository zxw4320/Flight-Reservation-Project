package model;

import model.Weather.WeatherMethod;

/**
 * Holds info and calculates weather for a specific airport.
 */
public class Airport {
    
    private String airportcode;
    private String name;
    private int delaytime;
    private WeatherMethod weatherMethod;
    
    /**
     * Constructor
     */
    public Airport(String airportcode, String name, int delaytime, WeatherMethod weatherMethod) {
        this.airportcode = airportcode;
        this.name = name;
        this.delaytime = delaytime;
        this.weatherMethod = weatherMethod;
    }
    
    public String toString() {
        return name + " (" + airportcode + ")";
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
    
    public void setWeatherMethod(WeatherMethod weatherMethod) {
        this.weatherMethod = weatherMethod;
    }
    
    /**
     * Gets the weather as a pretty string. Iterates the weather on call to next from DB.
     *
     * @return the current weather from the weatherMethod
     */
    public String getWeather() {
        return weatherMethod.getWeather();
    }
}
