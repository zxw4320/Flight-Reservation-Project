package Itinerary;

public class Airport {
    private String airportcode;
    private String name;
    private int delaytime;
    private String weather;

//
    public Airport(String airportcode, String name, int delaytime, String weather) {
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
        return weather;
    }
}
