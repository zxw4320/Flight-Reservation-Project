package ui;
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
import java.util.HashMap;

public class GUI extends Application implements MultiSessionUI{
    // This is a collection of all previous dialogue strings
    private HashMap<Integer,TextArea> textAreaHashMap = new HashMap();


    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("<<TITLE>>");
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
                //TODO - Get CID on new tab
                int sessionID = 1;
                Tab tab = new Tab("Tab" + (tabPane.getTabs().size() + 1));
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
                        if(inputArg.equals("")){
                            System.out.println("Empty Input");
                            //TODO - Give error message to user
                        }
                        else
                        {
                            System.out.println("Some Input");
                            System.out.println(inputArg);
                            //TODO - Give input to system
                            printString(sessionID,inputArg);
                        }
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
                tab.setOnCloseRequest(new EventHandler<Event>() {
                    //Requests to close tab - maybe use setOnClose
                    @Override
                    public void handle(Event event) {
                        //TODO - Client closes tab without disconnecting
                        System.out.println("DISCONNECT COMMAND");
                    }
                });
            }

        });



        //End Render
        root.getChildren().addAll(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void printString(int sessionID, String output){
        TextArea curr = textAreaHashMap.get(sessionID);
        curr.appendText(output+"\n");
        textAreaHashMap.put(sessionID,curr);
    }
}
