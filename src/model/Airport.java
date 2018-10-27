package model;

import model.Weather.WeatherMethod;

/**
 * Holds info and calculates weather for a specific airport.
 */
public class Airport {

    private String airportcode;
    private String name;
    private int delaytime;
    //private String[] weather;
    //private int weatherIndex = 0;
    
    private WeatherMethod weatherMethod;

    /**
     * Constructor
     *
    public Airport(String airportcode, String name, int delaytime, String[] weather) {
        this.airportcode = airportcode;
        this.weather = weather;
        this.name = name;
        this.delaytime = delaytime;
    }*/
    
    /**
     *  Constructor
     */
    public Airport(String airportcode, String name, int delaytime, WeatherMethod weatherMethod){
        this.airportcode = airportcode;
        this.name = name;
        this.delaytime = delaytime;
        this.weatherMethod = weatherMethod;
    }
    
    public String toString(){
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

    public void setWeatherMethod(WeatherMethod weatherMethod){
        this.weatherMethod = weatherMethod;
    }
    
    /**
     * Gets the weather as a pretty string.
     * Iterates the weather on call to next from DB.
     * @return
     */
    public String getWeather() {
        /*// reset before out of bounds TODO remove if calling get weather works
        if(weatherIndex+2 >= weather.length)
            weatherIndex = 0;
        String currentWeather = weather[weatherIndex+1] + ", " + weather[weatherIndex+2] +"F";
        weatherIndex = (weatherIndex+2) % weather.length;
        return currentWeather;*/
        return weatherMethod.getWeather();
    }
}
