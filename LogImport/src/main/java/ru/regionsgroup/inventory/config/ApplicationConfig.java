package ru.regionsgroup.inventory.config;

import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.regionsgroup.inventory.dao.impl.HibernateComputerDao;
import ru.regionsgroup.inventory.dao.impl.HibernateDomainDao;
import ru.regionsgroup.inventory.dao.impl.HibernatePrinterConnectionDao;
import ru.regionsgroup.inventory.dao.impl.HibernateUserDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.converter.ComputerAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.converter.DomainAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.converter.PrinterConnectionAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.converter.UserAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.directory.ComputerAuditDirectory;
import ru.regionsgroup.inventory.service.audit.directory.DomainAuditDirectory;
import ru.regionsgroup.inventory.service.audit.directory.PrinterConnectionAuditDirectory;
import ru.regionsgroup.inventory.service.audit.directory.UserAuditDirectory;
import ru.regionsgroup.inventory.service.audit.load.ComputerAuditLoader;
import ru.regionsgroup.inventory.service.audit.load.DomainAuditLoader;
import ru.regionsgroup.inventory.service.audit.load.PrinterConnectionAuditLoader;
import ru.regionsgroup.inventory.service.audit.load.UserAuditLoader;

import java.nio.file.Paths;

/**
 * @author Mbedritskiy
 */
@Configuration
@ComponentScan({"ru.regionsgroup.inventory.dao.impl"})
public class ApplicationConfig {
    private final String PRN_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Printers";
    private final String AD_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-AD";
    private final String COMP_LOG = "ad_computers.json";
    private final String USER_LOG = "ad_users.json";
    private final String DOMAIN_LOG = "ad_domains.json";
    private final String LOG_FILTER = "*.json";

    private HibernateUserDao userDao;
    private HibernateComputerDao computerDao;
    private HibernateDomainDao domainDao;
    private HibernatePrinterConnectionDao printerConnectionDao;

    @Bean
    UserAuditDirectory userAuditDirectory() {
        return new UserAuditDirectory(Paths.get(AD_ROOT), USER_LOG);
    }

    @Bean
    ComputerAuditDirectory computerAuditDirectory() {
        return new ComputerAuditDirectory(Paths.get(AD_ROOT), COMP_LOG);
    }

    @Bean
    DomainAuditDirectory domainAuditDirectory() {
        return new DomainAuditDirectory(Paths.get(AD_ROOT), DOMAIN_LOG);
    }

    @Bean
    PrinterConnectionAuditDirectory printerConnectionAuditDirectory() {
        return new PrinterConnectionAuditDirectory(Paths.get(PRN_ROOT), LOG_FILTER);
    }

    @Bean
    UserAuditContentConverter userAuditContentConverter() {
        return new UserAuditContentConverter();
    }

    @Bean
    ComputerAuditContentConverter computerAuditContentConverter() {
        return new ComputerAuditContentConverter();
    }

    @Bean
    DomainAuditContentConverter domainAuditContentConverter() {
        return new DomainAuditContentConverter();
    }

    @Bean
    PrinterConnectionAuditContentConverter printerConnectionAuditContentConverter() {
        return new PrinterConnectionAuditContentConverter();
    }

    @Bean
    UserAuditLoader userAuditLoader() {
        return new UserAuditLoader(userDao, userAuditContentConverter(), userAuditDirectory());
    }

    @Bean
    ComputerAuditLoader computerAuditLoader() {
        return new ComputerAuditLoader(computerDao, computerAuditContentConverter(), computerAuditDirectory());
    }

    @Bean
    DomainAuditLoader domainAuditLoader() {
        return new DomainAuditLoader(domainDao, domainAuditContentConverter(), domainAuditDirectory());
    }

    @Bean
    PrinterConnectionAuditLoader printerConnectionAuditLoader() {
        return new PrinterConnectionAuditLoader(
                printerConnectionDao, printerConnectionAuditContentConverter(), printerConnectionAuditDirectory());
    }

    @Autowired
    public void setUserDao(HibernateUserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setComputerDao(HibernateComputerDao computerDao) {
        this.computerDao = computerDao;
    }

    @Autowired
    public void setDomainDao(HibernateDomainDao domainDao) {
        this.domainDao = domainDao;
    }

    @Autowired
    public void setPrinterConnectionDao(HibernatePrinterConnectionDao printerConnectionDao) {
        this.printerConnectionDao = printerConnectionDao;
    }
}
