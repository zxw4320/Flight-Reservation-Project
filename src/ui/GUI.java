package ui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class GUI extends Application implements MultiSessionUI{
    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        HBox inputHbox = new HBox();
        Button submitButton = new Button("Submit");
        TextField input = new TextField();
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Button addButton = new Button("New Connection");
        BorderPane tabBorderPane = new BorderPane();
        Scene scene = new Scene(root, 600, 400, Color.WHITE);
        Label inputLabel = new Label("Input:");

        //This is the outermost border pane
        borderPane.setTop(addButton);
        borderPane.setCenter(tabPane);

        //This is the input box
        input.setText("Test");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(input.getText().equals("")){
                    System.out.println("Empty Input");
                    //TODO - Give error message to user
                }
                else
                {
                    System.out.println("Some Input");
                    System.out.println(input.getText());
                    //TODO - Give input to system
                }
                input.setText("");
            }
        });
        inputHbox.getChildren().addAll(input,submitButton);
        tabBorderPane.setBottom(inputHbox);

        //This button creates a new tab
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Tab tab = new Tab("Tab" + (tabPane.getTabs().size() + 1));
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
                tab.setContent(tabBorderPane);
            }
        });

        //End Render
        root.getChildren().addAll(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void printString(int sessionID, String output){

    }
}
