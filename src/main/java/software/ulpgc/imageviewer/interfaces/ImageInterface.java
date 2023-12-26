package software.ulpgc.imageviewer.interfaces;

public interface ImageInterface {
    String name();

    ImageInterface prev();
    ImageInterface next();

    String getAbsolutePath();
}
