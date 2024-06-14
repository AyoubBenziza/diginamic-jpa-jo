package fr.diginamic.daos.associatives;

import fr.diginamic.entities.associatives.WordingOrganisation;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class WordingOrganisationDao {
    private final EntityManager em;

    private final Set<WordingOrganisation> wordingOrganisations = new HashSet<>();

    public WordingOrganisationDao(EntityManager em) {
        this.em = em;
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
