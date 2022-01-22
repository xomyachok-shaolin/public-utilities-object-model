package sample.Model;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 */
public class GarbageDisposal extends CommunalService {

    /**
     * Default constructor
     */
    public GarbageDisposal() {
    }

    public GarbageDisposal(int id, String name) {
        super(id, name);
        this.typeCommunalService = name;
    }

    /**
     * 
     */
    public String disposal() {
        // TODO implement here
        return "Удаляет ТКО";
    }

    @Override
    public void provide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Предоставление услуги");

        alert.setHeaderText("ИД: " + this.getId() + "\nНазвание: " + this.getName() + "\nТип услуги: " + this.type +
                "\nТип коммунальной услуги: " + this.typeCommunalService);

        String image = this.getClass().getResource("/sample/assets/icons_services/garbage.png").toString();

        ImageView img = new ImageView(image);
        img.setFitHeight(70);
        img.setFitWidth(70);
        alert.setGraphic(img);

        alert.setContentText(this.disposal());

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(image));
        alert.showAndWait();
    }
}