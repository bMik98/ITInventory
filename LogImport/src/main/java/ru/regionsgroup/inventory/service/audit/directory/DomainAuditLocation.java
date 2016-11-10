package ru.regionsgroup.inventory.service.audit.directory;

import ru.regionsgroup.inventory.service.audit.AuditLocation;

import java.nio.charset.Charset;
import java.nio.file.Path;

/**
 * @author Mbedritskiy
 */
public class DomainAuditLocation extends JsonAuditLocation implements AuditLocation {
    public DomainAuditLocation(Path directoryPath, String filter) {
        super(directoryPath, filter);
        setCharset(Charset.forName("windows-1251"));
    }
}
