package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

public class Menu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Show;

    @FXML
    private Button Add;

    @FXML
    private Button Red;

    @FXML
    private Button Del;

    @FXML
    private Button gr;

    @FXML
    private Button addUser;

    @FXML
    private Button save;

    @FXML
    void initialize() {
        if (!Main.dostup) {
            addUser.setDisable(true);
            Add.setDisable(true);
            Del.setDisable(true);
            Red.setDisable(true);

        }
        Show.setOnAction(event -> {//System.out.println("hello");
            Stage stage = (Stage) Show.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ShowMenu.fxml"));
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
        Add.setOnAction(event -> {
            Stage stage = (Stage) Add.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddMenu.fxml"));
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
        Del.setOnAction(event -> {
            Stage stage = (Stage) Del.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/DelMenu.fxml"));
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
        Red.setOnAction(event -> {
            Stage stage = (Stage) Red.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ChangeMenu.fxml"));
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
        gr.setOnAction(event -> {
            Main.client.setMessage("grafic");
            Main.client.SendMess();
            try {
                int x = (int) Main.client.RecvMess();
                int y = (int) Main.client.RecvMess();
                int z = (int)Main.client.RecvMess();
                PieChart pie =  new PieChart();
                PieChart.Data slice1 = new PieChart.Data("Одежда", x);
                PieChart.Data slice2 = new PieChart.Data("Обувь", y);
                PieChart.Data slice3 = new PieChart.Data("Аксессуары", z);
                pie.getData().add(slice1);
                pie.getData().add(slice2);
                pie.getData().add(slice3);
                pie.setLegendSide(Side.LEFT);
                Stage primaryStage = new Stage();
                StackPane root = new StackPane(pie);
                Scene scene = new Scene(root, 400, 200);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
        save.setOnAction(event -> {
            Main.client.setMessage("SaveData");
            Main.client.SendMess();
        });
        addUser.setOnAction(event -> {
            Stage stage = (Stage) addUser.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddUser.fxml"));
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

