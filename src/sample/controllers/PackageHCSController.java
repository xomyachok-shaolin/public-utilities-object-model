/**
 * Sample Skeleton for 'PackageHCS.fxml' Controller Class
 */

package sample.controllers;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.controlsfx.dialog.CommandLinksDialog;
import org.controlsfx.dialog.CommandLinksDialog.CommandLinksButtonType;
import sample.Main;
import sample.Model.*;

public class PackageHCSController {
    @FXML // fx:id="btnBack"
    private Button btnBack; // Value injected by FXMLLoader

    @FXML // fx:id="btnProvideHCS"
    private Button btnProvideHCS; // Value injected by FXMLLoader

    @FXML // fx:id="lbl"
    private Label lbl; // Value injected by FXMLLoader

    @FXML // fx:id="btnRemoveHCS"
    private Button btnRemoveHCS; // Value injected by FXMLLoader

    @FXML // fx:id="btnNewHCS"
    private Button btnNewHCS; // Value injected by FXMLLoader

    @FXML // fx:id="nameHCSs"
    private TableColumn<HousingCommunalService, String> nameHCSs; // Value injected by FXMLLoader

    @FXML // fx:id="idHCSs"
    private TableColumn<HousingCommunalService, Integer> idHCSs; // Value injected by FXMLLoader

    @FXML // fx:id="tableHCSs"
    private TableView<HousingCommunalService> tableHCSs; // Value injected by FXMLLoader

    private ObservableList<HousingCommunalService> items = FXCollections.observableArrayList();

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        btnBack.setTooltip(new Tooltip("Назад к списку пакетов ЖЭУ"));
        btnProvideHCS.setTooltip(new Tooltip("Предоставить выбранную ЖКУ"));
        btnNewHCS.setTooltip(new Tooltip("Добавить ЖКУ в пакет"));
        btnRemoveHCS.setTooltip(new Tooltip("Удалить выбранную ЖКУ из пакета"));

        String namePackage = lbl.getText();
        namePackage += " «"+ Main.selectedPackageHCS.getName() + "»:";
        lbl.setText(namePackage);

        if (Main.selectedPackageHCS.getPackageHCS() != null)
            items.addAll(Main.selectedPackageHCS.getPackageHCS());

        nameHCSs.setCellValueFactory(new PropertyValueFactory<>("name"));
        idHCSs.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableHCSs.setItems(items);

        btnNewHCS.setOnAction(event -> {

            HousingCommunalService temp = getHCService(Main.selectedPackageHCS.counterID++);
            if (temp == null) return;
            if (Main.selectedPackageHCS.addHCS(temp))
                items.add(temp);
            else {
                Main.selectedPackageHCS.counterID--;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка при добавлении услуги!");
                alert.setHeaderText("Эта услуга уже имеется в данном пакете.");
                alert.showAndWait();
            }
        });

        btnRemoveHCS.setOnAction(event -> {
            if (tableHCSs.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка при удалении!");
                alert.setHeaderText("Для удаления определенной услуги из пакета, необходимо её выбрать в таблице!");
                alert.showAndWait();
            } else {
                HousingCommunalService temp = tableHCSs.getSelectionModel().getSelectedItem();
                Main.selectedPackageHCS.removeHCS(temp);
                items.remove(temp);
            }
        });

        btnBack.setOnAction(event -> Main.window.setScene(Main.scenePackagesHMM));

        btnProvideHCS.setOnAction(event -> {
            if (tableHCSs.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка доступа!");
                alert.setHeaderText("Для предоставления определенной услуги из пакета, необходимо её выбрать в таблице!");
                alert.showAndWait();
            } else {
                tableHCSs.getSelectionModel().getSelectedItem().provide();
            }
        });

