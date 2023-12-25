package software.ulpgc.imageviewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("javaFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Image Viewer");
        stage.setScene(scene);

        stage.setOnShown(event -> {
            Controller controller = fxmlLoader.getController();
            controller.setStage(stage);
        });

        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}