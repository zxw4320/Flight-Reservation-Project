package request;

public class MakeReservationRequest implements Request {

    //TODO Read up on requirements for this request
    private int id;
    private String passenger;

    public MakeReservationRequest(int id, String passenger){
        this.id = id;
        this.passenger = passenger;
    }

    @Override
    public void execute() {

    }
}
