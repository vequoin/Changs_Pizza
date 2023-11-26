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
import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.Objects;

public class BuildYouOwn {

    @FXML
    public CheckBox TomatoSauceCheckBox;

    @FXML
    public CheckBox AlfredoSauceCheckBox;
    public Label availableToppingsLabel;
    public Label selectedToppingsLabel;
    public Text totalPriceBox;
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
    private Pizza currentPizza;

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

        currentPizza = PizzaMaker.createPizza("BuildYourOwn");

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

        selectedToppingsListView.getItems().addListener((InvalidationListener) observable -> updatePizzaToppingsAndPrice());
        extraCheeseCheckBox.selectedProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());
        extraSauceCheckBox.selectedProperty().addListener((obs, oldSelection, newSelection) -> updatePrice());

    }

    private void updatePizzaToppingsAndPrice() {
        currentPizza.getToppings().clear();
        for (Topping topping : selectedToppingsListView.getItems()) {
            currentPizza.addTopping(topping);
        }
        updatePrice();
    }

    private void updatePrice() {
        if (pizzaComboBox.getValue() == null || pizzaComboBox.getValue().isEmpty()) {
            // No size selected or the selected value is empty, display $0.00
            totalPriceBox.setText("0.00");
            return;
        }
        // Logic to calculate and update the price
        String size = pizzaComboBox.getValue();
        currentPizza.setSize(getSizeForPizza(size));
        currentPizza.setExtraCheese(extraCheeseCheckBox.isSelected());
        currentPizza.setExtraSauce(extraSauceCheckBox.isSelected());
        double totalPrice = currentPizza.price();

        // Update the total price label
        totalPriceBox.setText(String.format("%.2f", totalPrice));

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
        if(selectedTopping == null){
            showAlert("Please Select","Please Select a topping by clicking on it");
            return;
        }
        if(selectedToppingsListView.getItems().size() < 7){
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
            return;
        }
        currentPizza.setSauce(getSauceForPizza());
        OrderBreaker.getOrder().addPizza(currentPizza);
        showAlert("Congrats", "Your pizza has been added to cart!");
        currentPizza = PizzaMaker.createPizza("BuildYourOwn");
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
