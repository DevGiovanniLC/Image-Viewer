package software.ulpgc.imageviewer.javaFX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import software.ulpgc.imageviewer.interfaces.ImageInterface;
import software.ulpgc.imageviewer.interfaces.ImageLoader;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private VBox vBox;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ImageLoader imageLoader = new JavaFXImageLoader("images");
        //System.out.println(imageLoader.load().getPath());
        //initializeImage(imageLoader.load());
    }

    private void initializeImage(ImageInterface imageInterface){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageInterface.getPath())));
        imageView.setImage(image);

        imageView.fitWidthProperty().bind(vBox.widthProperty());
        imageView.fitHeightProperty().bind(vBox.heightProperty());
        imageView.setPreserveRatio(true);
    }
}