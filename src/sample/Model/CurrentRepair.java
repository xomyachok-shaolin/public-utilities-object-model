package sample.Model;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 */
public class CurrentRepair extends Repair {

    /**
     * Default constructor
     */
    public CurrentRepair() {
    }

    public CurrentRepair(int id, String name) {
        super(id, name);
        this.typeRepair = name;
    }

    /**
     * 
     */
    public String repair() {
        // TODO implement here
        return "Выполняет ремонтные работы, которые не превышают тридцати процентов от ремонтируемого имущества";
    }

    /**
     *
     */
    @Override
    public void provide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Предоставление услуги");

        alert.setHeaderText("ИД: " + this.getId() + "\nНазвание: " + this.getName() + "\nТип услуги: " + this.type +
                "\nТип жилищной услуги: " + this.typeHousingService + "\nТип ремонта: " + this.typeRepair);

        String image = this.getClass().getResource("/sample/assets/icons_services/repair.png").toString();

        ImageView img = new ImageView(image);
        img.setFitHeight(70);
        img.setFitWidth(70);
        alert.setGraphic(img);

        alert.setContentText(this.repair());

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(image));
        alert.showAndWait();
    }
}