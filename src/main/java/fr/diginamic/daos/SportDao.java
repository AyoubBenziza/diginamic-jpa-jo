package fr.diginamic.daos;

import fr.diginamic.entities.Sport;

import java.util.HashSet;
import java.util.Set;

/**
 * DAO class to manage the Sport entity
 *
 * @see Sport
 * @see AbstractDao
 *
 * @author AyoubBenziza
 */
public class SportDao extends AbstractDao<Sport> {

    /**
     * Set of Sport objects
     *
     * @see Sport
     */
    private final Set<Sport> sports;

    /**
     * Constructor
     * Initializes the sports attribute
     */
    public SportDao() {
        sports = findAll();
    }

    /**
     * Returns the set of Sport objects
     *
     * @return Set of Sport objects
     * @see Sport
     */
    @Override
    public Sport findByPk(int id) {
        return sports.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the set of Sport objects
     *
     * @return Set of Sport objects
     * @see Sport
     */
    @Override
    public Set<Sport> findAll() {
        return new HashSet<>(em.createQuery("SELECT s FROM Sport s", Sport.class).getResultList());
    }

    /**
     * Saves the specified Sport object
     *
     * @param sport the Sport object to save
     * @see Sport
     */
    @Override
    public void save(Sport sport) {
        sports.add(sport);
        em.persist(sport);
    }
}
