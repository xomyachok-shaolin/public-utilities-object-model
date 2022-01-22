/* Файл Main.java									*/
/* ОТИ НИЯУ МИФИ									*/
/* 1ПО-37Д											*/
/* Минаев Александр Сергеевич						*/
/* Программирование (ООП)				            */
/* Программа PublicService							*/
/* Определяет точку входа для приложения			*/
/* Содержит описание основных глобальных переменных */
/* 22.12.19											*/

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Model.ManagementOrganization;
import sample.Model.PackageHousingCommunalServices;
import sample.Model.StateHousingInspectorate;

import java.util.ArrayList;

public class Main extends Application {

    public static Stage window;
    public static Scene sceneMain, sceneLicense, scenePackagesHMM, scenePackageHCS;

    public static int counterID = 1;
    public static StateHousingInspectorate SHI = new StateHousingInspectorate();
    public static ArrayList<ManagementOrganization> organizations = new ArrayList<>();
    public static ManagementOrganization selectedMO;
    public static PackageHousingCommunalServices selectedPackageHCS;

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/Main.fxml"));
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setTitle("Объектная модель коммунального хозяйства");

        window = stage;
        sceneMain = new Scene(root, 600, 400);
        stage.setScene(sceneMain);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
