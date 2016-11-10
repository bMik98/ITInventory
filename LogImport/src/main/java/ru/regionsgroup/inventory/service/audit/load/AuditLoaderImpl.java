package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.service.audit.AuditContent;
import ru.regionsgroup.inventory.service.audit.AuditLocation;
import ru.regionsgroup.inventory.dao.GenericDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public class AuditLoaderImpl implements AuditLoader {
    private GenericDao dao;
    private AuditContent contentConverter;
    private AuditLocation auditLocation;

    @Override
    public int importToDatabase() {
        List<String> content = auditLocation.importContent();
        List entries = contentConverter.decodeArrays(content);
        dao.saveOrUpdate(entries);
        return 0;
    }

    public GenericDao getDao() {
        return dao;
    }

    public void setDao(GenericDao dao) {
        this.dao = dao;
    }

    public AuditContent getContentConverter() {
        return contentConverter;
    }

    public void setContentConverter(AuditContent contentConverter) {
        this.contentConverter = contentConverter;
    }

    public AuditLocation getAuditLocation() {
        return auditLocation;
    }

    public void setAuditLocation(AuditLocation auditLocation) {
        this.auditLocation = auditLocation;
    }
}
