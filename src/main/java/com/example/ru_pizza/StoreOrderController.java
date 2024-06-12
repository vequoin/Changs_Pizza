package com.example.ru_pizza;

import com.example.ru_pizza.model.Order;
import com.example.ru_pizza.model.OrderBreaker;
import com.example.ru_pizza.model.Pizza;
import com.example.ru_pizza.model.StoreOrder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for StoreOrder window
 * @author Digvijay Singh
 * @author Arun Felix
 */
public class StoreOrderController implements Initializable {
    @FXML
    public ComboBox<Integer> orderComboBox;
    public TextField orderTotal;
    @FXML
    private ListView<Order> storeOrderList;
    @FXML
    private Button dispatchOrderButton;
    @FXML
    private Button cancelOrderButton;
    /**
     * initializes the controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }
    /**
     * helper for initializing the controller.
     */
    public void initialize() {
        loadComboBoxItems();
        setupEventHandlers();
    }

    /**
     * loads orders to orders list
     */
    private void loadComboBoxItems() {
        List<Order> orders = OrderBreaker.getStoreOrder().getOrders();
        for (Order order : orders) {
            orderComboBox.getItems().add(order.getOrderId());
        }
    }

    /**
     * loads the order details given an orderID.
     * @param orderId, a unique ID assigned to the order.
     */
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

    /**
     * sets up the event handlers for elements
     */
    private void setupEventHandlers() {

        orderComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                loadStoreOrderDetails(newValue);
        }
        });

        // Handle View Order Button action
        dispatchOrderButton.setOnAction(event -> exportOrders());

        // Handle Cancel Order Button action
        cancelOrderButton.setOnAction(event -> cancelSelectedOrder());
        // Add a listener to the ComboBox to update the order total when a new order is selected
        orderComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateOrderTotal(newValue);
            }
        });
    }

    /**
     * updates order total label.
     * @param orderId, ID for order.
     */
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

    /**
     * Allows user to export orderlist to text file, allowing user to select location.
     */
    private void exportOrders() {
        Order selectedOrder = storeOrderList.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            showAlert("No Order Selected", "Please select an order to view.");
            return;
        }

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Order File");

        // Set extension filters if needed
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        Stage stage = (Stage) storeOrderList.getScene().getWindow(); // Assuming your control is inside a stage
        java.io.File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            // User chose a file
            String filePath = file.getAbsolutePath();

            // Export the order to the selected file
            OrderBreaker.getStoreOrder().exportToFile(filePath);

            showAlert("Exported", "Order has been exported successfully.");
            storeOrderList.getItems().clear();
            OrderBreaker.createNewStoreOrder();
        }
    }

    /**
     * removes order from list if cancelled.
     */
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

    /**
     * displays alert
     * @param title, title of alert.
     * @param content, content of alert.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
