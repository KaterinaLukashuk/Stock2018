package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Document.Document;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;

public class ShowOrders {
    Document document;

    @FXML
    private TextField OrderAmount;

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
    private TextField ordID;

    @FXML
    private Button filtr;

    @FXML
    private Button filtr1;

    @FXML
    private TextField OrderPrice;

    @FXML
    private Button doc;

    @FXML
    void initialize() {
        ordID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,20}")) {
                    ordID.setText(oldValue);
                }
            }
        });
        filtr1.setDisable(true);
        doc.setDisable(true);
        OrderPrice.setEditable(false);
        OrderAmount.setEditable(false);
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


        back.setOnAction(event -> {
            System.out.println("1");
            Stage stage = (Stage) back.getScene().getWindow();
            System.out.println("2");
            stage.close();
            System.out.println("3");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu.fxml"));
            System.out.println("4");
            Parent root1 = null;
            System.out.println("5");
            try {
                root1 = (Parent) fxmlLoader.load();
                System.out.println("6");
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            System.out.println("7");
            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println("8");
            stage.setScene(new Scene(root1));
            System.out.println("9");
            stage.show();
        });

        filtr.setOnAction(event -> {
            if (ordID.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Условие не выбрано!");
            else {
                Main.client.setMessage("ShowOrderByID");
                Main.client.SendMess();
                Main.client.setMessage(Integer.parseInt(ordID.getText()));
                Main.client.SendMess();
                ObservableList<Order> data1 = FXCollections.observableArrayList();
                ArrayList<Order> mas1 = null;
                // ArrayList<Product> mas = null;
                try {
                    mas1 = (ArrayList<Order>) Main.client.RecvMess();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                for (int i=0; i< mas1.size(); i++)
                    data1.add(mas1.get(i));
                goodsTable.getItems().clear();
                try{
                    goodsTable.setItems(data1);}
                catch (java.lang.IllegalStateException e){}
                try {
                    double prdprs =(double) Main.client.RecvMess();
                    System.out.println(prdprs);
                    OrderPrice.setText(String.valueOf(prdprs));
                    int ordsa = (int)Main.client.RecvMess();
                    OrderAmount.setText(String.valueOf(ordsa));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                filtr1.setDisable(false);
                doc.setDisable(false);
            }
        });
        filtr1.setOnAction(event -> {
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
                goodsTable.setItems(data2);
                ordID.clear();
                OrderPrice.clear();
                OrderAmount.clear();
                filtr1.setDisable(true);
                doc.setDisable(true);
            }
            catch (java.lang.IllegalStateException e){}

        });

        doc.setOnAction(event -> {
            if (!ordID.getText().isEmpty())
            { Main.client.setMessage("ShowOrderByID");
            Main.client.SendMess();
            Main.client.setMessage(Integer.parseInt(ordID.getText()));
            Main.client.SendMess();
            try {
                ArrayList<Order> mas3 =(ArrayList<Order>) Main.client.RecvMess();
                double prdprs =(double) Main.client.RecvMess();
                int x = (int) Main.client.RecvMess();
                document = new Document();
                document.write(mas3, Double.parseDouble(OrderPrice.getText()));
                JOptionPane.showMessageDialog(null, "Отчет сгенерирован!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }}
        });
    }
}
