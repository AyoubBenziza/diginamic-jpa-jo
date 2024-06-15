package fr.diginamic.daos;

import fr.diginamic.entities.Sport;

import java.util.HashSet;
import java.util.Set;

public class SportDao extends AbstractDao {

    private final Set<Sport> sports;

    public SportDao() {
        sports = findAll();
    }

    public Sport findById(int id) {
        return sports.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Set<Sport> findAll() {
        return new HashSet<>(em.createQuery("SELECT s FROM Sport s", Sport.class).getResultList());
    }

    public void save(Sport sport) {
        sports.add(sport);
        em.persist(sport);
    }
}
