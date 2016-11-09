package ru.regionsgroup.inventory.service.audit;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public interface AuditDirectory {
    List<String> importContent();
}
