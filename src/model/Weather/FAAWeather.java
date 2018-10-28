package model.Weather;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *  Weather object that gets weather from the FAA servers
 *  Represents a concreteStrategy in the Strategy design pattern.
 */
public class FAAWeather implements WeatherMethod {
    
    String urlPreface =
        "https://soa.smext.faa.gov/asws/api/airport/status/";
    String url;
    String airportCode;
    
    /**
     * Constructor
     */
    public FAAWeather(String airportCode) {
        this.airportCode = airportCode;
    }
    
    /**
     * Gets the weather as a pretty string from the FAA server
     *
     * @return the most recent weather from the FAA server
     */
    public String getWeather() {
        try {        // Create a URL and open a connection
            StringBuilder response = new StringBuilder();
            Gson gson = new Gson();
            url = urlPreface + airportCode;
            URL FAAURL = new URL(url);
            HttpURLConnection urlConnection =
                (HttpURLConnection) FAAURL.openConnection();
            
            // Set the paramters for the HTTP Request
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestProperty("Accept", "application/json");
            
            // Create an input stream and wrap in a BufferedReader to read the response.
            BufferedReader in =
                new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                FAAAirport airport = gson.fromJson(inputLine, FAAAirport.class);
                response.append(airport.toString());//.append("\n");
            }
            
            // MAKE SURE TO CLOSE YOUR CONNECTION!
            in.close();
            
            // response is the contents of the XML
            return response.toString();
        } catch (FileNotFoundException e) {
            System.out.print("URL not found: ");
            System.out.println(e.getMessage());
        } catch (MalformedURLException e) {
            System.out.print("Malformed URL: ");
            System.out.println(e.getMessage());
        } catch (ProtocolException e) {
            System.out.print("Unsupported protocol: ");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
