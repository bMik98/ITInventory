package ru.regionsgroup.inventory.service.audit.converter;

import com.sun.java.browser.plugin2.DOM;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.model.Domain;

/**
 * @author Mbedritskiy
 */
@Component
public class DomainAuditConverter extends JsonAuditConverter<Domain> {
    public DomainAuditConverter() {
        super(Domain.class);
    }
}
