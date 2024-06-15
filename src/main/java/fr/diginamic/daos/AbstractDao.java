package fr.diginamic.daos;

import fr.diginamic.database.Connection;
import jakarta.persistence.EntityManager;

public abstract class AbstractDao {
    protected final EntityManager em;

    public AbstractDao() {
        this.em = Connection.getEntityManager();
    }
}
