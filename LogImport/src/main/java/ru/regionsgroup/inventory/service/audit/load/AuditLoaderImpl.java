package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.service.audit.AuditContentConverter;
import ru.regionsgroup.inventory.service.audit.AuditDirectory;
import ru.regionsgroup.inventory.dao.GenericDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public class AuditLoaderImpl implements AuditLoader {
    private GenericDao dao;
    private AuditContentConverter contentConverter;
    private AuditDirectory auditDirectory;

    @Override
    public int importToDatabase() {
        List<String> content = auditDirectory.importContent();
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

    public AuditContentConverter getContentConverter() {
        return contentConverter;
    }

    public void setContentConverter(AuditContentConverter contentConverter) {
        this.contentConverter = contentConverter;
    }

    public AuditDirectory getAuditDirectory() {
        return auditDirectory;
    }

    public void setAuditDirectory(AuditDirectory auditDirectory) {
        this.auditDirectory = auditDirectory;
    }
}
