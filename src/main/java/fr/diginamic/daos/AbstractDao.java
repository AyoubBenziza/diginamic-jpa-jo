package fr.diginamic.daos;

import fr.diginamic.database.Connection;
import fr.diginamic.interfaces.GenericDao;
import jakarta.persistence.EntityManager;

import java.util.Set;

/**
 * Parent class for the DAO classes
 * Abstract class that implements the GenericDao interface
 * It provides the EntityManager object to the subclasses
 *
 * @param <T> the entity type
 * @see GenericDao
 *
 * @author AyoubBenziza
 */
public abstract class AbstractDao<T> implements GenericDao<T> {
    /**
     * EntityManager object
     */
    protected final EntityManager em;

    /**
     * Constructor
     * Initializes the EntityManager object
     */
    public AbstractDao() {
        this.em = Connection.getEntityManager();
    }

    /**
     * {@inheritDoc}
     */
    abstract public Set<T> findAll();

    /**
     * {@inheritDoc}
     */
    abstract public T findByPk(int id);

    /**
     * {@inheritDoc}
     */
    abstract public void save(T entity);
}
