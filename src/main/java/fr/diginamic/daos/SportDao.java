package fr.diginamic.daos;

import fr.diginamic.entities.Sport;

import java.util.HashSet;
import java.util.Set;

public class SportDao extends AbstractDao<Sport> {

    private final Set<Sport> sports;

    public SportDao() {
        sports = findAll();
    }

    @Override
    public Sport findByPk(int id) {
        return sports.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Sport> findAll() {
        return new HashSet<>(em.createQuery("SELECT s FROM Sport s", Sport.class).getResultList());
    }

    @Override
    public void save(Sport sport) {
        sports.add(sport);
        em.persist(sport);
    }
}
