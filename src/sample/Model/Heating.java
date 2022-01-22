package sample.Model;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 */
public class Heating extends EnergySupply {

    /**
     * Default constructor
     */
    public Heating() {
    }

    public Heating(int id, String name) {
        super(id, name);
        this.typeEnergySupply = name;
    }

    /**
     * 
     */
    public String heat() {
        // TODO implement here
        return "Обогревает";
    }

    @Override
    public void provide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Предоставление услуги");

        alert.setHeaderText("ИД: " + this.getId() + "\nНазвание: " + this.getName() + "\nТип услуги: " + this.type +
                "\nТип коммунальной услуги: " + this.typeCommunalService + "\nТип энергоснабжения: " + this.typeEnergySupply);

        String image = this.getClass().getResource("/sample/assets/icons_services/heating.png").toString();

        ImageView img = new ImageView(image);
        img.setFitHeight(70);
        img.setFitWidth(70);
        alert.setGraphic(img);

        alert.setContentText(this.supply() + "\n" + this.heat());

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(image));
        alert.showAndWait();
    }
}