package fr.diginamic.daos.associatives;

import fr.diginamic.daos.AbstractDao;
import fr.diginamic.entities.associatives.WordingOrganisation;

import java.util.HashSet;
import java.util.Set;

/**
 * DAO class to manage the WordingOrganisation entity
 *
 * @see WordingOrganisation
 * @see AbstractDao
 *
 * @author AyoubBenziza
 */
public class WordingOrganisationDao extends AbstractDao<WordingOrganisation> {

    /**
     * Set of WordingOrganisation objects
     *
     * @see WordingOrganisation
     */
    private final Set<WordingOrganisation> wordingOrganisations;

    /**
     * Constructor
     * Initializes the wordingOrganisations attribute
     */
    public WordingOrganisationDao() {
        wordingOrganisations = findAll();
    }

    /**
     * Returns the set of WordingOrganisation objects
     *
     * @return Set of WordingOrganisation objects
     * @see WordingOrganisation
     */
    @Override
    public Set<WordingOrganisation> findAll() {
        return new HashSet<>(em.createQuery("SELECT w FROM WordingOrganisation w", WordingOrganisation.class).getResultList());
    }

    /**
     * Returns the WordingOrganisation object with the specified id
     *
     * @param id the id of the WordingOrganisation object
     * @return the WordingOrganisation object with the specified id
     * @see WordingOrganisation
     */
    @Override
    public WordingOrganisation findByPk(int id) {
        return wordingOrganisations.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the boolean value of the existence of a WordingOrganisation object with the specified organisation name and language name
     *
     * @param organisationName the name of the organisation
     * @param languageName the name of the language
     * @return the boolean value of the existence of a WordingOrganisation object with the specified organisation name and language name
     */
    public boolean exists(String organisationName, String languageName) {
        return wordingOrganisations.stream()
                .anyMatch(w -> w.getName().equals(organisationName) && w.getLangue().getName().equals(languageName));
    }

    /**
     * Saves the specified WordingOrganisation object
     *
     * @param wordingOrganisation the WordingOrganisation object to save
     * @see WordingOrganisation
     */
    @Override
    public void save(WordingOrganisation wordingOrganisation) {
        wordingOrganisations.add(wordingOrganisation);
        em.persist(wordingOrganisation);
    }
}
