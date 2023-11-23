package com.example.ru_pizza;

import com.example.ru_pizza.model.Pizza;
import com.example.ru_pizza.model.PizzaMaker;
import com.example.ru_pizza.model.Sauce;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class SpecialityPizzaController {

    public Label sauceLabel;
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

        Image placeHolder = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/PizzaSelectionPlaceHolder.jpg")));
        pizzaImageView.setImage(placeHolder);
        sizeToggleGroup.selectToggle(sizeSmall);
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
            displaySauceForPizza(newSelection);
        });

        // Listener for size selection changes
        sizeToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> updatePrice());
        pizzaComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());
        sizeToggleGroup.selectedToggleProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());
        extraCheeseCheckBox.selectedProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());
        extraSauceCheckBox.selectedProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());

        // ... Add more listeners for other interactive components
    }


    private void updatePizzaImage(String pizzaName) {
        try {
            String imagePath = getImagePathForPizza(pizzaName);
            InputStream imageStream = getClass().getResourceAsStream(imagePath);
            if(imageStream != null) {
                Image pizzaImage = new Image(imageStream);
                pizzaImageView.setImage(pizzaImage);
            } else {
                System.err.println("Image not found: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace(); // This will print the stack trace of the exception to the console.
        }
    }


    private String getImagePathForPizza(String pizzaName) {
        return switch (pizzaName) {
            case "Deluxe" -> "/pictures/DeluxePizza.jpg";
            case "Supreme" -> "/pictures/SupremePizza.jpg";
            case "Meatzza" -> "/pictures/MeatPizza.jpg";
            case "Pepperoni" -> "/pictures/PepperoniPizza.jpg";
            case "Seafood" -> "/pictures/SeafoodPizza.jpg";
            default -> throw new IllegalArgumentException("Unknown pizza type: " + pizzaName);
        };
    }

    private void displaySauceForPizza(String pizzaName) {
        Pizza pizza = PizzaMaker.createPizza(pizzaName);
        Sauce sauce = pizza.getSauce();
        sauceLabel.setText("Sauce: " + sauce.toString());
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
        String pizzaName = pizzaComboBox.getValue();
        Pizza pizza = PizzaMaker.createPizza(pizzaName);
        double basePrice = pizza.price();
        RadioButton selectedSize = (RadioButton) sizeToggleGroup.getSelectedToggle();
        double sizePrice = getSizePrice(selectedSize);
        // Add costs for extras
        double extraPrice = getExtraPrice();

        double totalPrice = basePrice + sizePrice + extraPrice;

        // Update the total price label
        totalPriceLabel.setText(String.format("Total Price: $%.2f", totalPrice));

    }

    private double getSizePrice(RadioButton selectedSize) {
        if (selectedSize != null) {
            return switch (selectedSize.getText()) {
                case "Small" -> 0.00; // No additional cost for small
                case "Medium" -> 2.00; // Additional cost for medium
                case "Large" -> 4.00; // Additional cost for large
                default -> 0.00; // Default case if needed
            };
        }
        return 0.00; // Default to no additional cost if no size is selected
    }

    private double getExtraPrice() {
        double extraPrice = 0.0;
        if (extraCheeseCheckBox.isSelected()) {
            extraPrice += 1.00; // Cost for extra cheese
        }
        if (extraSauceCheckBox.isSelected()) {
            extraPrice += 1.00; // Cost for extra sauce
        }
        return extraPrice;
    }

    @FXML
    private void handleConfirmAction() {
        // Handle confirm action
    }

    @FXML
    private void handleRefreshAction() {
        // Handle refresh action: reset all selections and views
    }


}
