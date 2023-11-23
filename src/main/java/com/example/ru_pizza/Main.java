package com.example.ru_pizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/MainView.fxml"));
        if (fxmlLoader.getLocation() == null) {
            System.err.println("Error: FXML file not found or invalid.");
            return;
        }
        Scene scene = new Scene(fxmlLoader.load(), 640, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}