package software.ulpgc.imageviewer;

import software.ulpgc.imageviewer.interfaces.ImageInterface;
import software.ulpgc.imageviewer.interfaces.ImageLoader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class JavaFXImageLoader implements ImageLoader {

    private final String path;
    private final List<String> fileList;

    public JavaFXImageLoader(String path) {


        File dir = new File(path);
        this.path = dir.getName();
        this.fileList = folderToList(dir.listFiles());
    }


    @Override
    public ImageInterface load() {
        return imageAt(0);
    }

    private ImageInterface imageAt(int i) {
        return new ImageInterface() {
            @Override
            public String name() {
                return fileList.get(i);
            }

            @Override
            public ImageInterface prev() {
                return imageAt((i + 1) % fileList.size());
            }

            @Override
            public ImageInterface next() {
                return imageAt(i > 0 ? i - 1 : fileList.size() - 1);
            }

            @Override
            public boolean equals(Object obj) {
                return obj instanceof ImageInterface && ((ImageInterface) obj).name().equals(this.name());
            }
            @Override
            public String getPath(){
                return  "/"+path+"/"+name();
            }
        };
    }


    private List<String> folderToList(File[] files) {
        return  Arrays
                .stream(files)
                .map(File::getName)
                .toList();
    }

}
