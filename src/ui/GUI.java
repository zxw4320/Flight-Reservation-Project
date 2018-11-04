package ui;
import database.CSVdb;
import database.Flightdb;
import database.ReservationCSVParser;
import database.Reservationdb;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ReservationCollection;
import model.RouteMap;
import request.RequestHandler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * The GUI for the AFRS that can handle multiple users. Separates each user in tabs.
 */
public class GUI extends Application implements MultiSessionUI{
    // This is a collection of all previous dialogue strings
    private HashMap<Integer,TextArea> textAreaHashMap = new HashMap();
    private request.RequestHandler afrs;
    private SessionHandler sessionHandler;


    public static void main(String[] args){
        Application.launch(args);
    }

    /**
     * Constructor
     */
    public GUI() {
        // acquire DB files
        Path a = Paths.get("csv/airports.csv");
        Path f = Paths.get("csv/flights.csv");
        Path w = Paths.get("csv/weather.csv");
        Path d = Paths.get("csv/delay.csv");
        Path r = Paths.get("csv/reservations.csv");
        // make DB readers
        Flightdb flightdb = new CSVdb(a, w, f, d);
        Reservationdb reservationdb = new ReservationCSVParser(r);
        // use DB readers
        RouteMap routeMap = flightdb.generateRouteMap();
        ReservationCollection reservationCollection = reservationdb
                .generateReservationCollection(routeMap);
        // create request handler
        afrs = new RequestHandler(routeMap, reservationCollection);
        sessionHandler = new SessionHandler(this, afrs);
    }

    /**
     * This method runs the GUI element.
     * @param primaryStage the gui element
     */
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("AFRS System");
        Group root = new Group();

        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Button addButton = new Button("New Connection");
        Scene scene = new Scene(root, 600, 400, Color.WHITE);


        //This is the outermost border pane
        borderPane.setTop(addButton);
        borderPane.setCenter(tabPane);

        //This button creates a new tab
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int sessionID = sessionHandler.addSession();
                
                //Tab layout
                Tab tab = new Tab("User " + sessionID);
                BorderPane tabBorderPane = new BorderPane();
                HBox inputHbox = new HBox();
                Button submitButton = new Button("Submit");
                TextField input = new TextField();
                tabPane.getTabs().add(tab);
                tab.setContent(tabBorderPane);

                //This is the input box
                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String inputArg = input.getText();
                        sendString(sessionID+","+inputArg);
                        input.setText("");
                    }
                });
                inputHbox.getChildren().addAll(input,submitButton);
                inputHbox.setSpacing(10);
                tabBorderPane.setBottom(inputHbox);

                //This is the output box
                TextArea output = new TextArea();
                textAreaHashMap.put(sessionID,output);
                tabBorderPane.setTop(textAreaHashMap.get(sessionID));

                tabBorderPane.setPadding(new Insets(10,10,10,10));

                //Disconnect on tab close
                tab.setOnClosed(new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        sendString(sessionID+",disconnect;");
                    }
                });
    
                sendString(sessionID + ",server,local;");
            }

        });

        //End Render
        root.getChildren().addAll(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * This is how the GUI sends input to the system.
     * @param sendText The input argument to be sent.
     */
    private void sendString(String sendText) {
        sessionHandler.makeRequest(sendText);
    }

    /**
     * This is how the GUI recieves output from the system.
     * @param sessionID The session to receive output
     * @param output The string to output to the UI
     */
    @Override
    public void printString(int sessionID, String output){
        TextArea curr = textAreaHashMap.get(sessionID);
        curr.appendText(output+"\n");
        textAreaHashMap.put(sessionID,curr);
    }
}
