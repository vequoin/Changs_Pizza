package com.example.ru_pizza;

import javafx.beans.InvalidationListener;
import com.example.ru_pizza.model.Size;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.ru_pizza.model.Topping;
import com.example.ru_pizza.model.Pizza;
import com.example.ru_pizza.model.PizzaMaker;
import com.example.ru_pizza.model.*;

import java.io.InputStream;
import java.util.Objects;

public class BuildYourOwnController {

    @FXML
    public CheckBox TomatoSauceCheckBox;

    @FXML
    public CheckBox AlfredoSauceCheckBox;
    public Label availableToppingsLabel;
    public Label selectedToppingsLabel;
    @FXML
    private ComboBox<String> pizzaComboBox;
    @FXML
    private ImageView pizzaImageView;
    private ListView<Topping> toppingsListView;
    @FXML
    private CheckBox extraCheeseCheckBox;
    @FXML
    private CheckBox extraSauceCheckBox;

    @FXML
    private Label totalPriceLabel;
    @FXML
    private Button refreshButton;
    @FXML
    private Button confirmButton;
    @FXML
    private ListView<Topping> availableToppingsListView;
    @FXML
    private Button addToppingButton;
    @FXML
    private Button removeToppingButton;
    @FXML
    private ListView<Topping> selectedToppingsListView;

    public void initialize(){
        Image placeHolder = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/PizzaSelectionPlaceHolder.jpg")));
        pizzaImageView.setImage(placeHolder);
        pizzaComboBox.getItems().addAll("Small", "Medium", "Large");
        availableToppingsListView.getItems().setAll(Topping.values());
        availableToppingsListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Topping item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getDescription());
            }
        });
        selectedToppingsListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Topping item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getDescription());
            }
        });

        addToppingButton.setOnAction(event ->handleAddTopping());
        removeToppingButton.setOnAction(event -> handleRemoveTopping());
        TomatoSauceCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            AlfredoSauceCheckBox.setDisable(newVal);
        });

        // Listener for Alfredo Sauce CheckBox
        AlfredoSauceCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            TomatoSauceCheckBox.setDisable(newVal);
        });

        // Set up listeners to update the UI based on user interactions
        setupListeners();
    }

    private void setupListeners() {
        pizzaComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updatePizzaImage(newSelection);
            updatePrice();
        });

        selectedToppingsListView.getItems().addListener((InvalidationListener) observable -> updatePrice());
        extraCheeseCheckBox.selectedProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());
        extraSauceCheckBox.selectedProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());

    }

    private void updatePrice() {
        if (pizzaComboBox.getValue() == null || pizzaComboBox.getValue().isEmpty()) {
            // No size selected or the selected value is empty, display $0.00
            totalPriceLabel.setText("Total Price: $0.00");
            return;
        }
        // Logic to calculate and update the price
        String pizzaSize = pizzaComboBox.getValue();
        String pizzaName = "BuildYourOwn";
        Pizza pizza = PizzaMaker.createPizza(pizzaName);
        double basePrice = pizza.price();
        double sizePrice = getSizePrice(pizzaSize);
        double extraPrice = getExtraPrice();
        double toppingPrice = getToppingsPrice();
        double totalPrice = basePrice + sizePrice + extraPrice + toppingPrice;

        // Update the total price label
        totalPriceLabel.setText(String.format("Total Price: $%.2f", totalPrice));

    }

    private double getToppingsPrice(){
       int numberTopping = selectedToppingsListView.getItems().size();
       if(numberTopping <= 3){
           return 0.0;
       }
       else{
           return (double)(numberTopping - 3) * 1.49;
       }
    }

    private double getSizePrice(String selectedSize) {
        if (selectedSize != null) {
            return switch (selectedSize) {
                case "Small" -> 0.00; // No additional cost for small
                case "Medium" -> 2.00; // Additional cost for medium
                case "Large" -> 4.00; // Additional cost for large
                default -> 0.00; // Default case if needed
            };
        }
        return 0.00;
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


    private void updateToppingsList(String newSelection) {
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
            case "Small" -> "/pictures/SmallPizza.jpg";
            case "Medium" -> "/pictures/MediumPizza.jpg";
            case "Large" -> "/pictures/LargePizza.jpg";
            default -> throw new IllegalArgumentException("Unknown pizza type: " + pizzaName);
        };
    }

    public void handleAddTopping() {
        Topping selectedTopping = availableToppingsListView.getSelectionModel().getSelectedItem();
        if(selectedTopping != null && selectedToppingsListView.getItems().size() < 7){
            availableToppingsListView.getItems().remove(selectedTopping);
            selectedToppingsListView.getItems().add(selectedTopping);
        }
        else{
            showAlert("Maximum Toppings Reached", "You can only select up to 7 toppings.");
        }
        
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleRemoveTopping() {
        Topping selectedTopping = selectedToppingsListView.getSelectionModel().getSelectedItem();
        if(selectedTopping != null){
            selectedToppingsListView.getItems().remove(selectedTopping);
            availableToppingsListView.getItems().add(selectedTopping);
        }
        
    }

    @FXML
    private void handleConfirmAction() {
        if(pizzaComboBox.getValue() == null){
            showAlert("Select Size","Please Select one size");
            return;
        }
        if(!(AlfredoSauceCheckBox.isSelected() || TomatoSauceCheckBox.isSelected())){
            showAlert("Select Sauce", "Please Select a Sauce");
            return;
        }
        if(selectedToppingsListView.getItems().size() < 3){
            showAlert("Select Toppings","Please Select least 3 toppings");
        }
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        String size = pizzaComboBox.getValue();
        pizza.setSize(getSizeForPizza(size));
        for (Topping topping : selectedToppingsListView.getItems()) {
            pizza.addTopping(topping);
        }
        pizza.setSauce(getSauceForPizza());
        pizza.setExtraCheese(extraCheeseCheckBox.isSelected());
        pizza.setExtraCheese(extraSauceCheckBox.isSelected());
    }

    private Sauce getSauceForPizza(){
        if(AlfredoSauceCheckBox.isSelected()){
            return Sauce.ALFREDO;
        }
        else{
            return Sauce.TOMATO;
        }
    }

    private Size getSizeForPizza(String size){
        return switch (size) {
            case "Small" -> Size.SMALL;
            case "Medium" -> Size.MEDIUM;
            case "Large" -> Size.LARGE;
            default -> null; // Change this line
        };
    }

    @FXML
    private void handleRefreshAction() {
        // Handle refresh action: reset all selections and views
    }
}
