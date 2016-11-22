package ru.regionsgroup.inventory.service.audit.source;

import ru.regionsgroup.inventory.service.audit.AuditSource;

import java.nio.charset.Charset;
import java.nio.file.Path;

/**
 * @author Mbedritskiy
 */
public class InstalledSoftwareAuditSourceImpl extends AuditSourceImpl implements AuditSource {
    public InstalledSoftwareAuditSourceImpl(Path directoryPath, String filter) {
        super(directoryPath, filter);
        setCharset(Charset.forName("UTF-16LE"));
    }
}
