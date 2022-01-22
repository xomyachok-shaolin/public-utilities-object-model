package sample.Model;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 */
public class ElevatorService extends HousingService {

    /**
     * Default constructor
     */
    public ElevatorService() {
    }

    public ElevatorService(int id, String name) {
        super(id, name);
        this.typeHousingService = name;
    }

    /**
     * 
     */
    public String lift() {
        // TODO implement here
        return "Лифт работает";
    }

    @Override
    public void provide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Предоставление услуги");

        alert.setHeaderText("ИД: " + this.getId() + "\nНазвание: " + this.getName() + "\nТип услуги: " + this.type +
                "\nТип жилищной услуги: " + this.typeHousingService);

        String image = this.getClass().getResource("/sample/assets/icons_services/lift.png").toString();

        ImageView img = new ImageView(image);
        img.setFitHeight(70);
        img.setFitWidth(70);
        alert.setGraphic(img);

        alert.setContentText(this.lift());

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(image));
        alert.showAndWait();
    }
}