package ru.regionsgroup.inventory.dao.impl;

import ru.regionsgroup.inventory.dao.UserDao;
import ru.regionsgroup.inventory.model.User;

/**
 * @author Mbedritskiy
 */
public class HibernateUserDao extends HibernateGenericDao<User, String> implements UserDao {

    public HibernateUserDao() {
        super(User.class);
    }
}
