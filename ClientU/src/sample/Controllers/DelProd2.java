package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Stock.Goods.Footwear;
import Stock.Goods.FootwearCreator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;

public class DelProd2 {
    FootwearCreator creator = new FootwearCreator();
    Footwear fw;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button delButton;

    @FXML
    private TextField name;

    @FXML
    private TextField amount;

    @FXML
    private TextField size;

    @FXML
    private TextField brand;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    amount.setText(oldValue);
                }
            }
        });
        size.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,2}")) {
                    size.setText(oldValue);
                }
            }
        });
        delButton.setOnAction(event -> {
            Main.client.setMessage("DelProd2");
            Main.client.SendMess();
            fw = (Footwear) creator.CreateProduct();
            fw.setName(name.getText());
            fw.setSize(Integer.parseInt(size.getText()));
            fw.setBrand(brand.getText());
            fw.setAmount(Integer.parseInt(amount.getText()));
            Main.client.setMessage(fw);
            Main.client.SendMess();
            try {
               String s = (String) Main.client.RecvMess();
                if (s.equals("ok"))
                JOptionPane.showMessageDialog(null, "изменения сохранены");
                else  JOptionPane.showMessageDialog(null, s);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        back.setOnAction(event -> {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        });
    }
}
