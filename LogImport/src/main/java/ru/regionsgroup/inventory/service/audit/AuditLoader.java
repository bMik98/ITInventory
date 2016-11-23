package ru.regionsgroup.inventory.service.audit;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public interface AuditLoader<T> {
    List<T> load();
}
