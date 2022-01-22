package sample.Model;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 */
public class Beautification extends HousingService {

    /**
     * Default constructor
     */
    public Beautification() {
    }

    public Beautification(int id, String name) {
        super(id, name);
        this.typeHousingService = name;
    }

    /**
     * 
     */
    public String  improve() {
        // TODO implement here
        return "Улучшает благоустройство(озеленение)";
    }

    /**
     *
     */
    @Override
    public void provide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Предоставление услуги");

        alert.setHeaderText("ИД: " + this.getId() + "\nНазвание: " + this.getName() + "\nТип услуги: " + this.type +
                "\nТип жилищной услуги: " + this.typeHousingService);

        String image = this.getClass().getResource("/sample/assets/icons_services/improve.png").toString();

        ImageView img = new ImageView(image);
        img.setFitHeight(70);
        img.setFitWidth(70);
        alert.setGraphic(img);

        alert.setContentText(this.improve());

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(image));
        alert.showAndWait();
    }

}