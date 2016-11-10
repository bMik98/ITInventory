package ru.regionsgroup.inventory.service.audit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public class AuditImport {
    private List<AuditLoader> loaders;

    public AuditImport() {
        loaders = new ArrayList<>();
        for(AuditLoader loader : loaders) {
            addLoader(loader);
        }
    }

    public void addLoader(AuditLoader loader) {
        loaders.add(loader);
    }

    public void run() {
        loaders.forEach(AuditLoader::importToDatabase);
    }
}
