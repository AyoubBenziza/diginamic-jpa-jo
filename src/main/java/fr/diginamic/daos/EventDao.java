package fr.diginamic.daos;

import fr.diginamic.entities.Event;
import jakarta.persistence.EntityManager;

public class EventDao {
    private final EntityManager em;

    public EventDao(EntityManager em) {
        this.em = em;
    }

    public boolean exists(String eventName) {
        Long count = em.createQuery("SELECT COUNT(e) FROM Event e WHERE e.nom = :name", Long.class)
                .setParameter("name", eventName)
                .getSingleResult();
        return count > 0;
    }

    public void save(Event event) {
        em.persist(event);
    }
}
