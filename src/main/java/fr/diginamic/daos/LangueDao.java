package fr.diginamic.daos;

import fr.diginamic.entities.Langue;

import java.util.HashSet;
import java.util.Set;

public class LangueDao extends AbstractDao<Langue> {

    private final Set<Langue> langues;

    public LangueDao() {
        langues = findAll();
    }

    @Override
    public Set<Langue> findAll() {
        return new HashSet<>(em.createQuery("SELECT l FROM Langue l", Langue.class).getResultList());
    }

    @Override
    public Langue findByPk(int id) {
        return langues.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Langue findByName(String name) {
        return langues.stream()
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Langue langue) {
        langues.add(langue);
        em.persist(langue);
    }
}
