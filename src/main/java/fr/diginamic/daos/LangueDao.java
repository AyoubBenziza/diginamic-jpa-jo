package fr.diginamic.daos;

import fr.diginamic.entities.Langue;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class LangueDao {
    private final EntityManager em;

    private final Set<Langue> langues = new HashSet<>();

    public LangueDao(EntityManager em) {
        this.em = em;
        this.langues.addAll(em.createQuery("SELECT l FROM Langue l", Langue.class).getResultList());
    }

    public Langue findByName(String name) {
        return langues.stream()
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void save(Langue langue) {
        langues.add(langue);
        em.persist(langue);
    }
}
