package model;

import java.util.ArrayList;
import java.util.HashMap;
import ui.AFRSInterface;

/**
 *
 */
public class AirportWeatherStorage {
    
    private HashMap<AFRSInterface, HashMap<String, WeatherMethod>> userMethods;
    private HashMap<String, WeatherMethod> localWeathers;
    private HashMap<String, WeatherMethod> faaWeathers;
    
    /**
     *  Constructor
     */
    public AirportWeatherStorage(HashMap<String, WeatherMethod> localWeathers,
        HashMap<String, WeatherMethod> faaWeathers) {
        this.userMethods = new HashMap<>();
        this.localWeathers = localWeathers;
        this.faaWeathers = faaWeathers;
    }
    
    /**
     * Sets the method for each user.
     *
     * @param ui
     * @param argumentArray
     */
    public void setMethod(ui.AFRSInterface ui, ArrayList<String> argumentArray) {
        if (argumentArray.get(1).equals("local")) {
            userMethods.put(ui, localWeathers);
        } else if (argumentArray.get(1).equals("faa")) {
            userMethods.put(ui, faaWeathers);
        } else {
            ui.printString("error, unknown information server");
        }
    }
    
    /**
     * returns the proper airport for the specified user
     *
     * @param ui the user
     * @param airportCode the airport needed
     * @return the airport based on the users server
     */
    public WeatherMethod getWeatherMethod(AFRSInterface ui, String airportCode) {
        return userMethods.get(ui).get(airportCode);
    }
}
