package ru.regionsgroup.inventory.dao;

import ru.regionsgroup.inventory.model.Computer;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public interface GenericComputerDependedDao<T, K extends Serializable> extends GenericDao<T, K> {
    int deleteByComputer(Computer computer);
}
