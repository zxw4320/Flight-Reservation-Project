package request;

/**
 * Interface for all requests to implement
 */
public interface Request {

    /**
     * Executes the current request
     */
    void execute();

    /**
     * Undoes the action taken by this requests execute method
     * Returns True if the command was undone, False if it was not.
     * THIS DOES NOT MEAN FAILURE, BUT THAT THE COMMAND COULD NOT BE UNDONE.
     */
    boolean unexecute();

}
