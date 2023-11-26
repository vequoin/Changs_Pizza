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
        currentOrder = OrderBreaker.getOrder();
        updateOrderList();
        updateOrderDetails();
    }

    private void updateOrderList() {
        orderList.getItems().setAll(currentOrder.getPizzas());
        orderList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Pizza item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString()); // Implement toString in Pizza for proper representation
            }
        });
    }

    private void updateOrderDetails() {
        orderNumberField.setText(String.valueOf(currentOrder.getOrderId()));
        subtotalField.setText(String.format("%.2f", currentOrder.getTotalAmount()));
        totalTaxField.setText(String.format("%.2f", currentOrder.calculateTax()));
        double getTotalAmt = Double.parseDouble(subtotalField.getText());
        double getTotalTax = Double.parseDouble(totalTaxField.getText());
        totalCostField.setText(String.format("%.2f", getTotalAmt+getTotalTax));
    }

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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
