package ru.regionsgroup.inventory.service.audit.directory;

import ru.regionsgroup.inventory.service.audit.AuditLocation;

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
public class JsonAuditLocation implements AuditLocation {
    private static final int MIN_FILE_SIZE = 20;

    private Path directoryPath;
    private String filter;
    private Charset charset = Charset.forName("UTF-16LE");

    public JsonAuditLocation() {
    }

    public JsonAuditLocation(Path directoryPath, String filter) {
        this.directoryPath = directoryPath;
        this.filter = filter;
    }

    @Override
    public List<String> importContent() {
        List<String> result = new ArrayList<>();
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath, filter);
            for (Path path : directoryStream) {
                String content = readFile(path);
                if (!content.isEmpty()) {
                    result.add(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String readFile(final Path path) {
        String result = "";
        try {
            if (Files.size(path) > MIN_FILE_SIZE) {
                result = new String(Files.readAllBytes(path), charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(Path directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }
}
