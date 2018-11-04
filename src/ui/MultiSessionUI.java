package ui;

/**
 * Interface for a UI that accepts multiple clients by CID
 */
public interface MultiSessionUI {
    
    /**
     * Prints a string out of the current UI for a specific CID
     *
     * @param sessionID The session to receive output
     * @param output The string to output to the UI
     */
    void printString(int sessionID, String output);
    
}
