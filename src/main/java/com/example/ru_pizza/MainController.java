package com.example.ru_pizza;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    private Button btnCurrentOrder;

    @FXML
    private Button btnCustom;

    @FXML
    private Button btnSpecialty;

    @FXML
    private Button btnStoreOrders;

    @FXML
    private ImageView imgCurrentOrder;

    @FXML
    private ImageView imgCustom;

    @FXML
    private ImageView imgSpecialty;

    @FXML
    private ImageView imgStoreOrders;

    @FXML
    public void initialize() {

        setCursorHandOnHover(imgSpecialty);
        setCursorHandOnHover(imgCustom);
        setCursorHandOnHover(imgCurrentOrder);
        setCursorHandOnHover(imgStoreOrders);
    }

    private void setCursorHandOnHover(ImageView imageView) {
        imageView.setOnMouseEntered(event -> {
            Scene scene = imageView.getScene();
            if (scene != null) {
                scene.setCursor(Cursor.HAND); // Change cursor to hand
            }
        });
        imageView.setOnMouseExited(event -> {
            Scene scene = imageView.getScene();
            if (scene != null) {
                scene.setCursor(Cursor.DEFAULT); // Change cursor to default
            }
        });
    }


    @FXML
    private void handleOrderSpecialty() {
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/Specialty_Pizza.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Speciality Pizza");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBuildYourOwn() {
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/BuildOwn.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Speciality Pizza");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void handleOrderlist() {
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/ru_pizza/fxml/Orderlist.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Speciality Pizza");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
