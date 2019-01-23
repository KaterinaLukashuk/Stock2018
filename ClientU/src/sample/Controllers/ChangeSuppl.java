package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Stock.Orders.Order;
import Stock.Supplies.Supplie;
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

public class ChangeSuppl {
    Supplie supplie;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Supplie> goodsTable;

    @FXML
    private TableColumn<Supplie, Integer> id;

    @FXML
    private TableColumn<Supplie, String> cust;

    @FXML
    private TableColumn<Supplie, Integer> prod_id;

    @FXML
    private TableColumn<Supplie, Integer> amount;

    @FXML
    private TableColumn<Supplie, String> date;


    @FXML
    private TableColumn<Supplie, String> phone;

    @FXML
    private TableColumn<Supplie, String> email;

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

        Main.client.setMessage("ShowAllSuppl");
        Main.client.SendMess();
        ObservableList<Supplie> data = FXCollections.observableArrayList();
        ArrayList<Supplie> mas = null;
        try {
            mas = (ArrayList<Supplie>) Main.client.RecvMess();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i< mas.size(); i++)
            data.add(mas.get(i));
        id.setCellValueFactory(new PropertyValueFactory<Supplie, Integer>("id"));
        prod_id.setCellValueFactory(new PropertyValueFactory<Supplie, Integer>("product_id"));
        cust.setCellValueFactory(new PropertyValueFactory<Supplie,String>("supplier"));
        date.setCellValueFactory(new PropertyValueFactory<Supplie,String>("supplDate"));
        phone.setCellValueFactory(new PropertyValueFactory<Supplie,String>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<Supplie,String>("email"));
        amount.setCellValueFactory(new PropertyValueFactory<Supplie,Integer>("amount"));
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
            supplie = new Supplie();
            if ((!goodsTable.getSelectionModel().isEmpty()))
            {   supplie = goodsTable.getSelectionModel().getSelectedItem();
             //   if (chAddress.isSelected() && !addressField.getText().isEmpty()) supplie.setAddress(addressField.getText());
                if (chAmount.isSelected()  && !amountField.getText().isEmpty() && Integer.parseInt(amountField.getText())!=0)  supplie.setAmount(Integer.parseInt(amountField.getText()));
                if (chDate.isSelected() && !dateField.getValue().toString().isEmpty())supplie.setSupplDate(dateField.getValue().toString());
                if(chEmail.isSelected() && !emailField.getText().isEmpty()) supplie.setEmail(emailField.getText());
                if (chPhone.isSelected()  && !phoneField.getText().isEmpty()) supplie.setPhone(phoneField.getText());
                Main.client.setMessage("ChangeSuppl");
                Main.client.SendMess();
                Main.client.setMessage(supplie);
                Main.client.SendMess();
                try {
                    if (Main.client.RecvMess().equals("ok"))
                    {  JOptionPane.showMessageDialog(null, "Редактирование выполнено успешно");
                        //todo: обновить таблицу
                        goodsTable.getItems().clear();
                        Main.client.setMessage("ShowAllSuppl");
                        Main.client.SendMess();
                        ObservableList<Supplie> data2 = FXCollections.observableArrayList();
                        ArrayList<Supplie> mas2 = null;
                        try {
                            mas2 = (ArrayList<Supplie>) Main.client.RecvMess();
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
        //    chAddress.setSelected(false);
            amountField.clear();
        //    addressField.clear();
            phoneField.clear();
            emailField.clear();
        });
        del.setOnAction(event -> {
            supplie = new Supplie();
            if ((!goodsTable.getSelectionModel().isEmpty()))
            {   supplie = goodsTable.getSelectionModel().getSelectedItem();
                Main.client.setMessage("DelFromSuppl");
                Main.client.SendMess();
                Main.client.setMessage(supplie);
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
            amountField.clear();
            phoneField.clear();
            emailField.clear();
        });
    }
}
