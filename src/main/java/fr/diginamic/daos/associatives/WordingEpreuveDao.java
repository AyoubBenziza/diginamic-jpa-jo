package fr.diginamic.daos.associatives;

import fr.diginamic.daos.AbstractDao;
import fr.diginamic.entities.associatives.WordingEpreuve;

import java.util.HashSet;
import java.util.Set;

public class WordingEpreuveDao extends AbstractDao<WordingEpreuve> {

    private final Set<WordingEpreuve> wordingEpreuves;

    public WordingEpreuveDao() {
        wordingEpreuves = findAll();
    }

    @Override
    public Set<WordingEpreuve> findAll() {
        return new HashSet<>(em.createQuery("SELECT w FROM WordingEpreuve w", WordingEpreuve.class).getResultList());
    }

    @Override
    public WordingEpreuve findByPk(int id) {
        return wordingEpreuves.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public WordingEpreuve findByEpreuveName(String epreuveName) {
        return wordingEpreuves.stream()
                .filter(w -> w.getName().equals(epreuveName))
                .findFirst()
                .orElse(null);
    }

    public boolean exists(String epreuveName, String languageName) {
        return wordingEpreuves.stream()
                .anyMatch(w -> w.getName().equals(epreuveName) && w.getLangue().getName().equals(languageName));
    }

    @Override
    public void save(WordingEpreuve wordingEpreuve) {
        wordingEpreuves.add(wordingEpreuve);
        em.persist(wordingEpreuve);
    }
}