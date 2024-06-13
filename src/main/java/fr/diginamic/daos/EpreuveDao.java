package fr.diginamic.daos;

import fr.diginamic.entities.Epreuve;
import jakarta.persistence.EntityManager;

public class EpreuveDao {
    private final EntityManager em;

    public EpreuveDao(EntityManager em) {
        this.em = em;
    }

    public void save(Epreuve epreuve) {
        em.persist(epreuve);
    }
}
