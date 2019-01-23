package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Stock.Supplies.Supplie;
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

import javax.swing.*;

public class DelSuppl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button delButton;

    @FXML
    private Button back;


    @FXML
    private ComboBox<Integer> IDs;   @FXML


    Supplie supplie;

    @FXML
    void initialize() {
        ObservableList<Integer> showlist = FXCollections.observableArrayList();
        try {
            Main.client.setMessage("AllSupplID");
            Main.client.SendMess();
            ArrayList<Integer> mas = (ArrayList<Integer>) Main.client.RecvMess();
            for (int i = 0; i < mas.size(); i++)
                showlist.add(mas.get(i));
            IDs.setItems(showlist);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


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
        delButton.setOnAction(event -> {
            if (!IDs.getSelectionModel().isEmpty())
            {
                supplie = new Supplie();
                //    System.out.println(IDs.getSelectionModel().getSelectedItem().);
                supplie.setId(IDs.getSelectionModel().getSelectedItem());
                Main.client.setMessage("DelSuppl");
                Main.client.SendMess();
                Main.client.setMessage(supplie);
                Main.client.SendMess();
                try {
                    if (Main.client.RecvMess().equals("ok"))
                        JOptionPane.showMessageDialog(null, "Поставка удалена");
                    else   JOptionPane.showMessageDialog(null, "Ошибка!");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
