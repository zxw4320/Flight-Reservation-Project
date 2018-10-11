package Itinerary;

public class Airport {
    private String airportcode;
    private String name;
    private int delaytime;
    private String[] weather;
    private int weatherIndex = 0;

    // TODO: 10/11/18 documentation 
    public Airport(String airportcode, String name, int delaytime, String[] weather) {
        this.airportcode = airportcode;
        this.delaytime = delaytime;
        this.weather = weather;
        this.name = name;
    }

    public String getAirportcode() {
        return airportcode;
    }

    public String getName() {
        return name;
    }

    public int getDelaytime() {
        return delaytime;
    }

    public String getWeather() {
        String currentWeather = weather[weatherIndex] + "," + weather[weatherIndex+1];
        weatherIndex = (weatherIndex+2)%weather.length;
        return currentWeather;
    }
}
