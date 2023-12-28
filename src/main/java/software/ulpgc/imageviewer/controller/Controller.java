package software.ulpgc.imageviewer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import software.ulpgc.imageviewer.presenter.ImageDisplay;
import software.ulpgc.imageviewer.view.ImageLoader;
import software.ulpgc.imageviewer.presenter.JavaFXImageDisplay;
import software.ulpgc.imageviewer.view.JavaFXImageLoader;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button chooseButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private VBox imageFrame;
    @FXML
    private ImageView imageView;
    @FXML
    private Stage stage;

    private ImageDisplay imageDisplay;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.imageDisplay = new JavaFXImageDisplay(imageFrame, imageView);
    }

    public void setStage(Stage stage) {
        this.stage=stage;
        JavaFXImageDisplay imageDisplay = (JavaFXImageDisplay) this.imageDisplay;
        imageDisplay.setStage(this.stage);
    }

    @FXML
    private void chooseDirectory(){
        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Select directory");

        File selectedDir = directoryChooser.showDialog(stage);

        if (selectedDir == null) return;

        ImageLoader imageLoader = new JavaFXImageLoader(selectedDir.getAbsolutePath());
        this.imageDisplay.setImage(imageLoader.load());
    }

    @FXML
    private void selectLeftImage() {
        this.imageDisplay.setLeftImage();
    }

     @FXML
    private void selectRightImage() {
        this.imageDisplay.setRightImage();
    }


}