package software.ulpgc.imageviewer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainTesting {
    public static void main(String[] args) {
        Set<String> extensions =  Set.of(
                "*.bmp",
                "*.gif",
                "*.png",
                "*.jpg",
                "*.jpeg",
                "*.wbmp",
                "*.svg",
                "*.tiff"
        );

        List<String> strings = List.of("hola.png", "adios.jpeg", "claro.jpg", "este_no.txt");

        List<String> stringsChecked =checkExtensionsFiles(strings,extensions);

         stringsChecked.forEach(System.out::println);
        System.out.println(getExtension(strings.getFirst()).toLowerCase());
    }

    public  static List<String> checkExtensionsFiles(List<String> files, Set<String> set) {
        return files.stream()
                .filter(file -> set.contains(getExtension(file).toLowerCase()))
                .collect(Collectors.toList());
    }

    public static String getExtension(String file){
        int lastDotIndex = file.lastIndexOf(".");
        return file.substring(lastDotIndex + 1);
    }
}
