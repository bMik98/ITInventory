package ru.regionsgroup.inventory.service.audit.location;

import ru.regionsgroup.inventory.service.audit.AuditLocation;

import java.nio.charset.Charset;
import java.nio.file.Path;

/**
 * @author Mbedritskiy
 */
public class InstalledSoftwareAuditLocation extends JsonAuditLocation implements AuditLocation {
    public InstalledSoftwareAuditLocation(Path directoryPath, String filter) {
        super(directoryPath, filter);
        setCharset(Charset.forName("UTF-16LE"));
    }
}
