package com.example.ru_pizza;

import com.example.ru_pizza.model.Order;
import com.example.ru_pizza.model.OrderBreaker;
import com.example.ru_pizza.model.Pizza;
import com.example.ru_pizza.model.StoreOrder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class StoreOrderController {
    @FXML
    public ComboBox<Integer> orderComboBox;
    public TextField orderTotal;
    @FXML
    private ListView<Order> storeOrderList;
    @FXML
    private Button dispatchOrderButton;
    @FXML
    private Button cancelOrderButton;


    // Initialize the controller
    public void initialize() {
        loadComboBoxItems();
        setupEventHandlers();
    }

    private void loadComboBoxItems() {
        List<Order> orders = OrderBreaker.getStoreOrder().getOrders();
        for (Order order : orders) {
            orderComboBox.getItems().add(order.getOrderId());
        }
    }

    private void loadStoreOrderDetails(Integer orderId) {
        storeOrderList.getItems().clear();
        StoreOrder storeOrder = OrderBreaker.getStoreOrder();
        Optional<Order> orderOpt = storeOrder.getOrders().stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst();

        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            storeOrderList.getItems().add(order);

        }
    }
    private void setupEventHandlers() {

        orderComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                loadStoreOrderDetails(newValue);
        }
        });

        // Handle View Order Button action
        dispatchOrderButton.setOnAction(event -> ExportOrders());

        // Handle Cancel Order Button action
        cancelOrderButton.setOnAction(event -> cancelSelectedOrder());
        // Add a listener to the ComboBox to update the order total when a new order is selected
        orderComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateOrderTotal(newValue);
            }
        });
    }

    private void updateOrderTotal(Integer orderId) {
        StoreOrder storeOrder = OrderBreaker.getStoreOrder();
        for (Order order : storeOrder.getOrders()) {
            if (order.getOrderId() == orderId) {
                double total = order.getOrderTotal(); // getOrderTotal() should calculate the total including tax
                orderTotal.setText(String.format("%.2f", total));
                return;
            }
        }
        orderTotal.setText("");
    }

    private void ExportOrders() {
        Order selectedOrder = storeOrderList.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("No Order Selected", "Please select an order to view.");
            return;
        }
        else{
            OrderBreaker.getStoreOrder().exportToFile("filename.txt");
            showAlert("Exported", "Order has been exported successfully.");
            storeOrderList.getItems().clear();
            OrderBreaker.createNewStoreOrder();
        }
    }

    private void cancelSelectedOrder() {

        Integer selectedOrderID = orderComboBox.getValue();
        if (selectedOrderID == null) {
            showAlert("No Order Selected", "Please select an order to cancel.");
            return;
        }
        else{
            List<Order> orders = OrderBreaker.getStoreOrder().getOrders();
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order order = iterator.next();
                if (order.getOrderId() == selectedOrderID) {
                    iterator.remove();
                    break;
                }
            }
            orderComboBox.getItems().clear();
            loadComboBoxItems();
            storeOrderList.getItems().clear();
            showAlert("Cancelled", "Order has been cancelled successfully.");

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
