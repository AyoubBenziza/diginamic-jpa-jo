package fr.diginamic.database;

import fr.diginamic.daos.*;
import fr.diginamic.daos.associatives.WordingEpreuveDao;
import fr.diginamic.daos.associatives.WordingOrganisationDao;
import fr.diginamic.daos.associatives.WordingSportDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Class Connection
 * Manages the connection to the database
 *
 * @see EntityManagerFactory
 * @see EntityManager
 * @see SportDao
 * @see EpreuveDao
 * @see OrganisationDao
 * @see LangueDao
 * @see EventDao
 * @see WordingSportDao
 * @see WordingEpreuveDao
 * @see WordingOrganisationDao
 *
 * @author AyoubBenziza
 */
public class Connection {
    /**
     * EntityManagerFactory emf
     *
     * @see EntityManagerFactory
     */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jo");
    /**
     * EntityManager em
     *
     * @see EntityManager
     */
    private static final EntityManager em = emf.createEntityManager();

    /**
     * SportDao sportDao
     *
     * @see SportDao
     */
    public static SportDao sportDao = new SportDao();

    /**
     * EpreuveDao epreuveDao
     *
     * @see EpreuveDao
     */
    public static EpreuveDao epreuveDao = new EpreuveDao();

    /**
     * OrganisationDao organisationDao
     *
     * @see OrganisationDao
     */
    public static OrganisationDao organisationDao = new OrganisationDao();

    /**
     * LangueDao langueDao
     *
     * @see LangueDao
     */
    public static LangueDao langueDao = new LangueDao();

    /**
     * EventDao eventDao
     *
     * @see EventDao
     */
    public static EventDao eventDao = new EventDao();

    /**
     * WordingSportDao
     *
     * @see WordingSportDao
     */
    public static WordingSportDao wordingSportDao = new WordingSportDao();

    /**
     * WordingEpreuveDao
     *
     * @see WordingEpreuveDao
     */
    public static WordingEpreuveDao wordingEpreuveDao = new WordingEpreuveDao();

    /**
     * WordingOrganisationDao
     *
     * @see WordingOrganisationDao
     */
    public static WordingOrganisationDao wordingOrganisationDao = new WordingOrganisationDao();

    /**
     * Getter of the EntityManager
     *
     * @return EntityManager
     * @see EntityManager
     */
    public static EntityManager getEntityManager() {
        return em;
    }

    /**
     * Begin a transaction
     *
     * @see EntityManager
     */
    public static void begin() {
        em.getTransaction().begin();
    }

    /**
     * Commit a transaction
     *
     * @see EntityManager
     */
    public static void commit() {
        em.getTransaction().commit();
    }

    /**
     * Rollback a transaction
     *
     * @see EntityManager
     */
    public static void rollback() {
        em.getTransaction().rollback();
    }

    /**
     * Close the connection
     *
     * @see EntityManager
     * @see EntityManagerFactory
     */
    public static void close() {
        em.close();
        emf.close();
    }
}
