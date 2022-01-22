package sample.Model;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 */
public class VideoMonitoring extends SecuritySafety {

    /**
     * Default constructor
     */
    public VideoMonitoring() {
    }

    public VideoMonitoring(int id, String name) {
        super(id, name);
        this.typeSecuritySafety = name;
    }

    /**
     * 
     */
    public String monitoring() {
        // TODO implement here
        return "Наблюдает за происходящим";
    }

    @Override
    public void provide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Предоставление услуги");

        alert.setHeaderText("ИД: " + this.getId() + "\nНазвание: " + this.getName() + "\nТип услуги: " + this.type +
                "\nТип жилищной услуги: " + this.typeHousingService + "\nТип безопасности: " + this.typeSecurity +
                "\nТип охранной безопасности: " + this.typeSecuritySafety);

        String image = this.getClass().getResource("/sample/assets/icons_services/monitoring.png").toString();

        ImageView img = new ImageView(image);
        img.setFitHeight(70);
        img.setFitWidth(70);
        alert.setGraphic(img);

        alert.setContentText(this.guard() + "\n" + this.monitoring());

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(image));
        alert.showAndWait();
    }
}