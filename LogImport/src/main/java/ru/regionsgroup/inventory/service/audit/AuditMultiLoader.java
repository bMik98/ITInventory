package ru.regionsgroup.inventory.service.audit;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public interface AuditMultiLoader {
    void setLoaders(List<AuditImport> loaders);

    void run();
}
