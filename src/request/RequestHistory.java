package request;

import java.util.HashMap;
import java.util.Stack;
import ui.AFRSInterface;

public class RequestHistory {
    
    private HashMap<AFRSInterface, Stack<Request>> undoStacks;
    private HashMap<AFRSInterface, Stack<Request>> redoStacks;
    
    
    public RequestHistory() {
        undoStacks = new HashMap<>();
        redoStacks = new HashMap<>();
    }
    
    
    /**
     * Adds a request to the request history for a CID
     *
     * @param ui client that made the request
     * @param request The request the client made
     */
    public void addRequest(AFRSInterface ui, Request request) {
        getStack(ui, undoStacks).push(request);
    }
    
    /**
     * Undoes a command made by the client
     *
     * @param ui client that wants to undo
     */
    public void undo(AFRSInterface ui) {
        // catch empty stack
        Request request;
        // keep popping until we hit an undoable command
        do {
            if (getStack(ui, undoStacks).isEmpty()) {
                ui.printString("error,no request available");
                return;
            }
            request = getStack(ui, undoStacks).pop();
        } while (!request.unexecute());
        
        // add the undone command to the redo stack
        getStack(ui, redoStacks).push(request);
    }
    
    /**
     * Redoes a request made by the client
     *
     * @param ui client that wants to redo
     */
    public void redo(AFRSInterface ui) {
        // catch empty stack
        if (getStack(ui, redoStacks).isEmpty()) {
            ui.printString("error,no request available");
            return;
        }
        // pop off redo stack
        Request request = getStack(ui, redoStacks).pop();
        request.execute();
        // push request to undo stack
        getStack(ui, undoStacks).push(request);
    }
    

    /**
     * helper function to et a stack from our hashmap faster creates a new stack if needed.
     *
     * @param ui client
     * @param hashMap hashmap to search
     * @return the stack of requests for that CID
     */
    private Stack<Request> getStack(AFRSInterface ui,
        HashMap<AFRSInterface, Stack<Request>> hashMap) {
        // create new stack if needed
        if (!hashMap.containsKey(ui)) {
            hashMap.put(ui, new Stack<>());
        }
        // return
        return hashMap.get(ui);
    }
}
