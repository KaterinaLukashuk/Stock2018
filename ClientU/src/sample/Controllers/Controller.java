package sample.Controllers;

//import com.gluonhq.charm.glisten.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Login;

    @FXML
    private PasswordField Pussword;

    @FXML
    private Button SignInButton;

    @FXML
    void initialize() {

//        assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'sample.fxml'.";
//        assert Pussword != null : "fx:id=\"Pussword\" was not injected: check your FXML file 'sample.fxml'.";
//        assert SignInButton != null : "fx:id=\"SignInButton\" was not injected: check your FXML file 'sample.fxml'.";
        SignInButton.setOnAction(event -> {
             // TODO: КОД ОБРАБОТЧИКА
            Main.client.setMessage("avtoriz");
            Main.client.SendMess();
            try {
                System.out.println(Main.client.RecvMess());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Main.client.setMessage(Login.getText());
            Main.client.SendMess();
            try {
                System.out.println(Main.client.RecvMess());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Main.client.setMessage(Pussword.getText());
            Main.client.SendMess();
            //System.out.println(Main.client.RecvMess());
            try {
                String answer = (String)Main.client.RecvMess();
                if (answer.equals("admin")) {
                    Main.dostup = true;
                    Stage stage = (Stage) SignInButton.getScene().getWindow();
                    stage.close();
                      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu.fxml"));
                //    FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("menu.fxml"));
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
                }else
                if (answer.equals("user")) {
                    Main.dostup = false;
                    Stage stage = (Stage) SignInButton.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu.fxml"));
                    //    FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("menu.fxml"));
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
                }else {
                    Login.clear();
                    Pussword.clear();
                    JOptionPane.showMessageDialog(null, "Ошибка входа в систему");
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
