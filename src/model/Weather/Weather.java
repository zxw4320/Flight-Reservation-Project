package model.Weather;

import java.util.ArrayList;

/**
 * Class filled by gson when getting weather from FAA server
 */
public class Weather {
    
    ArrayList<Weather> Weather = new ArrayList<Weather>();
    ArrayList<Object> Visibility = new ArrayList<Object>();
    ArrayList<Object> Meta = new ArrayList<Object>();
    ArrayList<Object> Temp = new ArrayList<Object>();
    ArrayList<Object> Wind = new ArrayList<Object>();
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Weather.size(); i++) {
            for (int j = 0; j < Weather.get(i).Temp.size(); j++) {
                result.append(Weather.get(i).Temp.get(j)).append(" ");
            }
        }
        return result.toString();
    }
    
    // Getter Methods
    
    // Setter Methods
    
}