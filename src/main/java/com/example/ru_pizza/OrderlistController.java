package com.example.ru_pizza;

import com.example.ru_pizza.model.Pizza;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderlistController implements Initializable {

    @FXML
    private ListView<Pizza> Order_List;

    @FXML
    private TextField Order_Number;

    @FXML
    private Button Place_Order;

    @FXML
    private Button Remove_Pizza;

    @FXML
    private TextField Subtotal;

    @FXML
    private TextField Total_Tax;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
