package ru.regionsgroup.inventory.service.audit.impl;

import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.AuditSource;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public abstract class AbstractAuditLoader<T> implements AuditLoader<T> {
    private final AuditConverter converter;
    private final AuditSource source;

    public AbstractAuditLoader(AuditConverter converter, AuditSource source) {
        this.converter = converter;
        this.source = source;
    }

    @Override
    public List<T> load() {
        List<String> content = source.importContent();
        return converter.convert(content);
    }
}
