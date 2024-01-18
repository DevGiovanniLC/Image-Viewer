package software.ulpgc.imageviewer.view;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class JavaFXContextPopUp extends Popup {
    public JavaFXContextPopUp(String text) {
        this.getContent().add(getBackground(text));
    }

    private StackPane getBackground(String text){
        StackPane background = new StackPane();
        background.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 15px;");

        setImageInfo(text, background);

        background.setEffect(getShadowEffect());

        return background;
    }

    private void setImageInfo(String text, StackPane background){
        Text label = new Text(text);
        label.setFont(new Font("Arial", 20));
        background.getChildren().add(label);
    }

    private DropShadow getShadowEffect(){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.3));

        return dropShadow;
    }
}
