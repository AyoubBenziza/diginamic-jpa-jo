package fr.diginamic.daos.associatives;

import fr.diginamic.entities.associatives.WordingOrganisation;
import jakarta.persistence.EntityManager;

public class WordingOrganisationDao {
    private final EntityManager em;

    public WordingOrganisationDao(EntityManager em) {
        this.em = em;
    }

    public boolean exists(String organisationName, String languageName) {
        Long count = em.createQuery("SELECT COUNT(w) FROM WordingOrganisation w WHERE w.name = :name AND w.langue.name = :langueName", Long.class)
                .setParameter("name", organisationName)
                .setParameter("langueName", languageName)
                .getSingleResult();
        return count > 0;
    }

    public void save(WordingOrganisation wordingOrganisation) {
        em.persist(wordingOrganisation);
    }
}
