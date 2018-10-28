package model;

import java.util.ArrayList;
import java.util.HashMap;
import model.Weather.WeatherMethod;
import ui.AFRSInterface;

public class AirportWeatherStorage {
    
    private HashMap<AFRSInterface, HashMap<String, WeatherMethod>> userMethods;
    private HashMap<String, WeatherMethod> localWeathers;
    private HashMap<String, WeatherMethod> faaWeathers;
    
    /**
     *
     * @param localWeathers
     * @param faaWeathers
     */
    public AirportWeatherStorage(HashMap<String, WeatherMethod> localWeathers,
        HashMap<String, WeatherMethod> faaWeathers) {
        this.userMethods = new HashMap<>();
        this.localWeathers = localWeathers;
        this.faaWeathers = faaWeathers;
    }
    
    /**
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
     *
     * @param ui
     * @param airportCode
     * @return
     */
    public WeatherMethod getWeatherMethod(AFRSInterface ui, String airportCode){
        return userMethods.get(ui).get(airportCode);
    }
}
