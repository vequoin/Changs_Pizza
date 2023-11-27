package com.example.ru_pizza;

import com.example.ru_pizza.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
/**
 * Controller class for the "Specialty Pizza" Window.
 * @author Digvijay Singh
 * @author Arun Felix
 */
public class SpecialityPizzaController {

    public Label sauceLabel;
    public Text totalPrice;
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
    /**
     * Initializes the controller. Sets up UI components, listeners, and default values.
     */
    public void initialize() {
        // Initialize your UI components here
        sizeToggleGroup = new ToggleGroup();
        sizeSmall.setToggleGroup(sizeToggleGroup);
        sizeMedium.setToggleGroup(sizeToggleGroup);
        sizeLarge.setToggleGroup(sizeToggleGroup);

        Image placeHolder = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/PizzaSelectionPlaceHolder.jpg")));
        pizzaImageView.setImage(placeHolder);
        sizeToggleGroup.selectToggle(sizeSmall);

        // Example: Populate the ComboBox with pizza names
        pizzaComboBox.getItems().addAll("Meatzza", "Deluxe", "Supreme","Seafood","Pepperoni"); // Replace with actual names

        // Set up listeners to update the UI based on user interactions
        setupListeners();
    }
    /**
     * Sets up event listeners to update the UI.
     */
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
        extraCheeseCheckBox.selectedProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());
        extraSauceCheckBox.selectedProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());

        // ... Add more listeners for other interactive components
    }

    /**
     * Updates the displayed image of the pizza based on the selected pizza type.
     *
     * @param pizzaName The name of the selected pizza type.
     */
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
    /**
     * Retrieves the image path for a specific pizza type.
     *
     * @param pizzaName The name of the pizza type.
     * @return The image path for the specified pizza type.
     */
    private String getImagePathForPizza(String pizzaName) {
        return switch (pizzaName) {
            case "Deluxe" -> "/pictures/DeluxePizza.jpg";
            case "Supreme" -> "/pictures/SupremePizza.jpg";
            case "Meatzza" -> "/pictures/MeatPizza.jpg";
            case "Pepperoni" -> "/pictures/PepperoniPizza.jpg";
            case "Seafood" -> "/pictures/SeafoodPizza.jpg";
            case "Default" -> "/pictures/PizzaSelectionPlaceHolder.jpg";
            default -> throw new IllegalArgumentException("Unknown pizza type: " + pizzaName);
        };
    }
    /**
     * Displays the sauce type for the selected pizza on the sauce label.
     *
     * @param pizzaName The name of the selected pizza type.
     */
    private void displaySauceForPizza(String pizzaName) {
        Pizza pizza = PizzaMaker.createPizza(pizzaName);
        Sauce sauce = pizza.getSauce();
        sauceLabel.setText("Sauce: " + sauce.toString());
    }

    /**
     * Updates the toppings list based on the selected pizza type.
     *
     * @param pizzaName The name of the selected pizza type.
     */
    private void updateToppingsList(String pizzaName) {
        // Logic to update the toppings list based on selected pizza
        PizzaToppingsManager manager = new PizzaToppingsManager();
        List<String> toppings = manager.getToppingsForPizza(pizzaName);

        toppingsListView.getItems().clear(); // Clear existing items
        toppingsListView.getItems().addAll(toppings); // Add all toppings for the selected pizza
    }

    /**
     * Updates the total price based on user selections.
     */
    private void updatePrice() {
        // Logic to calculate and update the price
        if(pizzaComboBox.getValue() == null){
            return;
        }
        String pizzaName = pizzaComboBox.getValue();
        Pizza pizza = PizzaMaker.createPizza(pizzaName);
        RadioButton selectedSize = (RadioButton) sizeToggleGroup.getSelectedToggle();
        pizza.setSize(getSizeForPizza());
        pizza.setExtraCheese(extraCheeseCheckBox.isSelected());
        pizza.setExtraSauce(extraSauceCheckBox.isSelected());
        // Update the total price label
        //totalPriceLabel.setText(String.format("Total Price: $%.2f"));
        totalPrice.setText(String.format("%.2f",pizza.price()));
    }
    /**
     * Retrieves the additional cost based on the selected pizza size.
     *
     * @param selectedSize The selected pizza size.
     * @return The additional cost for the selected size.
     */
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
    /**
     * Retrieves the additional cost for extra cheese and sauce.
     *
     * @return The additional cost for extra cheese and sauce.
     */
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
    /**
     * Handles the action when the "Confirm" button is clicked.
     */
    @FXML
    private void handleConfirmAction() {
        // Handle confirm action
        if(pizzaComboBox.getValue() == null){
            showAlert("Select Size","Please Select one size");
            return;
        }
        Pizza pizza = PizzaMaker.createPizza(pizzaComboBox.getValue());
        pizza.setSize(getSizeForPizza());
        pizza.setExtraCheese(extraCheeseCheckBox.isSelected());
        pizza.setExtraSauce(extraSauceCheckBox.isSelected());
        OrderBreaker.getOrder().addPizza(pizza);
        showAlert("Congrats", "Your pizza has been added to cart!");
    }

    /**
     * Retrieves the pizza size based on the selected size radio button.
     *
     * @return The selected pizza size.
     */
    private Size getSizeForPizza() {
        if(sizeSmall.isSelected()){
            return Size.SMALL;
        }
        else if(sizeMedium.isSelected()){
            return Size.MEDIUM;
        }
        else{
            return Size.LARGE;
        }
    }
    /**
     * Displays an alert with the specified title and message.
     *
     * @param title   The title of the alert.
     * @param message The message content of the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * Handles the action when the "Refresh" button is clicked.
     */
    @FXML
    private void handleRefreshAction() {
        try {
            sizeSmall.setSelected(false);
            sizeMedium.setSelected(false);
            sizeLarge.setSelected(false);
            pizzaComboBox.setValue(null);
            updatePizzaImage("Default");
            extraSauceCheckBox.setSelected(false);
            extraCheeseCheckBox.setSelected(false);
            totalPriceLabel.setText("0.00");
            sauceLabel.setText("N/A");
        } catch (NullPointerException e) {
            //:-)
        }
    }

}
