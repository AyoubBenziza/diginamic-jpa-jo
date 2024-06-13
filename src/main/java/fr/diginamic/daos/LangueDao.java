package fr.diginamic.daos;

import fr.diginamic.entities.Langue;
import jakarta.persistence.EntityManager;

public class LangueDao {
    private final EntityManager em;

    public LangueDao(EntityManager em) {
        this.em = em;
    }

    public Langue findByName(String name) {
        return em.createQuery("SELECT l FROM Langue l WHERE l.name = :name", Langue.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void save(Langue langue) {
        em.persist(langue);
    }
}
