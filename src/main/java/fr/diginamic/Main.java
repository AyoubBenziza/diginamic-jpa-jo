package fr.diginamic;

import fr.diginamic.utils.Import;

import java.util.Objects;

public class Main {
    private static final String SPORTS_CSV = Objects.requireNonNull(Main.class.getClassLoader().getResource("sports.csv")).getPath();
    private static final String EPREUVES_CSV = Objects.requireNonNull(Main.class.getClassLoader().getResource("epreuves.csv")).getPath();
    private static final String ORGANISATIONS_CSV = Objects.requireNonNull(Main.class.getClassLoader().getResource("organisations.csv")).getPath();
    private static final String EVENTS_CSV = Objects.requireNonNull(Main.class.getClassLoader().getResource("evenements.csv")).getPath();

    public static void main(String[] args) {

        Import.sportFile(SPORTS_CSV, Integer.MAX_VALUE);
        Import.epreuveFile(EPREUVES_CSV, Integer.MAX_VALUE);
        Import.organisationFile(ORGANISATIONS_CSV, Integer.MAX_VALUE);
        Import.eventFile(EVENTS_CSV, 100);
    }
}