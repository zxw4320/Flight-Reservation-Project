package ui;

import java.util.HashMap;
import java.util.Map;
import request.RequestHandler;

class SessionHandler {
    private Map<Integer, SessionUIProxy> SUIPmap;
    private Map<Integer, String> partialRequests;
    private RequestHandler afrs;
    private MultiSessionUI outputUI;

    SessionHandler(MultiSessionUI outputUI, RequestHandler afrs){
        this.SUIPmap = new HashMap<>();
        this.partialRequests = new HashMap<>();
        this.afrs = afrs;
        this.outputUI = outputUI;
        
    }


    /**
     * Makes a request to the correct UI based on CID. Will return text errors
     * to the UI if needed.
     * @param string Request to make in "CID,REQUEST" format
     */
    void makeRequest(String string) {

        String newRequestString;
        String fullRequestString;
        int cid;
        
        // split the string for parsing
        String[] tempArray = string.split(",");

        // throw out empty queries
        if(string.isEmpty())
            return;
        }
        
        // handle special connection requests
        if(string.equals("connect;")) {
            cid = addSession();
            printToUI(cid, "connect," + cid);
            return;
        }
        
        // get CID
        try {
            String cidstring = string.substring( 0, string.indexOf(","));
            newRequestString = string.substring(string.indexOf(",")+1);
            cid = Integer.parseInt(cidstring);

        } catch (Exception e) {
            return;
        }
        
        // get partial request elements

        fullRequestString = partialRequests.getOrDefault(cid, "");
        fullRequestString = fullRequestString.concat(newRequestString);

        // handle partial requests
        if (!string.endsWith(";")) {
            partialRequests.put(cid, fullRequestString);
            printToUI(cid, "partial-request");
            return;
        }
        

        // handle disconnect
        if(fullRequestString.equals("disconnect;")){
            printToUI(cid, "disconnect;");
            SUIPmap.remove(cid);
            partialRequests.remove(cid);
            return;
        }

        // finally send to afrs and handle invalid connections
        if (!useSUIP(cid, fullRequestString))
            printToUI(cid, "error,invalid connection");
        partialRequests.remove(cid);
    }
    
    
    /**
     * Allows a suip to print out to the correct user session
     *
     * @param suip The SUIP that wants to print to the ui
     * @param response the response to print
     */
    void printToUI(SessionUIProxy suip, String response) {
        SUIPmap.forEach((cid, sessionUIProxy) -> {
            if (sessionUIProxy == suip) {
                printToUI(cid, response);
            }
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
    public Integer addSession() {
        // choose a /fresh/ CID
        int cid = getCID();
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


    /**
     * Gets a new CID for a user
     * @return unused CID
     */
    private int getCID(){
        int i = 0;
        while(SUIPmap.containsKey(i))
            i++;
        return i;
    }

}
