package fr.diginamic.daos;

import fr.diginamic.entities.Sport;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class SportDao {
    private final EntityManager em;

    private final Set<Sport> sports = new HashSet<>();

    public SportDao(EntityManager em) {
        this.em = em;
        this.sports.addAll(em.createQuery("SELECT s FROM Sport s", Sport.class).getResultList());
    }

    public Sport findById(int id) {
        return sports.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Sport sport) {
        sports.add(sport);
        em.persist(sport);
    }
}
