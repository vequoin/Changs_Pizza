package com.example.ru_pizza;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class SpecialityPizzaController {

    @FXML
    private ComboBox<String> pizzaComboBox;
    @FXML
    private ImageView pizzaImageView;
    @FXML
    private ToggleGroup sizeToggleGroup;
    @FXML
    private RadioButton sizeSmall, sizeMedium, sizeLarge;
    @FXML
    private ListView<String> toppingsListView;
    @FXML
    private CheckBox extraCheeseCheckBox, extraSauceCheckBox;
    @FXML
    private CheckBox sauceAlfredoCheckBox, sauceTomatoCheckBox;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private Button confirmButton, refreshButton;

    public void initialize() {
        // Initialize your UI components here
        sizeToggleGroup = new ToggleGroup();
        sizeSmall.setToggleGroup(sizeToggleGroup);
        sizeMedium.setToggleGroup(sizeToggleGroup);
        sizeLarge.setToggleGroup(sizeToggleGroup);

        setupListeners();

        // Example: Populate the ComboBox with pizza names
        pizzaComboBox.getItems().addAll("Meatzza", "Deluxe", "Supreme","Seafood","Pepperoni"); // Replace with actual names

        // Set up listeners to update the UI based on user interactions
        setupListeners();
    }

    private void setupListeners() {
        // Listener for pizza selection changes
        pizzaComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updatePizzaImage(newSelection);
            updateToppingsList(newSelection);
        });

        // Listener for size selection changes
        sizeToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> updatePrice());

        // ... Add more listeners for other interactive components
    }


    private void updatePizzaImage(String pizzaName) {
        // Logic to update the pizza image based on selected pizza
        // Image pizzaImage = new Image(getImagePathForPizza(pizzaName));
        // pizzaImageView.setImage(pizzaImage);
    }

    private void updateToppingsList(String pizzaName) {
        // Logic to update the toppings list based on selected pizza
        PizzaToppingsManager manager = new PizzaToppingsManager();
        List<String> toppings = manager.getToppingsForPizza(pizzaName);

        toppingsListView.getItems().clear(); // Clear existing items
        toppingsListView.getItems().addAll(toppings); // Add all toppings for the selected pizza
    }


    private void updatePrice() {
        // Logic to calculate and update the price
    }

    @FXML
    private void handleConfirmAction() {
        // Handle confirm action
    }

    @FXML
    private void handleRefreshAction() {
        // Handle refresh action: reset all selections and views
    }

    // Additional methods as needed...
}
