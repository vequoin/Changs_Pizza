package com.example.ru_pizza;

import com.example.ru_pizza.model.Order;
import com.example.ru_pizza.model.OrderBreaker;
import com.example.ru_pizza.model.StoreOrder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

public class StoreOrderController {

    @FXML
    private ListView<Order> storeOrderList;
    @FXML
    private TextField orderIDField;
    @FXML
    private Button dispatchOrderButton;
    @FXML
    private Button cancelOrderButton;


    // Initialize the controller
    public void initialize() {
        // Populate the storeOrderList with orders
        loadStoreOrders();

        // Setup listeners or event handlers
        setupEventHandlers();
    }

    private void loadStoreOrders() {
        // Assume getStoreOrders() returns a List<Order> containing all store orders
        List<Order> orders = OrderBreaker.getStoreOrder().getOrders();
        storeOrderList.getItems().setAll(orders);
    }

    private void setupEventHandlers() {
        // Handle selecting an order from the ListView
        storeOrderList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                orderIDField.setText(String.valueOf(newValue.getOrderId()));
            }
        });

        // Handle View Order Button action
        dispatchOrderButton.setOnAction(event -> dispatchAllOrders());

        // Handle Cancel Order Button action
        cancelOrderButton.setOnAction(event -> cancelSelectedOrder());
    }

    private void dispatchAllOrders() {
        Order selectedOrder = storeOrderList.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("No Order Selected", "Please select an order to view.");
            return;
        }
    }

    private void cancelSelectedOrder() {
        Order selectedOrder = storeOrderList.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("No Order Selected", "Please select an order to cancel.");
            return;
        }
        else{
            OrderBreaker.getStoreOrder().getOrders().remove(selectedOrder);
        }

    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
