package ru.regionsgroup.inventory.dao.impl;

import org.springframework.stereotype.Repository;
import ru.regionsgroup.inventory.dao.UserDao;
import ru.regionsgroup.inventory.model.User;

/**
 * @author Mbedritskiy
 */
@Repository
public class HibernateUserDao extends HibernateGenericDao<User, String> implements UserDao {
    public HibernateUserDao() {
        super(User.class);
    }
}
