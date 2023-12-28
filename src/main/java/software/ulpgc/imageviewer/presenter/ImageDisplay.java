package software.ulpgc.imageviewer.presenter;

import software.ulpgc.imageviewer.model.ImageInterface;

public interface ImageDisplay {
    void setLeftImage();

    void setRightImage();

    void setActualImage(ImageInterface image);
}
