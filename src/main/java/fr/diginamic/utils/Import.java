package fr.diginamic.utils;

import fr.diginamic.daos.*;
import fr.diginamic.daos.associatives.WordingEpreuveDao;
import fr.diginamic.daos.associatives.WordingOrganisationDao;
import fr.diginamic.daos.associatives.WordingSportDao;
import fr.diginamic.database.Connection;
import fr.diginamic.entities.*;
import fr.diginamic.entities.associatives.WordingEpreuve;
import fr.diginamic.entities.associatives.WordingOrganisation;
import fr.diginamic.entities.associatives.WordingSport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Class Import
 * Each method imports data from a specific CSV file
 *
 * @see Connection
 * @see Sport
 * @see Epreuve
 * @see Organisation
 * @see Event
 * @see Langue
 * @see WordingSport
 * @see WordingEpreuve
 * @see WordingOrganisation
 * @see SportDao
 * @see EpreuveDao
 * @see OrganisationDao
 * @see EventDao
 * @see LangueDao
 * @see WordingSportDao
 * @see WordingEpreuveDao
 * @see WordingOrganisationDao
 *
 * @author AyoubBenziza
 */
public class Import {

    private static final SportDao sportDao = Connection.sportDao;
    private static final EpreuveDao epreuveDao = Connection.epreuveDao;
    private static final OrganisationDao organisationDao = Connection.organisationDao;
    private static final EventDao eventDao = Connection.eventDao;
    private static final LangueDao langueDao = Connection.langueDao;
    private static final WordingSportDao wordingSportDao = Connection.wordingSportDao;
    private static final WordingEpreuveDao wordingEpreuveDao = Connection.wordingEpreuveDao;
    private static final WordingOrganisationDao wordingOrganisationDao = Connection.wordingOrganisationDao;

