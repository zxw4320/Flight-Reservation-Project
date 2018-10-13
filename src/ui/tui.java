package ui;


import request.RequestHandler;

import java.awt.desktop.SystemEventListener;
import java.util.Scanner;

public class tui implements AFRSInterface {

    request.RequestHandler afrs;

    public tui() {
        afrs = new RequestHandler();
    }

    @Override
    public void printString(String printText) {
        System.out.println("printText");
    }

    private void sendString(String sendText) {
        afrs.makeRequest(sendText);
    }

    public static void main(String[] args) {
        tui activeTui = new tui();
        Scanner input = new Scanner(System.in);
        while(true){
            activeTui.sendString(input.nextLine());
        }

    }

}
