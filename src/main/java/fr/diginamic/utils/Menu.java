package fr.diginamic.utils;

import fr.diginamic.daos.SportDao;
import fr.diginamic.database.Connection;

import java.util.Objects;
import java.util.Scanner;

/**
 * Class Menu
 * Each method of this class displays a menu for the user to interact with the application
 *
 * @see SportDao
 * @see Connection
 * @see Import
 * @see Scanner
 *
 * @author AyoubBenziza
 */
public class Menu {
    /**
     * Path to the sports.csv file
     */
    private static final String SPORTS_CSV = Objects.requireNonNull(Menu.class.getClassLoader().getResource("sports.csv")).getPath();

    /**
     * Path to the epreuves.csv file
     */
    private static final String EPREUVES_CSV = Objects.requireNonNull(Menu.class.getClassLoader().getResource("epreuves.csv")).getPath();

    /**
     * Path to the organisations.csv file
     */
    private static final String ORGANISATIONS_CSV = Objects.requireNonNull(Menu.class.getClassLoader().getResource("organisations.csv")).getPath();

    /**
     * Path to the events.csv file
     */
    private static final String EVENTS_CSV = Objects.requireNonNull(Menu.class.getClassLoader().getResource("evenements.csv")).getPath();

    /**
     * {@link SportDao} sportDao
     */
    private static final SportDao sportDao = Connection.sportDao;

    /**
     * {@link Scanner} scanner
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Display the menu of the application
     */
    public static void displayMenu() {
        int choice;
        do{
            System.out.println("***** Application de gestion des événements sportifs *****");
            System.out.println("1. Gestion des sports");
            System.out.println("2. Gestion des épreuves");
            System.out.println("3. Gestion des organisations");
            System.out.println("4. Gestion des événements");
            System.out.println("5. Importer les données");
            System.out.println("99. Quitter");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayMenuSport();
                    break;
                case 2:
                    displayMenuEpreuve();
                    break;
                case 3:
                    displayMenuOrganisation();
                    break;
                case 4:
                    displayMenuEvenement();
                    break;
                case 5:
                    importData();
                    break;
                case 99:
                    Connection.close();
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }while (choice != 99);
        scanner.close();
    }

    /**
     * Display the menu of the sports
     */
    public static void displayMenuSport() {
        int choice;
        do {
            System.out.println("***** Gestion des sports *****");
            System.out.println("1. Lister les sports");
            System.out.println("2. Ajouter un sport");
            System.out.println("3. Supprimer un sport");
            System.out.println("99. Retour au menu principal");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Liste des sports");
                    sportDao.findAll().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Ajout d'un sport");
                    break;
                case 3:
                    System.out.println("Suppression d'un sport");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choice != 99);
    }

    /**
     * Display the menu of the events
     */
    public static void displayMenuEpreuve() {
        int choice;
        do {
            System.out.println("***** Gestion des épreuves *****");
            System.out.println("1. Lister les épreuves");
            System.out.println("2. Ajouter une épreuve");
            System.out.println("3. Supprimer une épreuve");
            System.out.println("99. Retour au menu principal");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Liste des épreuves");
                    break;
                case 2:
                    System.out.println("Ajout d'une épreuve");
                    break;
                case 3:
                    System.out.println("Suppression d'une épreuve");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choice != 99);
    }

    /**
     * Display the menu of the organisations
     */
    public static void displayMenuOrganisation() {
        int choice;
        do {
            System.out.println("***** Gestion des organisations *****");
            System.out.println("1. Lister les organisations");
            System.out.println("2. Ajouter une organisation");
            System.out.println("3. Supprimer une organisation");
            System.out.println("99. Retour au menu principal");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Liste des organisations");
                    break;
                case 2:
                    System.out.println("Ajout d'une organisation");
                    break;
                case 3:
                    System.out.println("Suppression d'une organisation");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choice != 99);
    }

    /**
     * Display the menu of the events
     */
    public static void displayMenuEvenement() {
        int choice;
        do {
            System.out.println("***** Gestion des événements *****");
            System.out.println("1. Lister les événements");
            System.out.println("2. Ajouter un événement");
            System.out.println("3. Supprimer un événement");
            System.out.println("99. Retour au menu principal");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Liste des événements");
                    break;
                case 2:
                    System.out.println("Ajout d'un événement");
                    break;
                case 3:
                    System.out.println("Suppression d'un événement");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choice != 99);
    }

    /**
     * Import the data from the CSV files
     *
     * @see Import
     */
    public static void importData() {
        System.out.println("Import des données");
        Import.sportFile(SPORTS_CSV, Integer.MAX_VALUE);
        Import.epreuveFile(EPREUVES_CSV, Integer.MAX_VALUE);
        Import.organisationFile(ORGANISATIONS_CSV, Integer.MAX_VALUE);
        Import.eventFile(EVENTS_CSV, 200);
    }
}
