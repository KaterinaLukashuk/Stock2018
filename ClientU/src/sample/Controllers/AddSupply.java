package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Stock.Goods.Accessor;
import Stock.Goods.Clothes;
import Stock.Goods.Footwear;
import Stock.Goods.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;

public class AddSupply {
    Clothes clothes;
    Footwear footwear;
    Accessor accessor;
    ArrayList<Product> supplylist = new ArrayList<Product>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField amount;

    @FXML
    private TextField size;

    @FXML
    private TextField brand;

    @FXML
    private Button back;

    @FXML
    private TextField heel;

    @FXML
    private Button end;

    @FXML
    private ComboBox<String> type;

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
        price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}([\\.]\\d{0,2})?")) {
                    price.setText(oldValue);
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
        ObservableList<String> showlist = FXCollections.observableArrayList("Товары / одежда","Товары / обувь","Товары / аксессуары");
        type.setItems(showlist);
        type.setOnAction(event -> {
            if (type.getSelectionModel().getSelectedItem().equals("Товары / одежда"))
            {
                heel.setEditable(false);
                size.setEditable(true);
            }
            if (type.getSelectionModel().getSelectedItem().equals("Товары / аксессуары"))
            {
                heel.setEditable(false);
                size.setEditable(false);
            }
            if (type.getSelectionModel().getSelectedItem().equals("Товары / обувь"))
            {
                heel.setEditable(true);
                size.setEditable(true);
            }
        });
        addButton.setOnAction(event -> {
            try{
            if (type.getSelectionModel().getSelectedItem().equals("Товары / одежда"))
            {
                if (Integer.parseInt(amount.getText())!=0)
                {  clothes = new Clothes();
                clothes.setName(name.getText());
                clothes.setSize(Integer.parseInt(size.getText()));
                clothes.setPrise(Double.parseDouble(price.getText()));
                clothes.setBrand(brand.getText());
                clothes.setAmount(Integer.parseInt(amount.getText()));
                    Main.client.setMessage("AddProd");
                    Main.client.SendMess();
                    Main.client.setMessage(clothes);
                    Main.client.SendMess();
                    try {
                        Main.client.RecvMess();
                        supplylist.add(clothes);
                        JOptionPane.showMessageDialog(null, "проукт добавлен");
                        name.clear();
                        size.clear();
                        price.clear();
                        brand.clear();
                        amount.clear();
                        heel.clear();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else  JOptionPane.showMessageDialog(null, "Ошибка");
            }
            if (type.getSelectionModel().getSelectedItem().equals("Товары / аксессуары"))
            {
                if (Integer.parseInt(amount.getText())!=0)
                {  accessor = new Accessor();
                    accessor.setName(name.getText());
                    accessor.setPrise(Double.parseDouble(price.getText()));
                    accessor.setBrand(brand.getText());
                    accessor.setAmount(Integer.parseInt(amount.getText()));
                    Main.client.setMessage("AddProd3");
                    Main.client.SendMess();
                    Main.client.setMessage(accessor);
                    Main.client.SendMess();
                    try {
                        Main.client.RecvMess();
                        supplylist.add(accessor);
                        JOptionPane.showMessageDialog(null, "проукт добавлен");
                        name.clear();
                        size.clear();
                        price.clear();
                        brand.clear();
                        amount.clear();
                        heel.clear();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else  JOptionPane.showMessageDialog(null, "Ошибка");
            }
            if (type.getSelectionModel().getSelectedItem().equals("Товары / обувь"))
            {
                if (Integer.parseInt(amount.getText())!=0)
                {
                    footwear = new Footwear();
                    footwear.setName(name.getText());
                    footwear.setSize(Integer.parseInt(size.getText()));
                    footwear.setPrise(Double.parseDouble(price.getText()));
                    footwear.setBrand(brand.getText());
                    footwear.setAmount(Integer.parseInt(amount.getText()));
                    footwear.setHeel(Integer.parseInt(heel.getText()));
                    Main.client.setMessage("AddProd2");
                    Main.client.SendMess();
                    Main.client.setMessage(footwear);
                    Main.client.SendMess();
                    try {
                        Main.client.RecvMess();
                        supplylist.add(footwear);
                        JOptionPane.showMessageDialog(null, "проукт добавлен");
                        name.clear();
                        size.clear();
                        price.clear();
                        brand.clear();
                        amount.clear();
                        heel.clear();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else  JOptionPane.showMessageDialog(null, "Ошибка");
            }} catch (NullPointerException e){
                JOptionPane.showMessageDialog(null, "Данные не выбраны");}
        });
        end.setOnAction(event -> {
            Main.client.setMessage("AddSupplie");
            Main.client.SendMess();
            //todo: отправить серверу объект заказа
            Main.client.setMessage(AddSupplier.supplier);
            Main.client.SendMess();
            // todo: отправить массив товаров
            Main.client.setMessage(supplylist);
            Main.client.SendMess();
            int code;
            // todo: олучить и сообщить клиенту код заказв
            try {
                code = (int) Main.client.RecvMess();
                if (code != -1)
                JOptionPane.showMessageDialog(null, "Код поставки: " + code);
                else  JOptionPane.showMessageDialog(null, "Ошибка при добавлении!" );
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) end.getScene().getWindow();
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
