package fr.diginamic.daos;

import fr.diginamic.entities.Langue;

import java.util.HashSet;
import java.util.Set;

public class LangueDao extends AbstractDao{

    private final Set<Langue> langues = new HashSet<>();

    public LangueDao() {
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
