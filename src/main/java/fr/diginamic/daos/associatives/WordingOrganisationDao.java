package fr.diginamic.daos.associatives;

import fr.diginamic.daos.AbstractDao;
import fr.diginamic.entities.associatives.WordingOrganisation;

import java.util.HashSet;
import java.util.Set;

public class WordingOrganisationDao extends AbstractDao<WordingOrganisation> {

    private final Set<WordingOrganisation> wordingOrganisations;

    public WordingOrganisationDao() {
        wordingOrganisations = findAll();
    }

    @Override
    public Set<WordingOrganisation> findAll() {
        return new HashSet<>(em.createQuery("SELECT w FROM WordingOrganisation w", WordingOrganisation.class).getResultList());
    }

    @Override
    public WordingOrganisation findByPk(int id) {
        return wordingOrganisations.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean exists(String organisationName, String languageName) {
        return wordingOrganisations.stream()
                .anyMatch(w -> w.getName().equals(organisationName) && w.getLangue().getName().equals(languageName));
    }

    @Override
    public void save(WordingOrganisation wordingOrganisation) {
        wordingOrganisations.add(wordingOrganisation);
        em.persist(wordingOrganisation);
    }
}
