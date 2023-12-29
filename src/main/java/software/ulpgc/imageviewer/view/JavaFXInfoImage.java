package software.ulpgc.imageviewer.view;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaFXInfoImage{

    private JavaFXInfoImage() {

    }

    public static String getInfo(Image image, String path)  {
        return String.format(
                """
                Width: %d px\s
                Height: %d px\s
                Size: %d KB
                """,
                (long)image.getWidth(), (long)image.getHeight(), imageSizeKB(path)
        );
    }

    public static long imageSizeKB(String absolutePath) {
        Path path = Paths.get(absolutePath.substring(5));
        try {
            return Files.size(path) / 1024;
        } catch (IOException e) {
            return 0;
        }
    }
}
