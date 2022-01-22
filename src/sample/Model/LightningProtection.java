package sample.Model;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 */
public class LightningProtection extends Security {

    /**
     * Default constructor
     */
    public LightningProtection() {
    }

    public LightningProtection(int id, String name) {
        super(id, name);
        this.typeSecurity = name;
    }
    /**
     * 
     */
    public String preventsLightningStrike() {
        // TODO implement here
        return "Отражает попадание молний";
    }

    @Override
    public void provide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Предоставление услуги");

        alert.setHeaderText("ИД: " + this.getId() + "\nНазвание: " + this.getName() + "\nТип услуги: " + this.type +
                "\nТип жилищной услуги: " + this.typeHousingService + "\nТип безопасности: " + this.typeSecurity);

        String image = this.getClass().getResource("/sample/assets/icons_services/scared.png").toString();

        ImageView img = new ImageView(image);
        img.setFitHeight(70);
        img.setFitWidth(70);
        alert.setGraphic(img);

        alert.setContentText(this.preventsLightningStrike());

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(image));
        alert.showAndWait();
    }
}