    /**
     * Import sports from a CSV file
     *
     * @param path Path to the CSV file
     * @param limit Number of records to import
     * @see Sport
     * @see Langue
     * @see WordingSport
     * @see SportDao
     * @see LangueDao
     * @see WordingSportDao
     * @see Connection
     */
    public static void sportFile(String path, int limit) {
        try {
            Reader in = new FileReader(path);
            CSVFormat format = CSVFormat.Builder
                    .create(CSVFormat.EXCEL)
                    .setDelimiter(';')
                    .setHeader("libelle_en", "libelle_fr")
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = format.parse(in);

            Connection.begin();

            for(CSVRecord record : records) {
                if (limit > 0) {
                    Sport sport = new Sport();
                    sportDao.save(sport);

                    for (String header : format.getHeader()) {
                        String sportName = record.get(header).trim();
                        String languageName = header.split("_")[1]; // assuming the header format is "libelle_xx"

                        Langue langue = langueDao.findByName(languageName);
                        if (langue == null) {
                            langue = new Langue(languageName);
                            langueDao.save(langue); // Fetch the persisted Langue
                        }

                        if (!wordingSportDao.exists(sportName,languageName) && !sportName.isEmpty()) {
                            WordingSport wordingSport = new WordingSport(sportName, langue, sport);
                            wordingSportDao.save(wordingSport);
                            sport.addWording(wordingSport);
                        }
                    }
                    limit--;
                } else {
                    break;
                }
            }
            Connection.commit();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Import epreuves from a CSV file
     *
     * @param path Path to the CSV file
     * @param limit Number of records to import
     * @see Epreuve
     * @see Langue
     * @see WordingEpreuve
     * @see EpreuveDao
     * @see LangueDao
     * @see WordingEpreuveDao
     * @see Connection
     */
    public static void epreuveFile(String path, int limit) {
        try {
            Reader in = new FileReader(path);
            CSVFormat format = CSVFormat.Builder
                    .create(CSVFormat.EXCEL)
                    .setDelimiter(';')
                    .setHeader("event_en", "event_fr")
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = format.parse(in);

            Connection.begin();

            for(CSVRecord record : records) {
                if (limit > 0) {
                    Epreuve epreuve = new Epreuve();
                    epreuveDao.save(epreuve);

                    for (String header : format.getHeader()) {
                        String epreuveName = record.get(header).trim();
                        String languageName = header.split("_")[1]; // assuming the header format is "libelle_xx"

                        Langue langue = langueDao.findByName(languageName);
                        if (langue == null) {
                            langue = new Langue(languageName);
                            langueDao.save(langue); // Fetch the persisted Langue
                        }

                        if (!wordingEpreuveDao.exists(epreuveName,languageName) && !epreuveName.isEmpty()) {
                            WordingEpreuve wordingEpreuve = new WordingEpreuve(epreuveName, langue, epreuve);
                            wordingEpreuveDao.save(wordingEpreuve);
                            epreuve.addWording(wordingEpreuve);
                        }
                    }
                    limit--;
                } else {
                    break;
                }
            }
            Connection.commit();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Import organisations from a CSV file
     *
     * @param path Path to the CSV file
     * @param limit Number of records to import
     * @see Organisation
     * @see Langue
     * @see WordingOrganisation
     * @see OrganisationDao
     * @see LangueDao
     * @see WordingOrganisationDao
     * @see Connection
     */
    public static void organisationFile(String path, int limit) {
        try {
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.Builder
                    .create(CSVFormat.EXCEL)
                    .setDelimiter(';')
                    .setHeader("cio_code","name_fr","name_en","iso_code", "obsolete")
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(in);

            Connection.begin();

            for(CSVRecord record : records) {
                if (limit > 0) {
                    Organisation organisation = new Organisation();
                    organisation.setCioCode(record.get("cio_code"));
                    if (!record.get("iso_code").isEmpty()){
                        organisation.setIsoCode(record.get("iso_code"));
                    }
                    if (!record.get("obsolete").isEmpty()){
                        organisation.setObsolete(Boolean.parseBoolean(record.get("obsolete")));
                    }
                    organisationDao.save(organisation);

                    String[] headersToCheck = {"name_fr", "name_en"};

                    for (String header : headersToCheck) {
                        String organisationName = record.get(header).trim();
                        String languageName = header.split("_")[1]; // assuming the header format is "libelle_xx"

                        Langue langue = langueDao.findByName(languageName);
                        if (langue == null) {
                            langue = new Langue(languageName);
                            langueDao.save(langue); // Fetch the persisted Langue
                        }

                        if (!wordingOrganisationDao.exists(organisationName,languageName) && !organisationName.isEmpty()) {
                            WordingOrganisation wordingOrganisation = new WordingOrganisation(organisationName, langue, organisation);
                            wordingOrganisationDao.save(wordingOrganisation);
                            organisation.addWording(wordingOrganisation);
                        }
                    }
                    limit--;
                } else {
                    break;
                }
            }
            Connection.commit();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Import events from a CSV file
     *
     * @param path Path to the CSV file
     * @param limit Number of records to import
     * @see Event
     * @see Organisation
     * @see WordingSport
     * @see WordingEpreuve
     * @see EventDao
     * @see OrganisationDao
     * @see WordingSportDao
     * @see WordingEpreuveDao
     * @see Connection
     */
    public static void eventFile(String path, int limit) {
        try {
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.Builder
                    .create(CSVFormat.EXCEL)
                    .setDelimiter(';')
                    .setHeader("id","champion", "sexe", "age", "taille", "poids", "equipe", "cno", "games", "annee", "saison", "ville", "sport","event", "medaille")
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(in);

            Connection.begin();

            for(CSVRecord record : records) {
                if (limit > 0) {
                    Event event = new Event();
                    if (record.get("event").isEmpty() || record.get("event").equals("NA")){
                        event.setNom(null);
                    } else {
                        event.setNom(record.get("event"));
                    }

                    if (record.get("sexe").isEmpty() || record.get("sexe").equals("NA")){
                        event.setSexe(null);
                    } else {
                        event.setSexe(record.get("sexe").charAt(0));
                    }

                    if (record.get("age").isEmpty() || record.get("age").equals("NA")){
                        event.setAge(0);
                    } else {
                        event.setAge(Integer.parseInt(record.get("age")));
                    }

                    if (record.get("taille").isEmpty() || record.get("taille").equals("NA")){
                        event.setTaille(0);
                    } else {
                        event.setTaille(Integer.parseInt(record.get("taille")));
                    }

                    if (record.get("poids").isEmpty() || record.get("poids").equals("NA")){
                        event.setPoids(0);
                    } else {
                        // Convert String to float
                        event.setPoids(Float.parseFloat(record.get("poids")));
                    }

                    if (record.get("equipe").isEmpty() || record.get("equipe").equals("NA")){
                        event.setEquipe(null);
                    } else {
                        event.setEquipe(record.get("equipe"));
                    }

                    if (record.get("cno").isEmpty() || record.get("cno").equals("NA")){
                        event.setCno(null);
                    } else {
                        String cno = record.get("cno");
                        event.setCno(cno);

                        // Check if an organisation exists with the given CIO code
                        Organisation organisation = organisationDao.findByCioCode(cno);
                        if (organisation != null) {
                            // Organisation found, you can set it to the event
                            event.setOrganisation(organisation);
                        }
                    }

                    if (record.get("annee").isEmpty() || record.get("annee").equals("NA")){
                        event.setAnnee(0);
                    } else {
                        event.setAnnee(Integer.parseInt(record.get("annee")));
                    }

                    if (record.get("saison").isEmpty() || record.get("saison").equals("NA")){
                        event.setSaison(null);
                    } else {
                        event.setSaison(record.get("saison"));
                    }

                    if (record.get("ville").isEmpty() || record.get("ville").equals("NA")){
                        event.setVille(null);
                    } else {
                        event.setVille(record.get("ville"));
                    }

                    if (record.get("champion").isEmpty() || record.get("champion").equals("NA")){
                        event.setChampion(null);
                    } else {
                        event.setChampion(record.get("champion"));
                    }

                    if (record.get("medaille").isEmpty() || record.get("medaille").equals("NA")){
                        event.setMedaille(null);
                    } else {
                        event.setMedaille(record.get("medaille"));
                    }

                    if (record.get("sport").isEmpty() || record.get("sport").equals("NA")){
                        event.setSport(null);
                    } else {
                        String sportName = record.get("sport");
                        WordingSport wordingSport = wordingSportDao.findBySportName(sportName);
                        if (wordingSport != null) {
                            event.setSport(wordingSport.getSport());
                        }
                    }

                    String epreuveName = record.get("event");
                    WordingEpreuve wordingEpreuve = wordingEpreuveDao.findByEpreuveName(epreuveName);
                    if (wordingEpreuve != null) {
                        event.setEpreuve(wordingEpreuve.getEpreuve());
                    }
                    eventDao.save(event);
                    limit--;
                }
            }
            Connection.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
