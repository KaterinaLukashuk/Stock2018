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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;

public class ChangeGoods {

    Clothes clothes;
    Footwear footwear;
    Accessor accessor;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Clothes> Table2;

    @FXML
    private TableColumn<Product, Integer> id2;

    @FXML
    private TableColumn<Product, String> name2;

    @FXML
    private TableColumn<Product, Double> prise2;

    @FXML
    private TableColumn<Product, String> brand2;

    @FXML
    private TableColumn<Product, Integer> size2;

    @FXML
    private TableColumn<Product, Integer> amount2;

    @FXML
    private Button back;

    @FXML
    private Button add;

    @FXML
    private Button del;

    @FXML
    private TextField amount;

    @FXML
    private TableView<Footwear> Table1;

    @FXML
    private TableColumn<Product, Integer> id1;

    @FXML
    private TableColumn<Product, String> name1;

    @FXML
    private TableColumn<Product, Double> prise1;

    @FXML
    private TableColumn<Product, String> brand1;

    @FXML
    private TableColumn<Product, Integer> size1;

    @FXML
    private TableColumn<Product, Integer> heel;
    @FXML
    private TextField heelfield;

    @FXML
    private TableColumn<Product, Integer> amount1;

    @FXML
    private TableView<Accessor> Table3;

    @FXML
    private TableColumn<Product, Integer> id3;

    @FXML
    private TableColumn<Product, String> name3;

    @FXML
    private TableColumn<Product, Double> prise3;

    @FXML
    private TableColumn<Product, String> brand3;

    @FXML
    private TableColumn<Product, Integer> amount3;

    @FXML
    private TextField price;

    @FXML
    private TextField name;

    @FXML
    private TextField brand;

    @FXML
    private TextField size;

    @FXML
    private CheckBox checkName;

    @FXML
    private CheckBox checkPrice;

    @FXML
    private CheckBox checkBrand;

    @FXML
    private CheckBox checkAmount;

    @FXML
    private CheckBox checkSize;

    @FXML
    private CheckBox checkHeel;

