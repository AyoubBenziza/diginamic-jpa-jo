package fr.diginamic.daos.associatives;

import fr.diginamic.daos.AbstractDao;
import fr.diginamic.entities.associatives.WordingOrganisation;

import java.util.HashSet;
import java.util.Set;

public class WordingOrganisationDao extends AbstractDao {

    private final Set<WordingOrganisation> wordingOrganisations = new HashSet<>();

    public WordingOrganisationDao() {
        this.wordingOrganisations.addAll(em.createQuery("SELECT wo FROM WordingOrganisation wo", WordingOrganisation.class).getResultList());
    }

    public boolean exists(String organisationName, String languageName) {
        return wordingOrganisations.stream()
                .anyMatch(w -> w.getName().equals(organisationName) && w.getLangue().getName().equals(languageName));
    }

    public void save(WordingOrganisation wordingOrganisation) {
        wordingOrganisations.add(wordingOrganisation);
        em.persist(wordingOrganisation);
    }
}
