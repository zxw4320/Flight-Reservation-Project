package ui;

public class SessionUIProxy implements AFRSInterface {

    // the session handler we report back to
    private SessionHandler sessionHandler;

    protected SessionUIProxy(SessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }

    @Override
    public void printString(String printText) {
        sessionHandler.printToUI(this, printText);
    }
}
