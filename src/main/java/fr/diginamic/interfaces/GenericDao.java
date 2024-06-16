package fr.diginamic.interfaces;

import java.util.Set;
import fr.diginamic.daos.AbstractDao;

/**
 * GenericDao interface
 * Template for the DAO classes
 * It provides the methods that the subclasses must implement
 *
 * @param <T> the entity type
 * @see AbstractDao
 *
 * @author AyoubBenziza
 */
public interface GenericDao<T> {
    /**
     * Finds all entities of type T
     *
     * @return a set of entities of type T
     */
    Set<T> findAll();

    /**
     * Finds an entity of type T by its primary key
     *
     * @param id the primary key of the entity
     * @return the entity of type T
     */
    T findByPk(int id);

    /**
     * Saves an entity of type T
     *
     * @param entity the entity to save
     */
    void save(T entity);
}
