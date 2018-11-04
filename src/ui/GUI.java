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
import javafx.scene.layout.BorderPane;
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
        TabPane tabPane = new TabPane();
        Button addButton = new Button("New Connection");
        BorderPane borderPane = new BorderPane();
        BorderPane tabBorderPane = new BorderPane();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Break One Reached");
                Tab tab = new Tab("Tab" + (tabPane.getTabs().size() + 1));
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
                tab.setContent(tabBorderPane);
            }
        });
        borderPane.setTop(addButton);
        borderPane.setCenter(tabPane);
        root.getChildren().addAll(borderPane);
        Scene scene = new Scene(root, 600, 400, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void printString(int sessionID, String output){

    }
}
