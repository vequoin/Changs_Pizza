package com.example.ru_pizza;

import com.example.ru_pizza.model.Order;
import com.example.ru_pizza.model.Pizza;
import com.example.ru_pizza.model.OrderBreaker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controller class for the orderlist window.
 * @author Arun Felix
 * @author Digvijay Singh
 */
public class OrderlistController implements Initializable {

    public TextField totalCostField;
    @FXML
    private ListView<Pizza> orderList;
    @FXML
    private TextField orderNumberField;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button removePizzaButton;
    @FXML
    private TextField subtotalField;
    @FXML
    private TextField totalTaxField;

    private Order currentOrder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentOrder = OrderBreaker.getOrder();
        updateOrderList();
        updateOrderDetails();
    }

    /**
     * populates the list in ordering window.
     */
    private void updateOrderList() {
        if(currentOrder.isEmpty()){
            showAlert("No Order","You have no orders.");
            return;
        }
        this.orderList.getItems().setAll(currentOrder.getPizzas());
        this.orderList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Pizza item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString()); // Implement toString in Pizza for proper representation
            }
        });
    }

    /**
     * updates the text elements in field based on the cost of the order + tax.
     */
    private void updateOrderDetails() {
        orderNumberField.setText(String.valueOf(currentOrder.getOrderId()));
        subtotalField.setText(String.format("%.2f", currentOrder.getTotalAmount()));
        totalTaxField.setText(String.format("%.2f", currentOrder.calculateTax()));
        double getTotalAmt = Double.parseDouble(subtotalField.getText());
        double getTotalTax = Double.parseDouble(totalTaxField.getText());
        totalCostField.setText(String.format("%.2f", getTotalAmt+getTotalTax));
    }

    /**
     * handles logic when place order button is pressed.
     */
    @FXML
    private void handlePlaceOrderAction() {
        if(currentOrder.getPizzas().isEmpty()){
            showAlert("Cart empty!", "Your shopping cart is empty! Cannot place order!");
            return;
        }
        showAlert("Order Placed", "Your order has been placed successfully.");
        OrderBreaker.getStoreOrder().addOrder(currentOrder);
        orderList.getItems().clear();
        subtotalField.setText("");
        totalTaxField.setText("");
        totalCostField.setText("");
        currentOrder = OrderBreaker.createNewOrder();
    }

    /**
     * Handles logic when user removes pizza.
     */
    @FXML
    private void handleRemovePizzaAction() {
        Pizza selectedPizza = orderList.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            currentOrder.getPizzas().remove(selectedPizza);
            updateOrderList();
            updateOrderDetails();
        } else {
            showAlert("No Selection", "Please select a pizza to remove.");
        }
    }

    /**
     * handles alerts
     * @param title, the title displayed on window
     * @param content, the content on the alert.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
