package fr.diginamic.daos;

import fr.diginamic.entities.Sport;
import jakarta.persistence.EntityManager;

public class SportDao {
    private final EntityManager em;

    public SportDao(EntityManager em) {
        this.em = em;
    }

    public void save(Sport sport) {
        em.persist(sport);
    }
}
