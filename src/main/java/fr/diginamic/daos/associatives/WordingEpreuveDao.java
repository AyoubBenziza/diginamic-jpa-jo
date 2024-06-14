package fr.diginamic.daos.associatives;

import fr.diginamic.entities.associatives.WordingEpreuve;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class WordingEpreuveDao {

    private final EntityManager em;

    private final Set<WordingEpreuve> wordingEpreuves = new HashSet<>();

    public WordingEpreuveDao(EntityManager em) {
        this.em = em;
        this.wordingEpreuves.addAll(em.createQuery("SELECT w FROM WordingEpreuve w", WordingEpreuve.class).getResultList());
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