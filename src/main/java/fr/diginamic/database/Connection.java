package fr.diginamic.database;

import fr.diginamic.daos.*;
import fr.diginamic.daos.associatives.WordingEpreuveDao;
import fr.diginamic.daos.associatives.WordingOrganisationDao;
import fr.diginamic.daos.associatives.WordingSportDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jo");
    private static final EntityManager em = emf.createEntityManager();

    public static EntityManager getEntityManager() {
        return em;
    }

    public static SportDao getSportDao() {
        return new SportDao(em);
    }

    public static EpreuveDao getEpreuveDao() {
        return new EpreuveDao(em);
    }

    public static OrganisationDao getOrganisationDao() {
        return new OrganisationDao(em);
    }

    public static EventDao getEventDao() {
        return new EventDao(em);
    }

    public static LangueDao getLangueDao() {
        return new LangueDao(em);
    }

    public static WordingEpreuveDao getWordingEpreuveDao() {
        return new WordingEpreuveDao(em);
    }

    public static WordingSportDao getWordingSportDao() {
        return new WordingSportDao(em);
    }

    public static WordingOrganisationDao getWordingOrganisationDao() {
        return new WordingOrganisationDao(em);
    }
    
    public static void begin() {
        em.getTransaction().begin();
    }

    public static void commit() {
        em.getTransaction().commit();
    }

    public static void rollback() {
        em.getTransaction().rollback();
    }


    public static void close() {
        em.close();
        emf.close();
    }
}
