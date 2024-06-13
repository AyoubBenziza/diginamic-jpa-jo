package fr.diginamic.daos.associatives;

import fr.diginamic.entities.associatives.WordingEpreuve;
import jakarta.persistence.EntityManager;

public class WordingEpreuveDao {

    private final EntityManager em;

    public WordingEpreuveDao(EntityManager em) {
        this.em = em;
    }

    public boolean exists(String epreuveName, String languageName) {
        Long count = em.createQuery("SELECT COUNT(w) FROM WordingSport w WHERE w.name = :name AND w.langue.name = :langueName", Long.class)
                .setParameter("name", epreuveName)
                .setParameter("langueName", languageName)
                .getSingleResult();
        return count > 0;
    }

    public void save(WordingEpreuve wordingEpreuve) {
        em.persist(wordingEpreuve);
    }
}