        tableHCSs.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
                if (!tableHCSs.getSelectionModel().isEmpty())
                    tableHCSs.getSelectionModel().getSelectedItem().provide();
        });
    }

    private HousingCommunalService getHCService(int id) {
        String image = this.getClass().getResource("/sample/assets/icons_services/search.png").toString();

        List<CommandLinksButtonType> link = Arrays
                .asList(
                        new CommandLinksButtonType("Коммунальные услуги",
                                "",false),
                        new CommandLinksButtonType("Жилищные услуги",
                                "", false));

        CommandLinksDialog dg = new CommandLinksDialog(link);
        dg.setTitle("Выбор типа функций предоставления услуг");
        Stage stage = (Stage) dg.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(image));
        dg.showAndWait();

        switch (dg.getResult().getText()){
            case "Коммунальные услуги":
                List<CommandLinksButtonType> linkCom = Arrays
                        .asList(
                                new CommandLinksButtonType("Энергоснабжение",
                                        "",false),
                                new CommandLinksButtonType("Водоснабжение",
                                        "", false),
                                new CommandLinksButtonType("Водоотведение",
                                        "", false),
                                new CommandLinksButtonType("Вывоз твердых коммунальных отходов",
                                        "", false));

                CommandLinksDialog dgCom = new CommandLinksDialog(linkCom);
                dgCom.setTitle("Выбор типа коммунальных услуг");
                stage = (Stage) dgCom.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(image));
                dgCom.showAndWait();

                switch (dgCom.getResult().getText()) {
                    case "Энергоснабжение":
                        List<CommandLinksButtonType> linkEnergy = Arrays
                                .asList(
                                        new CommandLinksButtonType("Электроснабжение",
                                                "",false),
                                        new CommandLinksButtonType("Теплоснабжение(отопление)",
                                                "", false),
                                        new CommandLinksButtonType("Газоснабжение",
                                                "", false));

                        CommandLinksDialog dgEnergy = new CommandLinksDialog(linkEnergy);
                        dgEnergy.setTitle("Выбор типа энергоснабжения");
                        stage = (Stage) dgEnergy.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(image));
                        dgEnergy.showAndWait();
                        switch (dgEnergy.getResult().getText()) {
                            case "Электроснабжение":
                                return new ElectricEnergy(id, "Электроснабжение");
                            case "Теплоснабжение(отопление)":
                                return new Heating(id, "Теплоснабжение(отопление)");
                            case "Газоснабжение":
                                return new GasEnergySupply(id, "Газоснабжение");
                            default:
                                break;
                        }
                        break;
                    case "Водоснабжение":
                        return new WaterSupply(id, "Водоснабжение");
                    case "Водоотведение":
                        return new WaterDisposal(id, "Водоотведение");
                    case "Вывоз твердых коммунальных отходов":
                        return new GarbageDisposal(id, "Вывоз ТКО");
                    default:
                        break;
                }
                break;
            case "Жилищные услуги":
                List<CommandLinksButtonType> linkHS = Arrays
                        .asList(
                                new CommandLinksButtonType("Безопасность",
                                        "",false),
                                new CommandLinksButtonType("Лифтовое хозяйство",
                                        "",false),
                                new CommandLinksButtonType("Уборка и санитарно-гигиеническая очистка",
                                        "",false),
                                new CommandLinksButtonType("Ремонт",
                                        "",false),
                                new CommandLinksButtonType("Благоустройство",
                                        "",false));

                CommandLinksDialog dgHS = new CommandLinksDialog(linkHS);
                dgHS.setTitle("Выбор типа жилищных услуг");
                stage = (Stage) dgHS.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(image));
                dgHS.showAndWait();
                switch (dgHS.getResult().getText()) {
                    case "Безопасность":
                        List<CommandLinksButtonType> linkSec = Arrays
                                .asList(
                                        new CommandLinksButtonType("Охранная безопасность",
                                                "",false),
                                        new CommandLinksButtonType("Пожарная безопасность",
                                                "", false),
                                        new CommandLinksButtonType("Молниезащита",
                                                "", false));

                        CommandLinksDialog dgSec = new CommandLinksDialog(linkSec);
                        dgSec.setTitle("Выбор типа безопасности");
                        stage = (Stage) dgSec.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(image));
                        dgSec.showAndWait();
                        switch (dgSec.getResult().getText()) {
                            case "Охранная безопасность":
                                List<CommandLinksButtonType> linkSecSafe = Arrays
                                        .asList(
                                                new CommandLinksButtonType("Охранная сигнализация",
                                                        "",false),
                                                new CommandLinksButtonType("Система контроля и управления доступом",
                                                        "", false),
                                                new CommandLinksButtonType("Видеонаблюдение",
                                                        "", false));

                                CommandLinksDialog dgSecSafe = new CommandLinksDialog(linkSecSafe);
                                dgSecSafe.setTitle("Выбор объекта охранной безопасности");
                                stage = (Stage) dgSecSafe.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(new Image(image));
                                dgSecSafe.showAndWait();
                                switch (dgSecSafe.getResult().getText()) {
                                    case "Охранная сигнализация":
                                        return new SecurityAlarm(id, "Охранная сигнализация");
                                    case "Система контроля и управления доступом":
                                        return new AccessControlSystem(id, "Система контроля и управления доступом");
                                    case "Видеонаблюдение":
                                        return new VideoMonitoring(id, "Видеонаблюдение");
                                    default:
                                        break;
                                }
                                break;
                            case "Пожарная безопасность":
                                return new FireSecurity(id, "Пожарная безопасность");
                            case "Молниезащита":
                                return new LightningProtection(id, "Молниезащита");
                            default:
                                break;
                        }
                        break;
                    case "Лифтовое хозяйство":
                        return new ElevatorService(id, "Лифтовое хозяйство");
                    case "Уборка и санитарно-гигиеническая очистка":
                        return new Hygiene(id, "Уборка и санитарно-гигиеническая очистка");
                    case "Ремонт":
                        List<CommandLinksButtonType> linkRepair = Arrays
                                .asList(
                                        new CommandLinksButtonType("Текущий ремонт",
                                                "",false),
                                        new CommandLinksButtonType("Капитальный ремонт",
                                                "", false));

                        CommandLinksDialog dgRepair = new CommandLinksDialog(linkRepair);
                        dgRepair.setTitle("Выбор необходимого ремонта");
                        stage = (Stage) dgRepair.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(image));
                        dgRepair.showAndWait();
                        switch (dgRepair.getResult().getText()) {
                            case "Текущий ремонт":
                                return new CurrentRepair(id, "Текущий ремонт");
                            case "Капитальный ремонт":
                                return new MajorRepair(id, "Капитальный ремонт");
                            default:
                                break;
                        }
                        break;
                    case "Благоустройство":
                        return new Beautification(id, "Благоустройство");
                    default:
                        break;
                }
            default:
                break;
        }
        return null;
    }
}
