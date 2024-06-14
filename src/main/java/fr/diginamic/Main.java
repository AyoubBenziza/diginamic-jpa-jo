package fr.diginamic;

import fr.diginamic.utils.Import;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Objects;

public class Main {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jo");
    private static final EntityManager em = emf.createEntityManager();
    private static final String SPORTS_CSV = Objects.requireNonNull(Main.class.getClassLoader().getResource("sports.csv")).getPath();
    private static final String EPREUVES_CSV = Objects.requireNonNull(Main.class.getClassLoader().getResource("epreuves.csv")).getPath();
    private static final String ORGANISATIONS_CSV = Objects.requireNonNull(Main.class.getClassLoader().getResource("organisations.csv")).getPath();
    private static final String EVENTS_CSV = Objects.requireNonNull(Main.class.getClassLoader().getResource("evenements.csv")).getPath();

    public static void main(String[] args) {

        Import.sportFile(em, SPORTS_CSV, Integer.MAX_VALUE);
        Import.organisationFile(em, ORGANISATIONS_CSV, Integer.MAX_VALUE);
        Import.epreuveFile(em, EPREUVES_CSV, Integer.MAX_VALUE);
        Import.eventFile(em, EVENTS_CSV, 100);

        em.close();
        emf.close();
    }
}