package software.ulpgc.imageviewer.view;

import software.ulpgc.imageviewer.model.ImageInterface;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;



public class JavaFXImageLoader implements ImageLoader {

    private final String dirPath;
    private final List<String> fileList;
    private final Set<String> extensions;


    public JavaFXImageLoader(String path) {

        File dir = new File(path);

        this.dirPath = dir.getPath();

        extensions =  Set.of(
                "bmp",
                "gif",
                "png",
                "jpg",
                "jpeg",
                "wbmp",
                "svg",
                "tiff"
        );

        this.fileList = folderToFileList(dir.listFiles());

    }


    @Override
    public ImageInterface load() {
        if(fileList.isEmpty())
            return defaultImage();
        else
            return imageAt(0);
    }

    private ImageInterface defaultImage(){
        return new ImageInterface() {
            @Override
            public String name() {
                return null;
            }

            @Override
            public ImageInterface prev() {
                return load();
            }

            @Override
            public ImageInterface next() {
                return load();
            }

            @Override
            public boolean equals(Object obj) {
                return obj instanceof ImageInterface && ((ImageInterface) obj).name().equals(this.name());
            }

            @Override
            public String getAbsolutePath() {
                return "/" + "default.png";
            }
        };
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
            public String getAbsolutePath(){
                return  "file:"+
                        dirPath
                                .replace("\\","/")
                        + "/" + name();
            }
        };
    }


    private List<String> folderToFileList(File[] files) {
        return  Arrays
                .stream(files)
                .map(File::getName)
                .filter(file -> this.extensions.contains(getExtension(file).toLowerCase()))
                .toList();
    }

    private String getExtension(String file){
        int lastDotIndex = file.lastIndexOf(".");
        return file.substring(lastDotIndex + 1);
    }



}