    @FXML
    void initialize() {
        Main.client.setMessage("ShowAllProd");
        Main.client.SendMess();
        ObservableList<Clothes> data = FXCollections.observableArrayList();
        ArrayList<Clothes> mas = null;
        // ArrayList<Product> mas = null;
        try {
            mas = (ArrayList<Clothes>) Main.client.RecvMess();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i< mas.size(); i++)
            data.add(mas.get(i));
        id2.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        name2.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        prise2.setCellValueFactory(new PropertyValueFactory<Product, Double>("prise"));
        brand2.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        size2.setCellValueFactory(new PropertyValueFactory<Product, Integer>("size"));
        amount2.setCellValueFactory(new PropertyValueFactory<Product,Integer>("amount"));
        try{
            Table2.setItems(data);}
        catch (java.lang.IllegalStateException e){}
        /////////////////////////////////////////////////////////////////////////////////////////
        Main.client.setMessage("ShowAllProd2");
        Main.client.SendMess();
        ObservableList<Footwear> data1 = FXCollections.observableArrayList();
        ArrayList<Footwear> mas1 = null;
        try {
            mas1 = (ArrayList<Footwear>) Main.client.RecvMess();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i< mas1.size(); i++)
            data1.add(mas1.get(i));
        id1.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        name1.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        prise1.setCellValueFactory(new PropertyValueFactory<Product, Double>("prise"));
        brand1.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        size1.setCellValueFactory(new PropertyValueFactory<Product, Integer>("size"));
        heel.setCellValueFactory(new PropertyValueFactory<Product, Integer>("heel"));
        amount1.setCellValueFactory(new PropertyValueFactory<Product,Integer>("amount"));
        try{
            Table1.setItems(data1);}
        catch (java.lang.IllegalStateException e){}
        //////////////////////////////////////////////////////////////////////////
        Main.client.setMessage("ShowAllProd3");
        Main.client.SendMess();
        ObservableList<Accessor> data3 = FXCollections.observableArrayList();
        ArrayList<Accessor> mas3 = null;
        // ArrayList<Product> mas = null;
        try {
            mas3 = (ArrayList<Accessor>) Main.client.RecvMess();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i< mas3.size(); i++)
            data3.add(mas3.get(i));

        id3.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        name3.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        prise3.setCellValueFactory(new PropertyValueFactory<Product, Double>("prise"));
        brand3.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        amount3.setCellValueFactory(new PropertyValueFactory<Product,Integer>("amount"));
        try{
            Table3.setItems(data3);}
        catch (java.lang.IllegalStateException e){}
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
        heelfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,2}")) {
                    heel.setText(oldValue);
                }
            }
        });
        add.setOnAction(event -> {
            String s = "";
            if ((!Table2.getSelectionModel().isEmpty()))
            {
                clothes = (Clothes) Table2.getSelectionModel().getSelectedItem();
                if (checkName.isSelected()&& !name.getText().isEmpty()) {clothes.setName(name.getText()); Table2.getSelectionModel().getSelectedItem().setName(name.getText());}
                if (checkBrand.isSelected()&& !brand.getText().isEmpty()) {clothes.setBrand(brand.getText()); Table2.getSelectionModel().getSelectedItem().setBrand(brand.getText());}
                if(checkPrice.isSelected()&& !price.getText().isEmpty()) {clothes.setPrise(Double.parseDouble(price.getText()));Table2.getSelectionModel().getSelectedItem().setPrise(Double.parseDouble(price.getText()));}
                if (checkSize.isSelected() && !size.getText().isEmpty()) {clothes.setSize(Integer.parseInt(size.getText())); Table2.getSelectionModel().getSelectedItem().setSize(Integer.parseInt(size.getText()));}
                if (checkAmount.isSelected()&& !amount.getText().isEmpty()&& Integer.parseInt(amount.getText())!=0) {clothes.setAmount(Integer.parseInt(amount.getText()));Table2.getSelectionModel().getSelectedItem().setAmount(Integer.parseInt(amount.getText()));}
                Main.client.setMessage("ChangeProd1");
                Main.client.SendMess();
                Main.client.setMessage(clothes);
                Main.client.SendMess();
                try {
                    s = (String) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (!Table1.getSelectionModel().isEmpty())
            {
                footwear = (Footwear) Table1.getSelectionModel().getSelectedItem();
                if (checkName.isSelected()&& !name.getText().isEmpty()) {footwear.setName(name.getText()); Table1.getSelectionModel().getSelectedItem().setName(name.getText());}
                if (checkBrand.isSelected()&& !brand.getText().isEmpty()) {footwear.setBrand(brand.getText()); Table1.getSelectionModel().getSelectedItem().setBrand(brand.getText());}
                if(checkPrice.isSelected()&& ! price.getText().isEmpty()) {footwear.setPrise(Double.parseDouble(price.getText()));Table1.getSelectionModel().getSelectedItem().setPrise(Double.parseDouble(price.getText()));}
                if (checkSize.isSelected()&& ! size.getText().isEmpty()) {footwear.setSize(Integer.parseInt(size.getText()));Table1.getSelectionModel().getSelectedItem().setSize(Integer.parseInt(size.getText()));}
                if (checkAmount.isSelected()&& !amount.getText().isEmpty()&& Integer.parseInt(amount.getText())!=0) {footwear.setAmount(Integer.parseInt(amount.getText()));Table1.getSelectionModel().getSelectedItem().setAmount(Integer.parseInt(amount.getText()));}
                if (checkHeel.isSelected()&& !heelfield.getText().isEmpty()) {footwear.setHeel(Integer.parseInt( heelfield.getText()));Table1.getSelectionModel().getSelectedItem().setHeel(Integer.parseInt( heelfield.getText()));}
                Main.client.setMessage("ChangeProd2");
                Main.client.SendMess();
                Main.client.setMessage(footwear);
                Main.client.SendMess();
                try {
                    s = (String) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if ((!Table3.getSelectionModel().isEmpty()) )
            {
                accessor = (Accessor) Table3.getSelectionModel().getSelectedItem();
                if (checkName.isSelected() && !name.getText().isEmpty()) {accessor.setName(name.getText());Table3.getSelectionModel().getSelectedItem().setName(name.getText());}
                if (checkBrand.isSelected()&& !brand.getText().isEmpty()) {accessor.setBrand(brand.getText());Table3.getSelectionModel().getSelectedItem().setBrand(brand.getText());}
                if(checkPrice.isSelected()&& !price.getText().isEmpty()) {accessor.setPrise(Double.parseDouble(price.getText()));Table3.getSelectionModel().getSelectedItem().setPrise(Double.parseDouble(price.getText()));}
                if (checkAmount.isSelected()&& !amount.getText().isEmpty()&& Integer.parseInt(amount.getText())!=0) {accessor.setAmount(Integer.parseInt(amount.getText()));Table3.getSelectionModel().getSelectedItem().setAmount(Integer.parseInt(amount.getText()));}
                Main.client.setMessage("ChangeProd3");
                Main.client.SendMess();
                Main.client.setMessage(accessor);
                Main.client.SendMess();
                try {
                    s = (String) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            checkName.setSelected(false);
            checkBrand.setSelected(false);
            checkPrice.setSelected(false);
            checkAmount.setSelected(false);
            checkSize.setSelected(false);
            checkHeel.setSelected(false);
            name.clear();
            brand.clear();
            size.clear();
            amount.clear();
            price.clear();
            heelfield.clear();
            Table1.getSelectionModel().clearSelection();
            Table2.getSelectionModel().clearSelection();
            Table3.getSelectionModel().clearSelection();
            if (s.equals("ok"))
            { JOptionPane.showMessageDialog(null, "Редактирование выполнено успешно");
                //todo: обновить таблицы
                Main.client.setMessage("ShowAllProd");
                Main.client.SendMess();
                ObservableList<Clothes> data4 = FXCollections.observableArrayList();
                ArrayList<Clothes> mas4 = null;
                // ArrayList<Product> mas = null;
                try {
                    mas4 = (ArrayList<Clothes>) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                for (int i=0; i< mas4.size(); i++)
                    data4.add(mas4.get(i));
                try{
                    Table2.setItems(data4);}
                catch (java.lang.IllegalStateException e){}
                /////////////////////////////////////////////////////////////////////////////////////////
                Main.client.setMessage("ShowAllProd2");
                Main.client.SendMess();
                ObservableList<Footwear> data5 = FXCollections.observableArrayList();
                ArrayList<Footwear> mas5 = null;
                try {
                    mas5 = (ArrayList<Footwear>) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                for (int i=0; i< mas5.size(); i++)
                    data5.add(mas5.get(i));
                try{
                    Table1.setItems(data5);}
                catch (java.lang.IllegalStateException e){}
                //////////////////////////////////////////////////////////////////////////
                Main.client.setMessage("ShowAllProd3");
                Main.client.SendMess();
                ObservableList<Accessor> data6 = FXCollections.observableArrayList();
                ArrayList<Accessor> mas6 = null;
                // ArrayList<Product> mas = null;
                try {
                    mas6 = (ArrayList<Accessor>) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                for (int i=0; i< mas6.size(); i++)
                    data6.add(mas6.get(i));
                try{
                    Table3.setItems(data6);}
                catch (java.lang.IllegalStateException e){}
            }
            else   JOptionPane.showMessageDialog(null, "Ошибка!");
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

        del.setOnAction(event -> {
            String s = "";
            if ((!Table2.getSelectionModel().isEmpty()))
            {
                clothes = (Clothes) Table2.getSelectionModel().getSelectedItem();
                Main.client.setMessage("DelProdID1");
                Main.client.SendMess();
                Main.client.setMessage(clothes);
                Main.client.SendMess();
                try {
                    s = (String) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (!Table1.getSelectionModel().isEmpty())
            {
                footwear = (Footwear) Table1.getSelectionModel().getSelectedItem();
                Main.client.setMessage("DelProdID2");
                Main.client.SendMess();
                Main.client.setMessage(footwear);
                Main.client.SendMess();
                try {
                    s = (String) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if ((!Table3.getSelectionModel().isEmpty()) )
            {
                accessor = (Accessor) Table3.getSelectionModel().getSelectedItem();
                Main.client.setMessage("DelProdID3");
                Main.client.SendMess();
                Main.client.setMessage(accessor);
                Main.client.SendMess();
                try {
                    s = (String) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (s.equals("ok"))
            { JOptionPane.showMessageDialog(null, "Удаление выполнено успешно");
            int row = 0;
            if ( !Table1.getSelectionModel().isEmpty())
            {   row = Table1.getSelectionModel().getSelectedIndex();
                Table1.getItems().remove(row);}

                if ( !Table2.getSelectionModel().isEmpty())
                {   row = Table2.getSelectionModel().getSelectedIndex();
                Table2.getItems().remove(row);}
                if ( !Table3.getSelectionModel().isEmpty())
                {  row = Table3.getSelectionModel().getSelectedIndex();
                Table3.getItems().remove(row);}
            }
            else   JOptionPane.showMessageDialog(null, "Ошибка!");
        });
    }
}
