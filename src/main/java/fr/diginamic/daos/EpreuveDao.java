package fr.diginamic.daos;

import fr.diginamic.entities.Epreuve;

import java.util.HashSet;
import java.util.Set;

public class EpreuveDao extends AbstractDao{
    private final Set<Epreuve> epreuves = new HashSet<>();

    public EpreuveDao() {
        this.epreuves.addAll(em.createQuery("SELECT e FROM Epreuve e", Epreuve.class).getResultList());
    }

    public Epreuve findById(int id) {
        return epreuves.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Epreuve epreuve) {
        epreuves.add(epreuve);
        em.persist(epreuve);
    }
}
