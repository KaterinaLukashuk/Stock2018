package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Stock.Supplies.Supplie;
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

public class AddSupplier {
    public static Supplie supplier = new Supplie();

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
    private DatePicker date;

    @FXML
    void initialize() {
        nextButton.setOnAction(event -> {
            try {
                if (!cust.getText().isEmpty())
                    supplier.setSupplier(cust.getText());
                if (!email.getText().isEmpty())
                    supplier.setEmail(email.getText());
                if (!phone.getText().isEmpty())
                    supplier.setPhone(phone.getText());
                supplier.setSupplDate(date.getValue().toString());
                if (!cust.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty() && !date.getValue().toString().isEmpty())
                    {
                    Stage stage = (Stage) nextButton.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddSupply.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root1));
                    try {
                        stage.show();
                    } catch (java.lang.IllegalStateException e) {
                    }
                }
            } catch (NullPointerException e){JOptionPane.showMessageDialog(null, "Данные не выбраны");}
        });
    }
}
