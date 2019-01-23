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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;


public class AddOrder {
    Clothes clothes;
    Footwear footwear;
    Accessor accessor;
    ArrayList<Product> orderlist = new ArrayList<Product>();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Clothes> Table2;

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
    private Button end;

    @FXML
    private TextField amount;

    @FXML
    private TableView<Footwear> Table1;

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
    private TableColumn<Product, Integer> amount1;

    @FXML
    private TableView<Accessor> Table3;

    @FXML
    private TableColumn<Product, String> name3;

    @FXML
    private TableColumn<Product, Double> prise3;

    @FXML
    private TableColumn<Product, String> brand3;

    @FXML
    private TableColumn<Product, Integer> amount3;

    @FXML
    private TableColumn<Product, Integer> id1;

    @FXML
    private TableColumn<Product, Integer> id2;

    @FXML
    private TableColumn<Product, Integer> id3;

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
        add.setOnAction(event -> {
            //todo: проверять количество
            //todo: исключить повторы
            if  (!amount.getText().isEmpty() && Integer.parseInt(amount.getText())!=0)
            {    if ((!Table2.getSelectionModel().isEmpty()))
            {

                clothes = (Clothes) Table2.getSelectionModel().getSelectedItem();
                if (clothes.getAmount() >= Integer.parseInt(amount.getText()))
                {
                    clothes.setAmount(Integer.parseInt(amount.getText()));
                orderlist.add(clothes);
                System.out.println(clothes.getId()+" "+clothes.getAmount());
                //Table2.getSelectionModel().clearSelection();
                int row = Table2.getSelectionModel().getSelectedIndex();
                Table2.getItems().remove(row);
                }
                else     JOptionPane.showMessageDialog(null, "Ошибка! Такого количества товара нет в наличии");
            }
            if (!Table1.getSelectionModel().isEmpty())
            {
                footwear = (Footwear) Table1.getSelectionModel().getSelectedItem();
                if (footwear.getAmount() >= Integer.parseInt(amount.getText())  )
                {
                footwear.setAmount(Integer.parseInt(amount.getText()));
                orderlist.add(footwear);
                System.out.println(footwear.getId()+" "+ footwear.getAmount());
               // Table1.getSelectionModel().clearSelection();
                int row = Table1.getSelectionModel().getSelectedIndex();
                Table1.getItems().remove(row);
                }else JOptionPane.showMessageDialog(null, "Ошибка! Такого количества товара нет в наличии");
            }
            if ((!Table3.getSelectionModel().isEmpty()) && (!amount.getText().isEmpty()))
            {
                accessor = (Accessor) Table3.getSelectionModel().getSelectedItem();
                if (accessor.getAmount() >= Integer.parseInt(amount.getText())) {
                    accessor.setAmount(Integer.parseInt(amount.getText()));
                    orderlist.add(accessor);
                    System.out.println(accessor.getId() + " " + accessor.getAmount());
                    int row = Table3.getSelectionModel().getSelectedIndex();
                    Table3.getItems().remove(row);
                }else JOptionPane.showMessageDialog(null, "Ошибка! Такого количества товара нет в наличии");
            }}else JOptionPane.showMessageDialog(null, "Ошибка! Количество товара введено неверно");
            Table1.getSelectionModel().clearSelection();
            Table2.getSelectionModel().clearSelection();
            Table3.getSelectionModel().clearSelection();
        });
        end.setOnAction(event -> {
            Main.client.setMessage("AddOrder");
            Main.client.SendMess();
            //todo: отправить серверу объект заказа
            Main.client.setMessage(AddCustomer.order);
            Main.client.SendMess();
            // todo: отправить массив товаров
            Main.client.setMessage(orderlist);
            Main.client.SendMess();
            int code;
            // todo: олучить и сообщить клиенту код заказв
            try {
               code = (int) Main.client.RecvMess();
                JOptionPane.showMessageDialog(null, "Код заказа: " + code);
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
