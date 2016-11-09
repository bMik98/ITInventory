package ru.regionsgroup.inventory.service.audit.directory;

import ru.regionsgroup.inventory.service.audit.AuditDirectory;

import java.nio.charset.Charset;
import java.nio.file.Path;

/**
 * @author Mbedritskiy
 */
public class PrinterConnectionAuditDirectory extends JsonAuditDirectory implements AuditDirectory {
    public PrinterConnectionAuditDirectory(Path directoryPath, String filter) {
        super(directoryPath, filter);
        setCharset(Charset.forName("UTF-16LE"));
    }
}
