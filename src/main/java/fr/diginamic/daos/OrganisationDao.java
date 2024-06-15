package fr.diginamic.daos;

import fr.diginamic.entities.Organisation;

import java.util.HashSet;
import java.util.Set;

public class OrganisationDao extends AbstractDao {
    private final Set<Organisation> organisations;

    public OrganisationDao() {
        organisations = findAll();
    }

    public Set<Organisation> findAll() {
        return new HashSet<>(em.createQuery("SELECT o FROM Organisation o", Organisation.class).getResultList());
    }

    public Organisation findByCioCode(String cioCode) {
        return organisations.stream()
                .filter(o -> o.getCioCode().equals(cioCode))
                .findFirst()
                .orElse(null);
    }

    public void save(Organisation organisation) {
        organisations.add(organisation);
        em.persist(organisation);
    }
}
