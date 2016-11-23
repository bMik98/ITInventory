package ru.regionsgroup.inventory.service.audit.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public class AuditFileUtil {
    private static final int MIN_FILE_SIZE = 20;
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-16LE");

    public static List<String> readFiles(final Path dir, final String filter, final Charset charset) {
        List<String> result = new ArrayList<>();
        if(!directoryExists(dir)) {
            System.out.println("Directory does not exists: " + dir.toString());
            return result;
        }
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir, filter);
            for (Path path : directoryStream) {
                String content = readFile(path, charset);
                if (!content.isEmpty()) {
                    result.add(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> readFiles(final Path dir, final String filter) {
        return readFiles(dir, filter, DEFAULT_CHARSET);
    }

    public static String readFile(final Path path, final Charset charset) {
        System.out.println(path);
        String result = "";
        try {
            if (isFileValid(path)) {
                result = new String(Files.readAllBytes(path), charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String readFile(final Path path) {
        return readFile(path, DEFAULT_CHARSET);
    }

    public static boolean fileExists(final Path path) {
        return Files.exists(path) && Files.isRegularFile(path);
    }

    public static boolean directoryExists(final Path path) {
        return Files.exists(path) && Files.isDirectory(path);
    }

    private static boolean isFileValid(final Path path) {
        if (!fileExists(path)) {
            return false;
        }
        try {
            if (Files.size(path) > MIN_FILE_SIZE) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
