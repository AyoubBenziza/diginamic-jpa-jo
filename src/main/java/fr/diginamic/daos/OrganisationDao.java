package fr.diginamic.daos;

import fr.diginamic.entities.Organisation;

import java.util.HashSet;
import java.util.Set;

public class OrganisationDao extends AbstractDao<Organisation> {
    private final Set<Organisation> organisations;

    public OrganisationDao() {
        organisations = findAll();
    }

    @Override
    public Set<Organisation> findAll() {
        return new HashSet<>(em.createQuery("SELECT o FROM Organisation o", Organisation.class).getResultList());
    }

    @Override
    public Organisation findByPk(int id) {
        return organisations.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Organisation findByCioCode(String cioCode) {
        return organisations.stream()
                .filter(o -> o.getCioCode().equals(cioCode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Organisation organisation) {
        organisations.add(organisation);
        em.persist(organisation);
    }
}
