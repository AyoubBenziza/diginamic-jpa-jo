package fr.diginamic.daos;

import fr.diginamic.entities.Sport;

import java.util.List;

public class SportDao extends AbstractDao {

    private final List<Sport> sports;

    public SportDao() {
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
