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

    public static SportDao sportDao = new SportDao(em);
    public static EpreuveDao epreuveDao = new EpreuveDao(em);
    public static OrganisationDao organisationDao = new OrganisationDao(em);
    public static LangueDao langueDao = new LangueDao(em);
    public static EventDao eventDao = new EventDao(em);
    public static WordingSportDao wordingSportDao = new WordingSportDao(em);
    public static WordingEpreuveDao wordingEpreuveDao = new WordingEpreuveDao(em);
    public static WordingOrganisationDao wordingOrganisationDao = new WordingOrganisationDao(em);
    
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
