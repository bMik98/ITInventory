package ru.regionsgroup.inventory.service.audit;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public interface AuditConverter<T> {
    List<T> convert(final List<String> content);
}
