package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Stock.Orders.Order;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;


public class AddCustomer {
    static Order order = new Order();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nextButton;

    @FXML
    private TextField cust;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private Button back;

    @FXML
    private TextField address;

    @FXML
    private DatePicker date;

    @FXML
    void initialize() {
        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,15}")) {
                    phone.setText(oldValue);
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
        nextButton.setOnAction(event -> {
            try {
            if (!cust.getText().isEmpty())   order.setCostumer(cust.getText());
            //if (!date.getValue().toString().isEmpty())
                order.setOrderDate(date.getValue().toString());
            if (!address.getText().isEmpty())order.setAddress(address.getText());
            if (!phone.getText().isEmpty())order.setPhone(phone.getText());
            if (!email.getText().isEmpty())order.setEmail(email.getText());
            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddOrder.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();}
            catch (NullPointerException e){
                JOptionPane.showMessageDialog(null, "Данные не выбраны");}

        });
    }
}
