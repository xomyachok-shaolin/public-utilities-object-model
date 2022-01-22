/**
 * Sample Skeleton for 'Main.fxml' Controller Class
 */

package sample.controllers;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.Main;
import sample.Model.License;
import sample.Model.ManagementOrganization;

public class MainController {

    @FXML // fx:id="btnAddMO"
    private Button btnAddMO; // Value injected by FXMLLoader

    @FXML // fx:id="btnShowLicense"
    private Button btnShowLicense; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddLicense"
    private Button btnAddLicense; // Value injected by FXMLLoader

    @FXML // fx:id="btnShowPackages"
    private Button btnShowPackages; // Value injected by FXMLLoader

    @FXML // fx:id="btnRemoveMO"
    private Button btnRemoveMO; // Value injected by FXMLLoader

    @FXML // fx:id="tableMO"
    public TableView<ManagementOrganization> tableMO; // Value injected by FXMLLoader

    @FXML // fx:id="idHMM"
    private TableColumn<ManagementOrganization, Integer> idMO; // Value injected by FXMLLoader

    @FXML // fx:id="numbersINN"
    private TableColumn<ManagementOrganization, Integer> numbersINN; // Value injected by FXMLLoader

    @FXML // fx:id="namesHMM"
    private TableColumn<ManagementOrganization, String> namesMO; // Value injected by FXMLLoader

    @FXML // fx:id="licensesHMM"
    private TableColumn<ManagementOrganization, String> licensesMO; // Value injected by FXMLLoader

    private ObservableList<ManagementOrganization> items = FXCollections.observableArrayList(Main.organizations);

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        btnAddMO.setTooltip(new Tooltip("Добавить УО"));
        btnAddLicense.setTooltip(new Tooltip("Лицензировать выбранную УО"));
        btnRemoveMO.setTooltip(new Tooltip("Удалить выбранную УО"));
        btnShowLicense.setTooltip(new Tooltip("Показать список лицензий ГЖИ"));
        btnShowPackages.setTooltip(new Tooltip("Показать список пакетов услуг,\nвыбранной УО"));

        items.addAll(Main.organizations);

        idMO.setCellValueFactory(new PropertyValueFactory<>("id"));
        numbersINN.setCellValueFactory(new PropertyValueFactory<>("numberINN"));
        namesMO.setCellValueFactory(new PropertyValueFactory<>("name"));
        licensesMO.setCellValueFactory(new PropertyValueFactory<>("license"));

        tableMO.setItems(items);

        btnAddMO.setOnAction(event -> showInputTextDialog());
        btnShowLicense.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/views/ListLicenses.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            Main.sceneLicense = new Scene(root);
            Main.window.setScene(Main.sceneLicense);

