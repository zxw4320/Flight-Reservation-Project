package model;


/**
 * Interface for all airports
 */
public interface Airport {
    
    /**
     * Makes sure every airport can return a string of their info
     *
     * @return all pertinent info about the airport
     */
    String getInfo();
    
    String getAirportcode();
    
    int getDelayTime();
}
