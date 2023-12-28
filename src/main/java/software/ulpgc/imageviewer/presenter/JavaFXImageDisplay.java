package software.ulpgc.imageviewer.presenter;

import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import software.ulpgc.imageviewer.model.ImageInterface;

public class JavaFXImageDisplay implements ImageDisplay {

    private final VBox imageFrame;
    private final ImageView imageView;
    private Stage stage;

    private ImageInterface actualImage;
    private double offsetX;
    private double initialPosImage;


    public JavaFXImageDisplay(VBox imageFrame, ImageView imageView) {
        this.imageFrame = imageFrame;
        this.imageView = imageView;

        initializeImageFrame();
        initializeImageView();
    }


    private void initializeImageView() {
        this.imageView.setOnMousePressed(this::checkMousePosition);
        this.imageView.setOnMouseDragged(this::moveImage);
        this.imageView.setOnMouseReleased(this::setImage);
    }

    private void initializeImageFrame(){
        ChangeListener<Number> listener = (observable, oldValue, newValue) -> {

            double width = imageFrame.getWidth()-100;
            double height = imageFrame.getHeight()-100;

            imageView.setFitWidth(width);
            imageView.setFitHeight(height);

        };

        imageFrame.widthProperty().addListener(listener);
        imageFrame.heightProperty().addListener(listener);

    }

    public void setStage(Stage stage){
        this.stage = stage;
    }



    private void checkMousePosition(MouseEvent event) {
        offsetX = event.getSceneX() - imageView.getTranslateX();
        initialPosImage = imageView.getTranslateX();
    }

    private void moveImage(MouseEvent event) {

        double limit= imageFrame.getWidth() - imageView.getBoundsInParent().getWidth() - 60;
        double newTranslateX = event.getSceneX() - offsetX;

        if (newTranslateX > 0) {
            newTranslateX = Math.min(limit, newTranslateX);
        } else {
            newTranslateX = Math.max(-limit, newTranslateX);
        }

        imageView.setTranslateX(newTranslateX);
    }

    private void setImage(MouseEvent event) {
        double limit= imageFrame.getWidth() - imageView.getBoundsInParent().getWidth()-100;

        if (imageView.getTranslateX() > -limit && imageView.getTranslateX() > initialPosImage) {
            setLeftImage();
        } else if (imageView.getTranslateX() < limit && imageView.getTranslateX() < initialPosImage) {
            setRightImage();
        }
    }

    @Override
    public void setLeftImage() {
        if (actualImage == null) return;
        this.actualImage = actualImage.prev();
        setActualImage(actualImage);
    }

    @Override
    public void setRightImage() {
        if (actualImage == null) return;
        this.actualImage = actualImage.next();
        setActualImage(actualImage);
    }

    private void printImage(ImageInterface imageInterface){
        String path = imageInterface.getAbsolutePath();
        Image image = new Image(path);
        imageView.setImage(image);
    }

   @Override
    public void setActualImage(ImageInterface image){
        this.actualImage = image;
        printImage(image);
        stage.setTitle(actualImage.name());
        imageView.setTranslateX(0);
    }
}