            tableMO.refresh();
        });
        btnRemoveMO.setOnAction(event -> {
            if (tableMO.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка при удалении!");
                alert.setHeaderText("Для удаления определенной управляющей организации, необходимо её выбрать в таблице!");
                alert.showAndWait();
            } else {
                ManagementOrganization temp = tableMO.getSelectionModel().getSelectedItem();
                Main.organizations.remove(temp);
                Main.SHI.removeLicense(temp.getLicense());
                tableMO.refresh();
                items.remove(temp);
            }
        });
        btnAddLicense.setOnAction(event -> {
            if (tableMO.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка при лицензировании!");
                alert.setHeaderText("Для лицензирования определенной управляющей организации, необходимо её выбрать в таблице!");
                alert.showAndWait();
            } else {
                ManagementOrganization temp = tableMO.getSelectionModel().getSelectedItem();
                if (temp.getLicense() != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Перелицензирование");
                    alert.setHeaderText("Данная управляющая организация уже лицензировано!");
                    alert.setContentText("Вы действительно хотите перелицензировать данную управляющую организацию?");

                    String image = this.getClass().getResource("/sample/assets/icons_services/license.png").toString();
                    ImageView img = new ImageView(image);
                    img.setFitHeight(70);
                    img.setFitWidth(70);
                    alert.setGraphic(img);

                    alert.getButtonTypes().clear();
                    ButtonType addButtonType = new ButtonType("Да", ButtonBar.ButtonData.OK_DONE);
                    ButtonType cancelButtonType = new ButtonType("Нет", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getDialogPane().getButtonTypes().addAll(addButtonType, cancelButtonType);

                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(image));
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get() == addButtonType) {
                        Main.SHI.removeLicense(temp.getLicense());
                        License l = new License(temp.getNumberINN());
                        Main.SHI.addLicense(l);
                        for (ManagementOrganization it : Main.organizations)
                            if (it == temp)
                                it.setLicense(l);
                        tableMO.refresh();
                    }
                } else {
                    License l = new License(temp.getNumberINN());
                    Main.SHI.addLicense(l);
                    for (ManagementOrganization it : Main.organizations)
                        if (it == temp)
                            it.setLicense(l);
                    tableMO.refresh();
                }
            }
        });
        btnShowPackages.setOnAction(event -> {

            if (!tableMO.getSelectionModel().isEmpty()) {
                showPackages();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText("Для просмотра пакетов услуг определенной управляющей организации, необходимо её выбрать в таблице!");
                alert.showAndWait();
            }
        });
        tableMO.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
                if (!tableMO.getSelectionModel().isEmpty())
                    showPackages();
        });
    }

    private void showPackages() {
        Main.selectedMO = tableMO.getSelectionModel().getSelectedItem();

        if (Main.selectedMO.getLicense() != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/views/PackagesHMM.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Main.scenePackagesHMM = new Scene(root);
            Main.window.setScene(Main.scenePackagesHMM);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка доступа!");
            alert.setHeaderText("Для просмотра пакетов услуг необходимо лицензировать данную управляющую организацию!");
            alert.showAndWait();
        }
    }

    private void showInputTextDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Добавление УО");
        dialog.setHeaderText("Пожалуйста, введите название управляющей организации и соответствующий ИНН");

        String image = this.getClass().getResource("/sample/assets/icons_services/info.png").toString();
        ImageView img = new ImageView(image);
        img.setFitHeight(70);
        img.setFitWidth(70);
        dialog.setGraphic(img);

        ButtonType addButtonType = new ButtonType("Добавить", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, cancelButtonType);

        // Создание меток и полей для имени и номера инн
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField fieldName = new TextField();
        fieldName.setPromptText("Любое название");
        TextField numberINN = new TextField();
        numberINN.setPromptText("12345678");

        grid.add(new Label("Название управляющей организации:"), 0, 0);
        grid.add(fieldName, 1, 0);
        grid.add(new Label("ИНН:"), 0, 1);
        grid.add(numberINN, 1, 1);

        // Включить / отключить кнопку добавления в зависимости от того, было ли введено название и корректный инн
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(numberINN.textProperty(),fieldName.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return (fieldName.getText().isEmpty()
                        || numberINN.getText().isEmpty() || !itsNumber(numberINN.getText()));
            }
        };
        addButton.disableProperty().bind(bb);

        dialog.getDialogPane().setContent(grid);
        // Запросить фокус на поле имени по умолчанию.
        Platform.runLater(fieldName::requestFocus);
        // Преобразовть результат в пару имя пользователя-пароль при нажатии кнопки входа.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType)
                return new Pair<>(numberINN.getText().trim(), fieldName.getText().trim());
            return null;
        });

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(image));

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(nameINN -> {
            ManagementOrganization temp = new ManagementOrganization(Main.counterID++,
                    nameINN.getKey(), nameINN.getValue());
            items.add(temp);
            Main.organizations.add(temp);
        });
    }
    private static boolean itsNumber(String s ){
        return s.matches("\\d+\\.\\d+") || s.matches("\\d+");
    }
}