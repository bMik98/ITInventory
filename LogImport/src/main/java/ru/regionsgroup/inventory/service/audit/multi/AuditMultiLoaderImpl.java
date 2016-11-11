package ru.regionsgroup.inventory.service.audit.multi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.AuditMultiLoader;

import java.util.List;

/**
 * @author Mbedritskiy
 */
@Component
public class AuditMultiLoaderImpl implements AuditMultiLoader {
    private List<AuditLoader> loaders;

    @Override
    @Autowired
    public void setLoaders(List<AuditLoader> loaders) {
        this.loaders = loaders;
    }

    @Override
    public void run() {
        loaders.forEach(AuditLoader::importToDatabase);
    }

}
