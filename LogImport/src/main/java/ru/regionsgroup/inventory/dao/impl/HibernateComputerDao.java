package ru.regionsgroup.inventory.dao.impl;

import org.springframework.stereotype.Repository;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.model.Computer;

/**
 * @author mbedritskiy
 */
@Repository
public class HibernateComputerDao extends HibernateGenericDao<Computer, String> implements ComputerDao {
    public HibernateComputerDao() {
        super(Computer.class);
    }
}
