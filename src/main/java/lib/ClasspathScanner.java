package lib;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lib.annotation.Controller;

public class ClasspathScanner {

    private static List<Class<?>> classes = new ArrayList<>();

    static {
        findClassesInBasePackage();
    }

    public List<Class<?>> getClassesAnnotatedWith(Class<? extends Annotation> annotation) {
        return classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

    private static void findClassesInBasePackage() {
        try {
            URL packageURL = Thread.currentThread().getContextClassLoader().getResource(".");
            File folder = Paths.get(packageURL.toURI()).toFile();
            classes = findClasses(folder.getPath(), folder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Class<?>> findClasses(String baseFolder, File folder) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        if (folder.toString().endsWith("/lib")) return classes;

        for (File f : Objects.requireNonNull(folder.listFiles())) {
            if (f.isDirectory()) {
                classes.addAll(findClasses(baseFolder, f));
                continue;
            }

            classes.add(Class.forName(
                    f.getPath()
                            .replace(baseFolder, "")
                            .replace(".class", "")
                            .substring(1)
                            .replace(File.separator, ".")
            ));
        }

        return classes;
    }


    public static void main(String[] args) {
        System.out.println("Running");

        System.out.println(
                new ClasspathScanner().getClassesAnnotatedWith(Controller.class)
        );
    }
}
