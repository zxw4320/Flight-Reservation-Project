package ui;


import request.RequestHandler;

import java.util.Scanner;

public class Tui implements AFRSInterface {

    request.RequestHandler afrs;

    public Tui() {
        afrs = new RequestHandler();
    }

    @Override
    public void printString(String printText) {
        System.out.println(printText);
    }

    private void sendString(String sendText) {
        afrs.makeRequest(this, sendText);
    }

    public static void main(String[] args) {
        Tui activeTui = new Tui();
        Scanner input = new Scanner(System.in);
        String inputLine = "";
        while(true){
            inputLine = input.nextLine();
            activeTui.sendString(inputLine);
            System.out.println(inputLine);
        }

    }

}
