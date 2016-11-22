package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.AuditSource;
import ru.regionsgroup.inventory.dao.GenericDao;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public class AuditImportImpl implements AuditImport {
    private GenericDao dao;
    private AuditConverter converter;
    private AuditSource location;

    @Override
    public int importToDatabase() {
        List<String> content = location.importContent();
        List entries = converter.decodeArrays(content);
        dao.saveOrUpdate(entries);
        return entries.size();
    }

    public GenericDao getDao() {
        return dao;
    }

    public void setDao(GenericDao dao) {
        this.dao = dao;
    }

    public AuditConverter getConverter() {
        return converter;
    }

    public void setConverter(AuditConverter converter) {
        this.converter = converter;
    }

    public AuditSource getLocation() {
        return location;
    }

    public void setLocation(AuditSource location) {
        this.location = location;
    }
}
