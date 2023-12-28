package software.ulpgc.imageviewer.model;

public interface ImageInterface {
    String name();

    ImageInterface prev();
    ImageInterface next();

    String getAbsolutePath();
}
