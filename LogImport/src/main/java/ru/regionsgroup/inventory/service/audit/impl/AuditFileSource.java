package ru.regionsgroup.inventory.service.audit.impl;

import ru.regionsgroup.inventory.service.audit.AuditSource;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public class AuditFileSource implements AuditSource {
    private final Path directoryPath;
    private final String filter;
    private final Charset charset;

    public AuditFileSource(final Path directoryPath, final String filter, final Charset charset) {
        this.directoryPath = directoryPath;
        this.filter = filter;
        this.charset = charset;
    }

    public AuditFileSource(final Path directoryPath, final String filter) {
        this.directoryPath = directoryPath;
        this.filter = filter;
        this.charset = Charset.forName("UTF-16LE");
    }

    @Override
    public List<String> importContent() {
        return AuditFileUtil.readFiles(directoryPath, filter, charset);
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

    public String getFilter() {
        return filter;
    }

    public Charset getCharset() {
        return charset;
    }
}
