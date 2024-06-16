package fr.diginamic.daos;

import fr.diginamic.entities.Epreuve;

import java.util.HashSet;
import java.util.Set;

/**
 * DAO class to manage the Epreuve entity
 *
 * @see Epreuve
 * @see AbstractDao
 *
 * @author AyoubBenziza
 */
public class EpreuveDao extends AbstractDao<Epreuve>{
    /**
     * Set of Epreuve objects
     *
     * @see Epreuve
     */
    private final Set<Epreuve> epreuves;

    /**
     * Constructor
     * Initializes the epreuves attribute
     */
    public EpreuveDao() {
        epreuves = findAll();
    }

    /**
     * Returns the set of Epreuve objects
     *
     * @return Set of Epreuve objects
     * @see Epreuve
     */
    @Override
    public Set<Epreuve> findAll() {
        return new HashSet<>(em.createQuery("SELECT e FROM Epreuve e", Epreuve.class).getResultList());
    }

    /**
     * Returns the Epreuve object with the specified id
     *
     * @param id the id of the Epreuve object
     * @return the Epreuve object with the specified id
     * @see Epreuve
     */
    @Override
    public Epreuve findByPk(int id) {
        return epreuves.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Saves the Epreuve object in the database
     *
     * @param epreuve the Epreuve object to save
     * @see Epreuve
     */
    @Override
    public void save(Epreuve epreuve) {
        epreuves.add(epreuve);
        em.persist(epreuve);
    }
}
