package fr.diginamic.daos;

import fr.diginamic.entities.Sport;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SportDao {
    private final EntityManager em;

    private final List<Sport> sports;

    public SportDao(EntityManager em) {
        this.em = em;
        this.sports = findAll();
    }

    public Sport findById(int id) {
        return sports.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Sport> findAll() {
        return em.createQuery("SELECT s FROM Sport s", Sport.class).getResultList();
    }

    public void save(Sport sport) {
        sports.add(sport);
        em.persist(sport);
    }
}
