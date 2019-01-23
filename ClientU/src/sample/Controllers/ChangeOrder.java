package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Stock.Orders.Order;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;

public class ChangeOrder {
    Order order;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Order> goodsTable;

    @FXML
    private TableColumn<Order, Integer> id;

    @FXML
    private TableColumn<Order, String> cust;

    @FXML
    private TableColumn<Order, Integer> prod_id;

    @FXML
    private TableColumn<Order, Integer> amount;

    @FXML
    private TableColumn<Order, String> date;

    @FXML
    private TableColumn<Order, Integer> address;

    @FXML
    private TableColumn<Order, String> phone;

    @FXML
    private TableColumn<Order, String> email;

    @FXML
    private Button back;

    @FXML
    private Button del;

    @FXML
    private CheckBox chAmount;

    @FXML
    private CheckBox chDate;

    @FXML
    private CheckBox chAddress;

    @FXML
    private CheckBox chPhone;

    @FXML
    private CheckBox chEmail;

    @FXML
    private CheckBox chCust;

    @FXML
    private TextField custField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private Button red;

    @FXML
    private DatePicker dateField;

    @FXML
    void initialize() {
        Main.client.setMessage("ShowAllOrders");
        Main.client.SendMess();
        ObservableList<Order> data = FXCollections.observableArrayList();
        ArrayList<Order> mas = null;
        // ArrayList<Product> mas = null;
        try {
            mas = (ArrayList<Order>) Main.client.RecvMess();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i< mas.size(); i++)
            data.add(mas.get(i));
        id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
        prod_id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("product_id"));
        cust.setCellValueFactory(new PropertyValueFactory<Order,String>("costumer"));
        date.setCellValueFactory(new PropertyValueFactory<Order,String>("orderDate"));
        phone.setCellValueFactory(new PropertyValueFactory<Order,String>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<Order,String>("email"));
        amount.setCellValueFactory(new PropertyValueFactory<Order,Integer>("amount"));
        address.setCellValueFactory(new PropertyValueFactory<Order,Integer>("address"));
        try{
            goodsTable.setItems(data);}
        catch (java.lang.IllegalStateException e){}

        amountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    amountField.setText(oldValue);
                }
            }
        });
        phoneField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,15}")) {
                    phoneField.setText(oldValue);
                }
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
        red.setOnAction(event -> {
            order = new Order();
            if ((!goodsTable.getSelectionModel().isEmpty()))
            {   order = goodsTable.getSelectionModel().getSelectedItem();
            if (chAddress.isSelected() && !addressField.getText().isEmpty()) order.setAddress(addressField.getText());
            if (chAmount.isSelected()  && !amountField.getText().isEmpty() && Integer.parseInt(amountField.getText())!=0) order.setAmount(Integer.parseInt(amountField.getText()));
            if (chDate.isSelected() && !dateField.getValue().toString().isEmpty())order.setOrderDate(dateField.getValue().toString());
            if(chEmail.isSelected() && !emailField.getText().isEmpty()) order.setEmail(emailField.getText());
            if (chPhone.isSelected()  && !phoneField.getText().isEmpty()) order.setPhone(phoneField.getText());
            Main.client.setMessage("ChangeOrder");
            Main.client.SendMess();
            Main.client.setMessage(order);
            Main.client.SendMess();
            try {
                if (Main.client.RecvMess().equals("ok"))
                { JOptionPane.showMessageDialog(null, "Редактирование выполнено успешно");
                 //todo: обновить таблицу
                    Main.client.setMessage("ShowAllOrders");
                    Main.client.SendMess();
                    ObservableList<Order> data2 = FXCollections.observableArrayList();
                    ArrayList<Order> mas2 = null;
                    // ArrayList<Product> mas = null;
                    try {
                        mas2 = (ArrayList<Order>) Main.client.RecvMess();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    for (int i=0; i< mas2.size(); i++)
                        data2.add(mas2.get(i));
                    try{
                        goodsTable.setItems(data2);}
                    catch (java.lang.IllegalStateException e){}
                }
                else   JOptionPane.showMessageDialog(null, "Ошибка!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }} else JOptionPane.showMessageDialog(null, "Данные не выбраны");
            goodsTable.getSelectionModel().clearSelection();
            chPhone.setSelected(false);
            chEmail.setSelected(false);
            chDate.setSelected(false);
            chAmount.setSelected(false);
            chAddress.setSelected(false);
            amountField.clear();
            addressField.clear();
            phoneField.clear();
            emailField.clear();
        });
        del.setOnAction(event -> {
            order = new Order();
            if ((!goodsTable.getSelectionModel().isEmpty()))
            {   order = goodsTable.getSelectionModel().getSelectedItem();
                Main.client.setMessage("DelFromOrder");
                Main.client.SendMess();
                Main.client.setMessage(order);
                Main.client.SendMess();
                try {
                    if (Main.client.RecvMess().equals("ok"))
                    {  JOptionPane.showMessageDialog(null, "Удаление выполнено успешно");
                        int row = goodsTable.getSelectionModel().getSelectedIndex();
                        goodsTable.getItems().remove(row);
                    }
                    else   JOptionPane.showMessageDialog(null, "Ошибка!");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }} else JOptionPane.showMessageDialog(null, "Данные не выбраны");
            goodsTable.getSelectionModel().clearSelection();
            chPhone.setSelected(false);
            chEmail.setSelected(false);
            chDate.setSelected(false);
            chAmount.setSelected(false);
            chAddress.setSelected(false);
            amountField.clear();
            addressField.clear();
            phoneField.clear();
            emailField.clear();
        });
    }
}
