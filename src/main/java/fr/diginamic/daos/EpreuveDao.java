package fr.diginamic.daos;

import fr.diginamic.entities.Epreuve;

import java.util.HashSet;
import java.util.Set;

public class EpreuveDao extends AbstractDao<Epreuve>{
    private final Set<Epreuve> epreuves;

    public EpreuveDao() {
        epreuves = findAll();
    }

    @Override
    public Set<Epreuve> findAll() {
        return new HashSet<>(em.createQuery("SELECT e FROM Epreuve e", Epreuve.class).getResultList());
    }

    @Override
    public Epreuve findByPk(int id) {
        return epreuves.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Epreuve epreuve) {
        epreuves.add(epreuve);
        em.persist(epreuve);
    }
}
