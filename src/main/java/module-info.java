module software.ulpgc.imageviewer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens software.ulpgc.imageviewer to javafx.fxml;
    exports software.ulpgc.imageviewer;
    exports software.ulpgc.imageviewer.model;
    opens software.ulpgc.imageviewer.model to javafx.fxml;
    exports software.ulpgc.imageviewer.view;
    opens software.ulpgc.imageviewer.view to javafx.fxml;
    exports software.ulpgc.imageviewer.controller;
    opens software.ulpgc.imageviewer.controller to javafx.fxml;
    exports software.ulpgc.imageviewer.presenter;
    opens software.ulpgc.imageviewer.presenter to javafx.fxml;
}