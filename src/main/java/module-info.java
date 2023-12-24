module software.ulpgc.imageviewer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens software.ulpgc.imageviewer to javafx.fxml;
    exports software.ulpgc.imageviewer;
}