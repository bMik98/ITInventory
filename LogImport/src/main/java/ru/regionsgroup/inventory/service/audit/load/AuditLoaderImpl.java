package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.service.audit.AuditLocation;
import ru.regionsgroup.inventory.dao.GenericDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public class AuditLoaderImpl implements AuditLoader {
    private GenericDao dao;
    private AuditConverter converter;
    private AuditLocation location;

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

    public AuditLocation getLocation() {
        return location;
    }

    public void setLocation(AuditLocation location) {
        this.location = location;
    }
}
