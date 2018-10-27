package ui;

import request.RequestHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SessionHandler {

    private Map<Integer, SessionUIProxy> SUIPmap;
    private Map<Integer, ArrayList<String>> partialRequests;
    private RequestHandler afrs;
    private MultiSessionUI outputUI;
    private int nextCID;


    protected SessionHandler(MultiSessionUI outputUI, RequestHandler afrs){

        this.SUIPmap = new HashMap<>();
        this.partialRequests = new HashMap<>();
        this.afrs = afrs;
        this.outputUI = outputUI;

    }


    protected void makeRequest(String string) {

        ArrayList<String> newRequestArray = new ArrayList<>();
        ArrayList<String> fullRequestArray = new ArrayList<>();
        int cid;

        // split the string for parsing
        String[] tempArray = string.split(",");
        newRequestArray = new ArrayList<>(Arrays.asList(tempArray));

        // throw out empty queries
        if(newRequestArray.isEmpty())
            return;

        // handle special connection requests
        if(newRequestArray.get(0).equals("connect;")) {
            cid = addSession();
            printToUI(cid, "connect," + Integer.toString(cid));
            useSUIP(cid, "server,local;");
            return;
        }

        // get CID
        try {
            cid = Integer.parseInt(newRequestArray.get(0));
        } catch (Exception e) {
            return;
        }

        // get partial request elements
        fullRequestArray = partialRequests.getOrDefault(cid, new ArrayList<>());
        fullRequestArray.addAll(newRequestArray);

        // handle partial requests
        if (!string.endsWith(";")) {
            partialRequests.put(cid, fullRequestArray);
            printToUI(cid, "partial-request");
            return;
        }

        // finally, strip the cid and hit the main afrs, and handle case if
        // the SUIP doesn't exist for the CID...
        fullRequestArray.remove(0);
        if (!useSUIP(cid, String.join(",", fullRequestArray)))
            printToUI(cid, "error,invalid connection");

    }


    /**
     * Allows a suip to print out to the correct user session
     *
     * @param suip The SUIP that wants to print to the ui
     * @param response the response to print
     */
    protected void printToUI(SessionUIProxy suip, String response) {
        SUIPmap.forEach((cid, sessionUIProxy) -> {
            if (sessionUIProxy == suip)
                printToUI(cid, response);
        });
    }

    private void printToUI(int cid, String string) {
        outputUI.printString(cid, string);
    }


    /**
     * Creates a session in our SUIPmap with a new ID
     *
     * @return The generated ID
     */
    private Integer addSession() {
        // choose a /fresh/ CID
        int cid = nextCID;
        nextCID++;

        //Generate our SUIP , add to map and return
        SUIPmap.put(cid, new SessionUIProxy(this));
        return cid;
    }


    /**
     * Handles sending a request to the AFRS with the correct SUIP
     *
     * @param cid Client ID making request
     * @param command Request made by client session
     * @return Did we have success?
     */
    private boolean useSUIP(int cid, String command) {
        // handle using SUIP
        if (SUIPmap.containsKey(cid)) {
            SessionUIProxy suip = SUIPmap.get(cid);
            afrs.makeRequest(suip, command);
            return true;
        }
        // failure
        return false;
    }


}
