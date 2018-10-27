package model;

import java.util.ArrayList;
import java.util.HashMap;
import model.Weather.WeatherMethod;
import ui.AFRSInterface;

public class AirportStorage implements {
    
    private HashMap<AFRSInterface, HashMap<String, WeatherMethod>> userMethods;
    private HashMap<String, WeatherMethod> localWeathers;
    private HashMap<String, WeatherMethod> faaWeathers;
    
    public AirportStorage(HashMap<String, WeatherMethod> localWeathers,
        HashMap<String, WeatherMethod> faaWeathers;) {
        this.userMethods = new HashMap<>();
        this.localWeathers = localWeathers;
        this.faaWeathers = faaWeathers;
    }
    
    public void setMethod(ui.AFRSInterface ui, ArrayList<String> argumentArray) {
        if (argumentArray.get(3).equals("local")) {
            userMethods.put(ui, localWeathers);
        } else if (argumentArray.get(3).equals("faa")) {
            userMethods.put(ui, faaWeathers);
        } else {
            ui.printString("error, unknown information server");
        }
    }
    
}
