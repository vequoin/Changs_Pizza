package com.example.ru_pizza;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnCurrentOrder;

    @FXML
    private Button btnCustom;

    @FXML
    private Button btnSpecialty;

    @FXML
    private Button btnStoreOrders;

    @FXML
    private ImageView imgCurrentOrder;

    @FXML
    private ImageView imgCustom;

    @FXML
    private ImageView imgSpecialty;

    @FXML
    private ImageView imgStoreOrders;

    @FXML
    public void openwindow() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/Specialty_Pizza.fxml"));

            if (fxmlLoader.getLocation() == null) {
                System.err.println("Error: FXML file not found or invalid.");
                return;
            }

            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            stage.setTitle("Hello!");

            // Set the desired location (x, y)
            double x = 100; // Set your desired x-coordinate
            double y = 100; // Set your desired y-coordinate
            stage.setX(x);
            stage.setY(y);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return;
        }
    }

}
