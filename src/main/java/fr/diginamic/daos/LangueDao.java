package fr.diginamic.daos;

import fr.diginamic.entities.Langue;

import java.util.HashSet;
import java.util.Set;

/**
 * DAO class to manage the Langue entity
 *
 * @see Langue
 * @see AbstractDao
 *
 * @author AyoubBenziza
 */
public class LangueDao extends AbstractDao<Langue> {
    /**
     * Set of Langue objects
     *
     * @see Langue
     */
    private final Set<Langue> langues;

    /**
     * Constructor
     * Initializes the langues attribute
     */
    public LangueDao() {
        langues = findAll();
    }

    /**
     * Returns the set of Langue objects
     *
     * @return Set of Langue objects
     * @see Langue
     */
    @Override
    public Set<Langue> findAll() {
        return new HashSet<>(em.createQuery("SELECT l FROM Langue l", Langue.class).getResultList());
    }

    /**
     * Returns the Langue object with the specified id
     *
     * @param id the id of the Langue object
     * @return the Langue object with the specified id
     * @see Langue
     */
    @Override
    public Langue findByPk(int id) {
        return langues.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the Langue object with the specified name
     *
     * @param name the name of the Langue object
     * @return the Langue object with the specified name
     * @see Langue
     */
    public Langue findByName(String name) {
        return langues.stream()
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Saves the Langue object in the database
     *
     * @param langue the Langue object to save
     * @see Langue
     */
    @Override
    public void save(Langue langue) {
        langues.add(langue);
        em.persist(langue);
    }
}
