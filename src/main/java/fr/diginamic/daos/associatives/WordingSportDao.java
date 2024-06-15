package fr.diginamic.daos.associatives;

import fr.diginamic.daos.AbstractDao;
import fr.diginamic.entities.associatives.WordingSport;

import java.util.HashSet;
import java.util.Set;

public class WordingSportDao extends AbstractDao {

    private final Set<WordingSport> wordingSports = new HashSet<>();

    public WordingSportDao() {
        this.wordingSports.addAll(em.createQuery("SELECT ws FROM WordingSport ws", WordingSport.class).getResultList());
    }

    public WordingSport findBySportName(String sportName) {
        System.out.println(wordingSports);
        return wordingSports.stream()
                .filter(w -> w.getName().equals(sportName))
                .findFirst()
                .orElse(null);
    }

    public boolean exists(String sportName, String languageName) {
        return wordingSports.stream()
                .anyMatch(w -> w.getName().equals(sportName) && w.getLangue().getName().equals(languageName));
    }

    public void save(WordingSport wordingSport) {
        wordingSports.add(wordingSport);
        em.persist(wordingSport);
    }
}
