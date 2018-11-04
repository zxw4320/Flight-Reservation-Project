package model.Weather;

import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;

/**
 * Class filled when gson gets data from FAA server
 */
public class FAAInfo {
    
    private String Name;
    private String City;
    private String State;
    private String ICAO;
    private String IATA;
    private boolean SupportedAirport;
    private boolean Delay;
    private float DelayCount;
    ArrayList<Object> Status = new ArrayList<Object>();
    Weather Weather;
    
    @Override
    public String toString() {
        if (Delay) {
            String delay;
            if((((LinkedTreeMap) Status.get(0)).containsKey("AvgDelay"))){
                delay = (String) (((LinkedTreeMap) Status.get(0)).get("AvgDelay"));
            } else { // never had any airports with this type of delay, but left for safety
                delay = "Calculate Average Delay here"; //TODO calc avg delay if need be
            }
            return Name + ", " + Weather.toString() + delay;
        } else {
            return Name + ", " + Weather.toString() + "0 delay";
        }
    }
    
    // Getter Methods
    
    public String getName() {
        return Name;
    }
    
    public String getCity() {
        return City;
    }
    
    public String getState() {
        return State;
    }
    
    public String getICAO() {
        return ICAO;
    }
    
    public String getIATA() {
        return IATA;
    }
    
    public boolean getSupportedAirport() {
        return SupportedAirport;
    }
    
    public boolean getDelay() {
        return Delay;
    }
    
    public float getDelayCount() {
        return DelayCount;
    }
    
    public Weather getWeather() {
        return Weather;
    }
    
    // Setter Methods
    
    public void setName(String Name) {
        this.Name = Name;
    }
    
    public void setCity(String City) {
        this.City = City;
    }
    
    public void setState(String State) {
        this.State = State;
    }
    
    public void setICAO(String ICAO) {
        this.ICAO = ICAO;
    }
    
    public void setIATA(String IATA) {
        this.IATA = IATA;
    }
    
    public void setSupportedAirport(boolean SupportedAirport) {
        this.SupportedAirport = SupportedAirport;
    }
    
    public void setDelay(boolean Delay) {
        this.Delay = Delay;
    }
    
    public void setDelayCount(float DelayCount) {
        this.DelayCount = DelayCount;
    }
    
    public void setWeather(Weather Weather) {
        this.Weather = Weather;
    }
}
