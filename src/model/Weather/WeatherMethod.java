package model.Weather;

/**
 * This is the interface that sets up all the weather objects an airport can get weather from. It is
 * the strategy in the Strategy design pattern.
 */
public interface WeatherMethod {
    
    /**
     * Method that all WeatherMethods must implement to get the weather
     *
     * @return a String of the weather
     */
    public String getWeather();
    
}
