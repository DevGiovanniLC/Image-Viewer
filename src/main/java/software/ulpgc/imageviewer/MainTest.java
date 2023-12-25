package software.ulpgc.imageviewer;

import javafx.scene.image.Image;

import java.util.Objects;

public class MainTest {
    public static void main(String[] args) {
        JavaFXImageLoader images = new JavaFXImageLoader("src/main/resources/images");
        System.out.println(images.load().getPath());
        Image image = new Image("/images/non_fire.14.png");
    }
}
