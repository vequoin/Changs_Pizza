package com.example.ru_pizza;

import com.example.ru_pizza.model.Order;
import com.example.ru_pizza.model.OrderBreaker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller class for the main application window.
 * @author Arun Felix
 * @author Digvijay Singh
 */

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

    /**
     * Initializes the controller. Sets up event handlers and initializes UI components.
     */
    @FXML
    public void initialize() {
        setCursorHandOnHover(imgSpecialty);
        setCursorHandOnHover(imgCustom);
        setCursorHandOnHover(imgCurrentOrder);
        setCursorHandOnHover(imgStoreOrders);
        Order test = OrderBreaker.getOrder();
    }

    /**
     * Sets the cursor to hand on hover for the specified ImageView.
     *
     * @param imageView The ImageView to which the cursor behavior is applied.
     */
    private void setCursorHandOnHover(ImageView imageView) {
        imageView.setOnMouseEntered(event -> {
            Scene scene = imageView.getScene();
            if (scene != null) {
                scene.setCursor(Cursor.HAND); // Change cursor to hand
            }
        });
        imageView.setOnMouseExited(event -> {
            Scene scene = imageView.getScene();
            if (scene != null) {
                scene.setCursor(Cursor.DEFAULT); // Change cursor to default
            }
        });
    }

    /**
     * Handles the event when the "Order Specialty" button is clicked.
     * Opens a new stage with the Specialty Pizza view.
     */
    @FXML
    public void handleOrderSpecialty() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/Specialty_Pizza.fxml"));

            if (fxmlLoader.getLocation() == null) {
                System.err.println("Error: FXML file not found or invalid.");
                return;
            }

            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            stage.setTitle("Speciality Pizza!");

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

    /**
     * Handles the event when the "Build Your Own" button is clicked.
     * Opens a new stage with the Build Your Own Pizza view.
     */
    @FXML
    private void handleBuildYourOwn() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/BuildOwn.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("BuildYourOwn");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the event when the "Order List" button is clicked.
     * Opens a new stage with the Order List view.
     */
    @FXML
    private void handleOrderlist() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/OrderList.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Current Orders");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the event when the "Store Order List" button is clicked.
     * Opens a new stage with the Store Order List view.
     */
    @FXML
    private void handleStoreOrderlist() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/StoreOrder.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Current Store Orders");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
