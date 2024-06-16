package fr.diginamic.interfaces;

import java.util.Set;

public interface GenericDao<T> {
    Set<T> findAll();
    T findByPk(int id);
    void save(T entity);
}
