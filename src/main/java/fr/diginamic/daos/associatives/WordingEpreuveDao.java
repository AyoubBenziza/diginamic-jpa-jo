package fr.diginamic.daos.associatives;

import fr.diginamic.daos.AbstractDao;
import fr.diginamic.entities.associatives.WordingEpreuve;

import java.util.HashSet;
import java.util.Set;

public class WordingEpreuveDao extends AbstractDao {

    private final Set<WordingEpreuve> wordingEpreuves = new HashSet<>();

    public WordingEpreuveDao() {
        this.wordingEpreuves.addAll(em.createQuery("SELECT w FROM WordingEpreuve w", WordingEpreuve.class).getResultList());
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

    public void save(WordingEpreuve wordingEpreuve) {
        wordingEpreuves.add(wordingEpreuve);
        em.persist(wordingEpreuve);
    }
}