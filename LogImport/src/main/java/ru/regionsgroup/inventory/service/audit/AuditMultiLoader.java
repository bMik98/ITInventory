package ru.regionsgroup.inventory.service.audit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public interface AuditMultiLoader {
    void setLoaders(List<AuditLoader> loaders);

    void run();
}
