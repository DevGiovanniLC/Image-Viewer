package software.ulpgc.imageviewer;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import software.ulpgc.imageviewer.interfaces.ImageInterface;
import software.ulpgc.imageviewer.interfaces.ImageLoader;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button chooseButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private VBox imageDisplay;
    @FXML
    private ImageView imageView;
    @FXML
    Stage stage;

    private ImageInterface actualImage;

    private double offsetX;
    private double initialPosImage;
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeImageDisplay();
    }

    private void initializeImageDisplay(){

        ChangeListener<Number> listener = (observable, oldValue, newValue) -> {

            double width = imageDisplay.getWidth()-100;
            double height = imageDisplay.getHeight()-100;

            imageView.setFitWidth(width);
            imageView.setFitHeight(height);

        };

        imageDisplay.widthProperty().addListener(listener);
        imageDisplay.heightProperty().addListener(listener);
        
    }

    @FXML
    private void onMousePressed(MouseEvent event) {
        offsetX = event.getSceneX() - imageView.getTranslateX();
        initialPosImage = imageView.getTranslateX();
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {

        double limit= imageDisplay.getWidth() - imageView.getBoundsInParent().getWidth() - 60;
        double newTranslateX = event.getSceneX() - offsetX;

        if (newTranslateX > 0) {
            newTranslateX = Math.min(limit, newTranslateX);
        } else {
            newTranslateX = Math.max(-limit, newTranslateX);
        }

        imageView.setTranslateX(newTranslateX);
    }

    @FXML
    private void onMouseReleased(MouseEvent event) {
        double limit= imageDisplay.getWidth() - imageView.getBoundsInParent().getWidth()-100;

        if (imageView.getTranslateX() > -limit && imageView.getTranslateX() > initialPosImage) {
            setLeftImage();
        } else if (imageView.getTranslateX() < limit && imageView.getTranslateX() < initialPosImage) {
            setRightImage();
        }
    }

    private void printImage(ImageInterface imageInterface){
        String path = imageInterface.getAbsolutePath();
        Image image = new Image(path);
        imageView.setImage(image);
    }

    private void reprintImage(ImageInterface imageInterface){

        printImage(imageInterface);
        stage.setTitle(actualImage.name());
        imageView.setTranslateX(0);
    }

    @FXML
    private void setLeftImage() {
            if (actualImage == null) return;
            this.actualImage = actualImage.prev();
            reprintImage(actualImage);
    }

    @FXML
    private void setRightImage() {
            if (actualImage == null) return;
            this.actualImage = actualImage.next();
            reprintImage(actualImage);
    }


    public void setStage(Stage stage) {
        this.stage=stage;
    }

    @FXML
    private void chooseDirectory(){
        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Select directory");

        File selectedDir = directoryChooser.showDialog(stage);

        if (selectedDir == null) return;

        ImageLoader imageLoader = new JavaFXImageLoader(selectedDir.getAbsolutePath());
        actualImage = imageLoader.load();
        stage.setTitle(actualImage.name());
        printImage(actualImage);
    }
}