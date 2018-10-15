package request;

import ui.AFRSInterface;

/**
 *
 */
public class MakeReservationRequest implements Request {

    private int id;
    private String passenger;

    /**
     * Constructor
     */
    public MakeReservationRequest(int id, String passenger){
        this.id = id;
        this.passenger = passenger;
    }

    @Override
    public void execute() {

    }
}
