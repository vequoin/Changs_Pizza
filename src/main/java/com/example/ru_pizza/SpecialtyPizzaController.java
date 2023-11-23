package com.example.ru_pizza;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SpecialtyPizzaController {

    @FXML
    private ChoiceBox<?> PizzaDropdown;

    @FXML
    private ImageView Pizza_Image;

    @FXML
    private CheckBox SP_Extcheese;

    @FXML
    private CheckBox SP_Extsauce;

    @FXML
    private RadioButton SP_Large;

    @FXML
    private ListView<?> SP_Listview;

    @FXML
    private RadioButton SP_Medium;

    @FXML
    private Button SP_Place;

    @FXML
    private TextField SP_Price;

    @FXML
    private TextField SP_Sauce;

    @FXML
    private RadioButton SP_Small;

}
