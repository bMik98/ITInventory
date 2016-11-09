package ru.regionsgroup.inventory.service.audit.directory;

import ru.regionsgroup.inventory.service.audit.AuditDirectory;

import java.nio.charset.Charset;
import java.nio.file.Path;

/**
 * @author Mbedritskiy
 */
public class UserAuditDirectory extends JsonAuditDirectory implements AuditDirectory {
    public UserAuditDirectory(Path directoryPath, String filter) {
        super(directoryPath, filter);
        setCharset(Charset.forName("windows-1251"));
    }
}
