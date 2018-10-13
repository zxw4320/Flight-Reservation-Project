package request;

import java.util.ArrayList;
import java.util.Arrays;

public class RequestHandler {

    private String cachedString;
    private Boolean partialRequest;

    public RequestHandler(){
        cachedString = "";
        partialRequest = false;
    }

    /**
     * Handles taking in strings of input from a client. It takes arguments
     * separated by commas and terminated with a semi-colon. It returns a 1
     * when more information is needed from the user. It stores the previous
     * part of the requestString and will accept more until it receives the
     * terminator.
     * @param requestString User input string
     * @return 0 on success, 1 on error of partial request
     */
    public int makeRequest(ui.AFRSInterface ui, String requestString) {

        ArrayList<String> requestArray = new ArrayList<>();

        // use our cachedString, we prepend it if it exist
        if (partialRequest) {
            requestString = cachedString + requestString;
        }

        // check for terminating character
        boolean hasTerminator =
                requestString.charAt(requestString.length() - 1) == ';';

        // throw error on lack of terminator
        if (!hasTerminator) {
            cachedString += requestString;
            partialRequest = true;
            return 1;
        }
        // leave partial request state in it
        else if (partialRequest) {
            partialRequest = false;
            cachedString = "";
        }

        // split the string for parsing and remove terminator
        String[] tempArray = requestString
                .substring(0, requestString.length() - 1)
                .split(",");
        requestArray = new ArrayList<>(Arrays.asList(tempArray));

        // check the command we are running and hand off to associated method
        String command = requestArray.get(0);
        if (command.equals("info")) {
            parseInfo(ui, requestArray);
        } else if (command.equals("reserve")) {
            parseReserve(ui, requestArray);
        } else if (command.equals("retrieve")) {
            parseRetrieve(ui, requestArray);
        } else if (command.equals("delete")) {
            parseDelete(ui, requestArray);
        } else if (command.equals("airport")) {
            parseAirport(ui, requestArray);
        }

        // return success
        return 0;

    }


    private void parseInfo(ui.AFRSInterface ui,
                           ArrayList<String> argumentArray){
        ui.printString("Info request attempted");
    }

    private void parseReserve(ui.AFRSInterface ui,
                              ArrayList<String> argumentArray){

    }

    private void parseRetrieve(ui.AFRSInterface ui,
                               ArrayList<String> argumentArray){

    }

    private void parseDelete(ui.AFRSInterface ui,
                             ArrayList<String> argumentArray){

    }

    private void parseAirport(ui.AFRSInterface ui,
                              ArrayList<String> argumentArray){
        // create request
        Request airportRequest = new AirportInfoRequest(ui,
                argumentArray.get(1));

    }

}
