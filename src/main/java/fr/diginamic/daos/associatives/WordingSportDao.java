package fr.diginamic.daos.associatives;

import fr.diginamic.entities.associatives.WordingSport;
import jakarta.persistence.EntityManager;

public class WordingSportDao {
    private final EntityManager em;

    public WordingSportDao(EntityManager em) {
        this.em = em;
    }

    public boolean exists(String sportName, String languageName) {
        Long count = em.createQuery("SELECT COUNT(w) FROM WordingSport w WHERE w.name = :name AND w.langue.name = :langueName", Long.class)
                .setParameter("name", sportName)
                .setParameter("langueName", languageName)
                .getSingleResult();
        return count > 0;
    }

    public void save(WordingSport wordingSport) {
        em.persist(wordingSport);
    }
}
