package ru.regionsgroup.inventory.service.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class FileUtil {
    public static boolean fileExists(final String fileSpec) {
        Path path = Paths.get(fileSpec);
        return Files.exists(path) && Files.isRegularFile(path);
    }

    public static boolean fileExists(final String dir, final String fileName) {
        return directoryExists(dir) && fileExists(String.format("%s\\%s", dir, fileName));
    }

    public static boolean directoryExists(final String dir) {
        Path path = Paths.get(dir);
        return Files.exists(path) && Files.isDirectory(path);
    }
}
