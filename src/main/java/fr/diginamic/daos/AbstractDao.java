package fr.diginamic.daos;

import fr.diginamic.database.Connection;
import fr.diginamic.interfaces.GenericDao;
import jakarta.persistence.EntityManager;

import java.util.Set;

public abstract class AbstractDao<T> implements GenericDao<T> {
    protected final EntityManager em;

    public AbstractDao() {
        this.em = Connection.getEntityManager();
    }

    abstract public Set<T> findAll();

    abstract public T findByPk(int id);

    abstract public void save(T entity);
}
