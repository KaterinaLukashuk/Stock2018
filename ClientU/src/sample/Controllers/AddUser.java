package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;

public class AddUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField log;

    @FXML
    private Button back;

    @FXML
    private ComboBox<String> dostup;

    @FXML
    private PasswordField pass;

    @FXML
    void initialize() {
        ObservableList<String> showlist = FXCollections.observableArrayList("Администратор","Пользователь");
        dostup.setItems(showlist);
        addButton.setOnAction(event -> {
            if (!pass.getText().isEmpty() && !log.getText().isEmpty() && !dostup.getSelectionModel().isEmpty())
            {
                Main.client.setMessage("addUser");
                Main.client.SendMess();
                if (dostup.getSelectionModel().getSelectedItem().equals("Администратор"))
               Main.client.setMessage(true);
                else  Main.client.setMessage(false);
                Main.client.SendMess();
                Main.client.setMessage(log.getText());
                Main.client.SendMess();
                Main.client.setMessage(pass.getText());
                Main.client.SendMess();
                try {
                  String s = (String)  Main.client.RecvMess();
                  if (s.equals("ok")) JOptionPane.showMessageDialog(null, "ПОльзователь добавлен");
                  else JOptionPane.showMessageDialog(null, "Ошибка");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                pass.clear();
                log.clear();
            }else JOptionPane.showMessageDialog(null, "Данные не выбраны");
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
