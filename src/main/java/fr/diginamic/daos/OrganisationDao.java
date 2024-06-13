package fr.diginamic.daos;

import fr.diginamic.entities.Organisation;
import jakarta.persistence.EntityManager;

public class OrganisationDao {
    private final EntityManager em;

    public OrganisationDao(EntityManager em) {
        this.em = em;
    }

    public boolean exists(String organisationName, String languageName) {
        Long count = em.createQuery("SELECT COUNT(w) FROM WordingOrganisation w WHERE w.name = :name AND w.langue.name = :langueName", Long.class)
                .setParameter("name", organisationName)
                .setParameter("langueName", languageName)
                .getSingleResult();
        return count > 0;
    }

    public void save(Organisation organisation) {
        em.persist(organisation);
    }
}
