package com.example.ru_pizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class that starts the RUPizza application.
 * @author Arun Felix
 * @author Digvijay Singh
 */
public class Main extends Application {

    /**
     * The main entry point for the application.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs during FXML loading.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/MainView.fxml"));
        if (fxmlLoader.getLocation() == null) {
            System.err.println("Error: FXML file not found or invalid.");
            return;
        }
        Scene scene = new Scene(fxmlLoader.load(), 640, 600);
        stage.setTitle("RUPizza!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch();
    }
}
