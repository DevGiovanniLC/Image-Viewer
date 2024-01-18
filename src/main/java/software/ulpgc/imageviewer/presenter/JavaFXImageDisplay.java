package software.ulpgc.imageviewer.presenter;

import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import software.ulpgc.imageviewer.model.ImageInterface;
import software.ulpgc.imageviewer.view.JavaFXContextPopUp;
import software.ulpgc.imageviewer.view.JavaFXImageInfo;


public class JavaFXImageDisplay implements ImageDisplay {

    private final VBox imageFrame;
    private final ImageView imageView;
    private Stage stage;

    private ImageInterface actualImage;

    private double offsetX;
    private double initialPosImage;

    private String infoActualImage;
    private Popup popup;


    public JavaFXImageDisplay(VBox imageFrame, ImageView imageView) {
        this.imageFrame = imageFrame;
        this.imageView = imageView;

        configureImageFrame();
        configureImageView();
    }

    private void configureImageFrame(){
        ChangeListener<Number> listener = (observable, oldValue, newValue) -> {

            double width = imageFrame.getWidth()-100;
            double height = imageFrame.getHeight()-100;

            imageView.setFitWidth(width);
            imageView.setFitHeight(height);

        };

        imageFrame.widthProperty().addListener(listener);
        imageFrame.heightProperty().addListener(listener);
    }


    private void configureImageView() {
        this.imageView.setOnMousePressed(this::checkMousePosition);
        this.imageView.setOnMouseDragged(this::moveImage);
        this.imageView.setOnMouseReleased(this::selectNextImage);
    }

    private void checkMousePosition(MouseEvent event) {

        if (!event.getButton().equals(MouseButton.PRIMARY)) return;

        offsetX = event.getSceneX() - imageView.getTranslateX();
        initialPosImage = imageView.getTranslateX();

    }

    private void moveImage(MouseEvent event) {

        if (!event.getButton().equals(MouseButton.PRIMARY)) return;

        double limit = imageFrame.getWidth() - imageView.getBoundsInParent().getWidth() - 60;
        double newTranslateX = event.getSceneX() - offsetX;

        if (newTranslateX > 0) {
            newTranslateX = Math.min(limit, newTranslateX);
        }
        else {
            newTranslateX = Math.max(-limit, newTranslateX);
        }

        imageView.setTranslateX(newTranslateX);
    }

    private void selectNextImage(MouseEvent event) {

        if (!event.getButton().equals(MouseButton.PRIMARY)) return;

        double limit = imageFrame.getWidth() - imageView.getBoundsInParent().getWidth();

        if (imageView.getTranslateX() > -limit && imageView.getTranslateX() > initialPosImage) {
            setLeftImage();
        }
        else if (imageView.getTranslateX() < limit && imageView.getTranslateX() < initialPosImage) {
            setRightImage();
        }
    }

    public void setParentStage(Stage stage){
        this.stage = stage;
        configureContextInfo();

    }

    private void configureContextInfo(){
        popup = new Popup();

        imageView.setOnContextMenuRequested((ContextMenuEvent event) ->{
            if (!popup.isShowing()) showPopup(infoActualImage);
        });

        stage.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (!mouseEvent.getButton().equals(MouseButton.SECONDARY)) hideActionPopUP();
        });

        stage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) hideActionPopUP();
        });
    }

    private void showPopup(String text) {
        popup = new JavaFXContextPopUp(text);
        popup.setX(stage.getX()+20);
        popup.setY(stage.getY()+40);
        popup.show(stage);
    }

    private void hideActionPopUP(){
        if (popup.isShowing()) popup.hide();
    }
    


    @Override
    public void setLeftImage()
    {
        if (actualImage == null) return;
        this.actualImage = actualImage.prev();
        setImage(actualImage);
    }

    @Override
    public void setRightImage()
    {
        if (actualImage == null) return;
        this.actualImage = actualImage.next();
        setImage(actualImage);
    }


   @Override
    public void setImage(ImageInterface image)
   {
        this.actualImage = image;
        printImage(image);
        stage.setTitle(actualImage.name());
        imageView.setTranslateX(0);
   }

   private void printImage(ImageInterface imageInterface)
    {
        String path = imageInterface.getAbsolutePath();
        Image image = new Image(path);
        imageView.setImage(image);
        infoActualImage = JavaFXImageInfo.getInfo(image, imageInterface.getAbsolutePath());
    }

}
