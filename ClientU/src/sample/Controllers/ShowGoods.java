package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Stock.Goods.Clothes;
import Stock.Goods.Product;
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

public class ShowGoods {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Clothes> goodsTable;


    @FXML
    private TableColumn<Product, String> name;

//    @FXML
//    private TableColumn<?, ?> type;

    @FXML
    private TableColumn<Product, Double> prise;

    @FXML
    private TableColumn<Product, String> brand;

    @FXML
    private TableColumn<Product, Integer> size;


    @FXML
    private TableColumn<Product, Integer> id;
//////////////////////////////////////////////////////

    @FXML
    private Button back;


    @FXML
    private TableColumn<Product, Integer> amount;

    @FXML
    private TextField minPrice;

    @FXML
    private TextField maxPrice;

    @FXML
    private Button filtr;


    @FXML
    private Button filtr1;

    @FXML
    private ComboBox<String> combo;

    @FXML
    void initialize() {
        ObservableList<String> showlist = FXCollections.observableArrayList("По цене", "По количеству");
        combo.setItems(showlist);

        filtr1.setDisable(true);
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
        id.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        prise.setCellValueFactory(new PropertyValueFactory<Product, Double>("prise"));
        brand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        size.setCellValueFactory(new PropertyValueFactory<Product, Integer>("size"));
        amount.setCellValueFactory(new PropertyValueFactory<Product,Integer>("amount"));
        try{
       goodsTable.setItems(data);}
       catch (java.lang.IllegalStateException e){}
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

        filtr.setOnAction(event -> {
            if (!combo.getSelectionModel().isEmpty())
            {    if (minPrice.getText().isEmpty() || maxPrice.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Условия не выбраны!");
            else {
                if (combo.getSelectionModel().getSelectedItem().equals("По цене"))
                Main.client.setMessage("FiltrGoodsByPr");
                else  Main.client.setMessage("FiltrGoodsByAm");
                Main.client.SendMess();
                Main.client.setMessage(Integer.parseInt(minPrice.getText()));
                Main.client.SendMess();
                Main.client.setMessage(Integer.parseInt(maxPrice.getText()));
                Main.client.SendMess();
                ObservableList<Clothes> data1 = FXCollections.observableArrayList();
                ArrayList<Clothes> mas1 = null;
                // ArrayList<Product> mas = null;
                try {
                    mas1 = (ArrayList<Clothes>) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                for (int i=0; i< mas1.size(); i++)
                    data1.add(mas1.get(i));
                goodsTable.getItems().clear();
                try{
                    goodsTable.setItems(data1);}
                catch (java.lang.IllegalStateException e){}
                filtr1.setDisable(false);
            }} else JOptionPane.showMessageDialog(null, "Условия не выбраны!");
        });
        filtr1.setOnAction(event -> {
            Main.client.setMessage("ShowAllProd");
            Main.client.SendMess();
            ObservableList<Clothes> data2 = FXCollections.observableArrayList();
            ArrayList<Clothes> mas2 = null;
            // ArrayList<Product> mas = null;
            try {
                mas2 = (ArrayList<Clothes>) Main.client.RecvMess();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for (int i=0; i< mas2.size(); i++)
                data2.add(mas2.get(i));
            try{
                goodsTable.setItems(data2);}
            catch (java.lang.IllegalStateException e){}
            minPrice.clear();
            maxPrice.clear();
            filtr1.setDisable(true);
        });
    }
}
