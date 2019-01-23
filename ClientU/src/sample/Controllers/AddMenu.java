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
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

public class AddMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboShow;

    @FXML
    private Button ShowInfo;


    @FXML
    private Button back;

    @FXML
    void initialize() {
        //     assert comboShow != null : "fx:id=\"comboShow\" was not injected: check your FXML file 'ShowMenu.fxml'.";
        ObservableList<String> showlist = FXCollections.observableArrayList("Товары / одежда","Товары / обувь","Товары / аксессуары", "Заказ", "Поставка");
        comboShow.setItems(showlist);
        ShowInfo.setOnAction(event -> {
            switch (comboShow.getSelectionModel().getSelectedItem())
            {
                case "Товары / одежда":

                    Stage stage = (Stage) ShowInfo.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddProd.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root1));
                    try{
                        stage.show();}
                    catch (java.lang.IllegalStateException e){}
                    break;
                case "Товары / обувь":
                 //   Main.client.setMessage("ShowAllProd2");
                 //   Main.client.SendMess();
                    stage = (Stage) ShowInfo.getScene().getWindow();
                    stage.close();
                    fxmlLoader = new FXMLLoader(getClass().getResource("/AddProd2.fxml"));
                    root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root1));
                    try{
                        stage.show();}
                    catch (java.lang.IllegalStateException e){}
                    break;
                case "Товары / аксессуары":
                    //Main.client.setMessage("ShowAllProd3");
                 //   Main.client.SendMess();
                    stage = (Stage) ShowInfo.getScene().getWindow();
                    stage.close();
                    fxmlLoader = new FXMLLoader(getClass().getResource("/AddProd3.fxml"));
                    root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root1));
                    try{
                        stage.show();}
                    catch (java.lang.IllegalStateException e){}

                    break;
                case "Заказ":
                    //Main.client.setMessage("ShowAllProd3");
                    //   Main.client.SendMess();
                    stage = (Stage) ShowInfo.getScene().getWindow();
                    stage.close();
                    fxmlLoader = new FXMLLoader(getClass().getResource("/AddCustomer.fxml"));
                    root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root1));
                    try{
                        stage.show();}
                    catch (java.lang.IllegalStateException e){}

                    break;
                case "Поставка":
                    //Main.client.setMessage("ShowAllProd3");
                    //   Main.client.SendMess();
                    stage = (Stage) ShowInfo.getScene().getWindow();
                    stage.close();
                    fxmlLoader = new FXMLLoader(getClass().getResource("/AddSupplier.fxml"));
                    root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root1));
                    try{
                        stage.show();}
                    catch (java.lang.IllegalStateException e){}

                    break;
                    default: break;
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

