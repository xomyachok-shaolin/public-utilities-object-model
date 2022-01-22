/**
 * Sample Skeleton for 'PackagesHMM.fxml' Controller Class
 */

package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.Model.PackageHousingCommunalServices;
import sample.animations.Shake;

public class PackagesController {

    @FXML // fx:id="btnBack"
    private Button btnBack; // Value injected by FXMLLoader

    @FXML // fx:id="fieldNamePackage"
    private TextField fieldNamePackage; // Value injected by FXMLLoader

    @FXML // fx:id="btnRemovePackage"
    private Button btnRemovePackage; // Value injected by FXMLLoader

    @FXML // fx:id="btnNewPackage"
    private Button btnNewPackage; // Value injected by FXMLLoader

    @FXML // fx:id="lbl"
    private Label lbl; // Value injected by FXMLLoader

    @FXML // fx:id="btnEditPackage"
    private Button btnEditPackage; // Value injected by FXMLLoader

    @FXML // fx:id="tablePackages"
    private TableView<PackageHousingCommunalServices> tablePackages; // Value injected by FXMLLoader

    @FXML // fx:id="namesPackages"
    private TableColumn<PackageHousingCommunalServices, String> namesPackages; // Value injected by FXMLLoader

    @FXML // fx:id="numbersPackages"
    private TableColumn<PackageHousingCommunalServices, Integer> numbersPackages; // Value injected by FXMLLoader

    private ObservableList<PackageHousingCommunalServices> items = FXCollections.observableArrayList();

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        btnBack.setTooltip(new Tooltip("Назад к списку ЖЭУ"));
        btnNewPackage.setTooltip(new Tooltip("Создать новый пакет ЖКУ"));
        btnEditPackage.setTooltip(new Tooltip("Показать выбранный пакет ЖКУ"));
        btnRemovePackage.setTooltip(new Tooltip("Удалить выбранный пакет ЖКУ"));

        String nameHMM = lbl.getText();
        nameHMM += " «"+ Main.selectedMO.getName() + "»:";
        lbl.setText(nameHMM);

        if (Main.selectedMO.getPackages() != null) {
            items.addAll(Main.selectedMO.getPackages());
        }

        namesPackages.setCellValueFactory(new PropertyValueFactory<>("name"));
        numbersPackages.setCellValueFactory(new PropertyValueFactory<>("ID"));

        tablePackages.setItems(items);

        btnBack.setOnAction(event -> Main.window.setScene(Main.sceneMain));

        btnNewPackage.setOnAction(event -> {
            String namePackage = fieldNamePackage.getText().trim();
            if (namePackage.equals("")) {
                Shake fieldAnim = new Shake(fieldNamePackage);
                fieldAnim.playAnim();
            } else {
                PackageHousingCommunalServices temp = new PackageHousingCommunalServices(Main.selectedMO.counterID++, namePackage);
                items.add(temp);
                Main.selectedMO.addPackageHCS(temp);
            }
            fieldNamePackage.setText("");
        });
        fieldNamePackage.setOnAction(event -> {
            String namePackage = fieldNamePackage.getText().trim();
            if (namePackage.equals("")) {
                Shake fieldAnim = new Shake(fieldNamePackage);
                fieldAnim.playAnim();
            } else {
                PackageHousingCommunalServices temp = new PackageHousingCommunalServices(Main.selectedMO.counterID++, namePackage);
                items.add(temp);
                Main.selectedMO.addPackageHCS(temp);
                System.out.println(namePackage);
            }
            fieldNamePackage.setText("");
        });
        btnRemovePackage.setOnAction(event -> {
            if (tablePackages.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка при удалении!");
                alert.setHeaderText("Для удаления определенного пакета, необходимо его выбрать в таблице!");
                alert.showAndWait();
            } else {
                PackageHousingCommunalServices temp = tablePackages.getSelectionModel().getSelectedItem();
                Main.selectedMO.removePackageHCS(temp);
                items.remove(temp);
            }
        });
        btnEditPackage.setOnAction(event -> {
            if (tablePackages.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка доступа!");
                alert.setHeaderText("Для просмотра определенного пакета, необходимо его выбрать в таблице!");
                alert.showAndWait();
            } else {
                showPackage();
            }
        });
        tablePackages.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
                if (!tablePackages.getSelectionModel().isEmpty())
                    showPackage();
        });
    }
    private void showPackage() {
        Main.selectedPackageHCS = tablePackages.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/views/PackageHCS.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        Main.scenePackageHCS = new Scene(root);
        Main.window.setScene(Main.scenePackageHCS);
    }
}