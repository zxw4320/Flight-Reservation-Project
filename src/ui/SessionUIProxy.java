package ui;

/**
 * An AFRSInterface implementing stand-in for a UI that calls back to its
 * parent SessionHandler so that it can map the callback to a distinct CID.
 */
public class SessionUIProxy implements AFRSInterface {
    
    // the session handler we report back to
    private SessionHandler sessionHandler;

    SessionUIProxy(SessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }
    
    @Override
    public void printString(String printText) {
        sessionHandler.printToUI(this, printText);
    }
}
