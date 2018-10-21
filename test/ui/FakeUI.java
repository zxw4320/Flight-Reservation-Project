package ui;

import request.RequestHandler;

import java.util.ArrayList;
import java.util.List;

public class FakeUI implements AFRSInterface{

    List<String> responceList;

    public FakeUI(){
        responceList = new ArrayList<>();
    }

    @Override
    /**
     * Adds a print response to our list
     */
    public void printString(String printText) {
        responceList.add(printText);
    }

    protected List<String> sendCommand(RequestHandler requestHandler, String request){
        requestHandler.makeRequest(this, request);

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> tempString = new ArrayList<>(responceList);
        responceList.clear();
        return tempString;

    }




}
