package fr.diginamic.daos.associatives;

import fr.diginamic.daos.AbstractDao;
import fr.diginamic.entities.associatives.WordingEpreuve;

import java.util.HashSet;
import java.util.Set;

/**
 * DAO Class to manage the WordingEpreuve entity
 *
 * @see WordingEpreuve
 * @see AbstractDao
 *
 * @author AyoubBenziza
 */
public class WordingEpreuveDao extends AbstractDao<WordingEpreuve> {
    /**
     * Set of WordingEpreuve objects
     *
     * @see WordingEpreuve
     */
    private final Set<WordingEpreuve> wordingEpreuves;

    /**
     * Constructor
     * Initializes the wordingEpreuves attribute
     */
    public WordingEpreuveDao() {
        wordingEpreuves = findAll();
    }

    /**
     * Returns the set of WordingEpreuve objects
     *
     * @return Set of WordingEpreuve objects
     * @see WordingEpreuve
     */
    @Override
    public Set<WordingEpreuve> findAll() {
        return new HashSet<>(em.createQuery("SELECT w FROM WordingEpreuve w", WordingEpreuve.class).getResultList());
    }

    /**
     * Returns the WordingEpreuve object with the specified id
     *
     * @param id the id of the WordingEpreuve object
     * @return the WordingEpreuve object with the specified id
     * @see WordingEpreuve
     */
    @Override
    public WordingEpreuve findByPk(int id) {
        return wordingEpreuves.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the WordingEpreuve object with the specified epreuve name
     *
     * @param epreuveName the name of the WordingEpreuve object
     * @return the WordingEpreuve object with the specified epreuve name
     * @see WordingEpreuve
     */
    public WordingEpreuve findByEpreuveName(String epreuveName) {
        return wordingEpreuves.stream()
                .filter(w -> w.getName().equals(epreuveName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the boolean value of the existence of the WordingEpreuve object with the specified epreuve name and language name
     *
     * @param epreuveName the name of the WordingEpreuve object
     * @param languageName the name of the Langue object
     * @return the boolean value of the existence of the WordingEpreuve object with the specified epreuve name and language name
     */
    public boolean exists(String epreuveName, String languageName) {
        return wordingEpreuves.stream()
                .anyMatch(w -> w.getName().equals(epreuveName) && w.getLangue().getName().equals(languageName));
    }

    /**
     * Saves the specified WordingEpreuve object
     *
     * @param wordingEpreuve the WordingEpreuve object to save
     * @see WordingEpreuve
     */
    @Override
    public void save(WordingEpreuve wordingEpreuve) {
        wordingEpreuves.add(wordingEpreuve);
        em.persist(wordingEpreuve);
    }
}