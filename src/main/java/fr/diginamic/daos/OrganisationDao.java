package fr.diginamic.daos;

import fr.diginamic.entities.Organisation;

import java.util.HashSet;
import java.util.Set;

public class OrganisationDao extends AbstractDao {
    private final Set<Organisation> organisations = new HashSet<>();

    public OrganisationDao() {
        this.organisations.addAll(em.createQuery("SELECT o FROM Organisation o", Organisation.class).getResultList());
    }

    public Organisation findByCioCode(String cioCode) {
        return organisations.stream()
                .filter(o -> o.getCioCode().equals(cioCode))
                .findFirst()
                .orElse(null);
    }

    public boolean exists(String organisationName, String languageName) {
        Long count = em.createQuery("SELECT COUNT(w) FROM WordingOrganisation w WHERE w.name = :name AND w.langue.name = :langueName", Long.class)
                .setParameter("name", organisationName)
                .setParameter("langueName", languageName)
                .getSingleResult();
        return count > 0;
    }

    public void save(Organisation organisation) {
        organisations.add(organisation);
        em.persist(organisation);
    }
}
