package fr.diginamic.daos;

import fr.diginamic.entities.Organisation;

import java.util.HashSet;
import java.util.Set;

/**
 * DAO class to manage the Organisation entity
 *
 * @see Organisation
 * @see AbstractDao
 *
 * @author AyoubBenziza
 */
public class OrganisationDao extends AbstractDao<Organisation> {
    /**
     * Set of Organisation objects
     *
     * @see Organisation
     */
    private final Set<Organisation> organisations;

    /**
     * Constructor
     * Initializes the organisations attribute
     */
    public OrganisationDao() {
        organisations = findAll();
    }

    /**
     * Returns the set of Organisation objects
     *
     * @return Set of Organisation objects
     * @see Organisation
     */
    @Override
    public Set<Organisation> findAll() {
        return new HashSet<>(em.createQuery("SELECT o FROM Organisation o", Organisation.class).getResultList());
    }

    /**
     * Returns the Organisation object with the specified id
     *
     * @param id the id of the Organisation object
     * @return the Organisation object with the specified id
     * @see Organisation
     */
    @Override
    public Organisation findByPk(int id) {
        return organisations.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the Organisation object with the specified CIO code
     *
     * @param cioCode the CIO code of the Organisation object
     * @return the Organisation object with the specified CIO code
     * @see Organisation
     */
    public Organisation findByCioCode(String cioCode) {
        return organisations.stream()
                .filter(o -> o.getCioCode().equals(cioCode))
                .findFirst()
                .orElse(null);
    }

    /**
     * Saves the Organisation object in the database
     *
     * @param organisation the Organisation object to save
     * @see Organisation
     */
    @Override
    public void save(Organisation organisation) {
        organisations.add(organisation);
        em.persist(organisation);
    }
}
