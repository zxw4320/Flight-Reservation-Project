package model.Weather;

/**
 * Weather object generated from the local files. Represents a concreteStrategy in the Strategy
 * design pattern.
 */
public class LocalWeather implements WeatherMethod {
    
    private String[] weather;
    private int weatherIndex = 0;
    
    /**
     * Constructor
     */
    public LocalWeather(String[] weather) {
        this.weather = weather;
    }
    
    /**
     * Gets the weather as a pretty string. Iterates the weather on call to next from DB.
     *
     * @return weather stored in local files
     */
    public String getWeather() {
        // reset before out of bounds
        if (weatherIndex + 2 >= weather.length) {
            weatherIndex = 0;
        }
        String currentWeather = weather[weatherIndex + 1] + ", " + weather[weatherIndex + 2] + "F";
        weatherIndex = (weatherIndex + 2) % weather.length;
        return currentWeather;
    }
}
