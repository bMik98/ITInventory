package ru.regionsgroup.inventory.service.audit;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public interface AuditContent<T> {
    T decode(final String content);

    List<T> decodeArray(final String content);

    List<T> decodeArrays(final List<String> contents);
}
