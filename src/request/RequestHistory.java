package request;

import java.util.HashMap;
import java.util.Stack;

public class RequestHistory {

    private HashMap<Integer, Stack<Request>> undoStacks;
    private HashMap<Integer, Stack<Request>> redoStacks;


    public RequestHistory() {
        undoStacks = new HashMap<>();
        redoStacks = new HashMap<>();
    }


    /**
     * Adds a request to the request history for a CID
     * @param cid client ID that made the request
     * @param request The request the client made
     */
    public void addRequest(int cid, Request request) {
        getStack(cid, undoStacks).push(request);
    }

    /**
     * Undoes a command made by the client with CID
     * @param cid CID for client that wants to undo
     */
    public void undo(int cid) {
        Request request;

        // keep popping until we hit an undoable command
        do {
            request = getStack(cid, undoStacks).pop();
        } while (!request.unexecute());

        // add the undone command to the redo stack
        getStack(cid, redoStacks).push(request);
    }

    /**
     * Redoes a request made by the client
     * @param cid CID for client that wants to redo
     */
    public void redo(int cid) {
        // pop off redo stack
        Request request = getStack(cid, redoStacks).pop();
        // execute
        request.execute();
        // push request to undo stack
        getStack(cid, undoStacks).push(request);
    }

    /**
     * helper function to et a stack from our hashmap faster
     * creates a new stack if needed.
     * @param cid client id
     * @param hashMap hashmap to search
     * @return the stack of requests for that CID
     */
    private Stack<Request> getStack(int cid, HashMap<Integer, Stack<Request>> hashMap){
        // create new stack if needed
        if (!hashMap.containsKey(cid))
            hashMap.put(cid, new Stack<>());
        // return
        return hashMap.get(cid);
    }
}
