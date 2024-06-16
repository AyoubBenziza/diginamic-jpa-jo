package fr.diginamic.daos.associatives;

import fr.diginamic.daos.AbstractDao;
import fr.diginamic.entities.associatives.WordingSport;

import java.util.HashSet;
import java.util.Set;

/**
 * DAO class to manage the WordingSport entity
 *
 * @see WordingSport
 * @see AbstractDao
 *
 * @author AyoubBenziza
 */
public class WordingSportDao extends AbstractDao<WordingSport> {

    /**
     * Set of WordingSport objects
     *
     * @see WordingSport
     */
    private final Set<WordingSport> wordingSports;

    /**
     * Constructor
     * Initializes the wordingSports attribute
     */
    public WordingSportDao() {
        wordingSports = findAll();
    }

    /**
     * Returns the set of WordingSport objects
     *
     * @return Set of WordingSport objects
     * @see WordingSport
     */
    @Override
    public Set<WordingSport> findAll() {
        return new HashSet<>(em.createQuery("SELECT ws FROM WordingSport ws", WordingSport.class).getResultList());
    }

    /**
     * Returns the WordingSport object with the specified id
     *
     * @param id the id of the WordingSport object
     * @return the WordingSport object with the specified id
     * @see WordingSport
     */
    @Override
    public WordingSport findByPk(int id) {
        return wordingSports.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the WordingSport object with the specified sport name
     *
     * @param sportName the name of the sport
     * @return the WordingSport object with the specified sport name
     * @see WordingSport
     */
    public WordingSport findBySportName(String sportName) {
        System.out.println(wordingSports);
        return wordingSports.stream()
                .filter(w -> w.getName().equals(sportName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the boolean value indicating whether the WordingSport object with the specified sport name and language name exists
     *
     * @param sportName the name of the sport
     * @param languageName the name of the language
     * @return the boolean value indicating whether the WordingSport object with the specified sport name and language name exists
     */
    public boolean exists(String sportName, String languageName) {
        return wordingSports.stream()
                .anyMatch(w -> w.getName().equals(sportName) && w.getLangue().getName().equals(languageName));
    }

    /**
     * Saves the WordingSport object in the database
     *
     * @param wordingSport the WordingSport object to save
     * @see WordingSport
     */
    @Override
    public void save(WordingSport wordingSport) {
        wordingSports.add(wordingSport);
        em.persist(wordingSport);
    }
}
