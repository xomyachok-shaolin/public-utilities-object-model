/**
 * Sample Skeleton for 'ListLicenses.fxml' Controller Class
 */

package sample.controllers;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.Model.License;
import sample.Model.ManagementOrganization;

public class LicensesController {

    @FXML // fx:id="btnBack"
    private Button btnBack;

    @FXML // fx:id="btnRemoveLicense"
    private Button btnRemoveLicense;

    @FXML // fx:id="numberINN"
    private TableColumn<License, Integer> numberINN; // Value injected by FXMLLoader

    @FXML // fx:id="tableSHI"
    private TableView<License> tableSHI; // Value injected by FXMLLoader

    @FXML // fx:id="dateLicense"
    private TableColumn<License, Date> dateLicense; // Value injected by FXMLLoader

    private ObservableList<License> items = FXCollections.observableArrayList();

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        btnBack.setTooltip(new Tooltip("Назад к списку ЖЭУ"));
        btnRemoveLicense.setTooltip(new Tooltip("Удалить выбранную лицензию"));

        items.addAll(Main.SHI.getLicenses());

        numberINN.setCellValueFactory(new PropertyValueFactory<>("numberINN"));
        dateLicense.setCellValueFactory(new PropertyValueFactory<>("dateOrder"));

        tableSHI.setItems(items);

        btnBack.setOnAction(event -> Main.window.setScene(Main.sceneMain));

        btnRemoveLicense.setOnAction(this::handle);
    }

    private void handle(ActionEvent event) {
        if (tableSHI.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка при удалении!");
            alert.setHeaderText("Для удаления определенной лицензии, необходимо её выбрать в таблице!");
            alert.showAndWait();
        } else {
            License l = tableSHI.getSelectionModel().getSelectedItem();
            for (ManagementOrganization it : Main.organizations)
                if (it.getLicense() == l)
                    it.setLicense(null);
            Main.SHI.removeLicense(l);
            items.remove(l);
        }
    }
}
