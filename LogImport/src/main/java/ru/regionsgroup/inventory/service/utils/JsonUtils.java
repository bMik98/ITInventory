package ru.regionsgroup.inventory.service.utils;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String JSON_LOG_FILE_EXT = "*.json";
    private static final int VALID_FILE_SIZE = 20;

    public static <T> List<T> decodeAllFiles(final String dir, String charsetName, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        for (Path path : getDirectoryStream(dir, JSON_LOG_FILE_EXT)) {
            result.addAll(decodeFile(path, charsetName, clazz));
        }
        return result;
    }

    public static <T> List<T> decodeFile(Path path, String charsetName, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        try {
            if (Files.size(path) > VALID_FILE_SIZE) {
                System.out.println(path.getFileName());
                String content = new String(Files.readAllBytes(path), Charset.forName(charsetName));
                result.addAll(decodeArray(content, clazz));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> List<T> decodeArray(String content, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        try {
            JsonArray jsonArray = new JsonArray(content);
            for (int i = 0; i < jsonArray.size(); i++) {
                result.add(Json.decodeValue(jsonArray.getValue(i).toString(), clazz));
            }
        } catch (DecodeException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static DirectoryStream<Path> getDirectoryStream(final String dir, final String filter) {
        try {
            return Files.newDirectoryStream(Paths.get(dir), filter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
