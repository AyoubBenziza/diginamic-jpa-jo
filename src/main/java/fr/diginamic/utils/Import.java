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
import fr.diginamic.exceptions.ImportException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Year;
import java.util.Objects;
import java.util.function.Consumer;

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
     * Import data from a CSV file
     *
     * @param path Path to the CSV file
     * @param headers Headers of the CSV file
     * @param limit Number of records to import
     * @param recordHandler Consumer to handle each record
     */
    public static void importFile(String path, String[] headers, int limit, Consumer<CSVRecord> recordHandler) {
        try {
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.Builder
                    .create(CSVFormat.EXCEL)
                    .setDelimiter(';')
                    .setHeader(headers)
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(in);

            Connection.begin();

            for (CSVRecord record : records) {
                if (limit-- > 0) {
                    recordHandler.accept(record);
                } else {
                    break;
                }
            }

            Connection.commit();
        } catch (IOException e) {
            throw new ImportException("Failed to import data from " + path, e);
        }
    }

    /**
     * Get the value of a record
     *
     * @param record CSVRecord
     * @param key Key of the record
     * @return Value of the record
     */
    private static String getRecordValue(CSVRecord record, String key) {
        String value = record.get(key);
        return (value.isEmpty() || value.equals("NA")) ? null : value;
    }

    /**
     * Get the value of a record as an integer
     *
     * @param record CSVRecord
     * @param key Key of the record
     * @param defaultValue Default value if the record is empty
     * @return Value of the record as an integer
     */
    private static int getRecordValueAsInt(CSVRecord record, String key, int defaultValue) {
        String value = getRecordValue(record, key);
        return value == null ? defaultValue : Integer.parseInt(value);
    }

    /**
     * Get the value of a record as a float
     *
     * @param record CSVRecord
     * @param key Key of the record
     * @param defaultValue Default value if the record is empty
     * @return Value of the record as a float
     */
    private static float getRecordValueAsFloat(CSVRecord record, String key, float defaultValue) {
        String value = getRecordValue(record, key);
        return value == null ? defaultValue : Float.parseFloat(value);
    }

    /**
     * Get or create a Langue
     *
     * @param languageName Name of the language
     * @return Langue
     */
    private static Langue getOrCreateLangue(String languageName) {
        Langue langue = langueDao.findByName(languageName);
        if (langue == null) {
            langue = new Langue(languageName);
            langueDao.save(langue); // Fetch the persisted Langue
        }
        return langue;
    }

    /**
     * Import sports from a CSV file
     *
     * @param path Path to the CSV file
     * @param headers Headers of the CSV file
     * @param limit Number of records to import
     * @see Sport
     * @see Langue
     * @see WordingSport
     * @see SportDao
     * @see LangueDao
     * @see WordingSportDao
     * @see Connection
     */
    public static void sportFile(String path, String[] headers, int limit) {
        importFile(path, headers, limit, record -> {
            Sport sport = new Sport();
            sportDao.save(sport);

            for (String header : headers) {
                String sportName = record.get(header).trim();
                String languageName = header.split("_")[1]; // assuming the header format is "libelle_xx"

                Langue langue = getOrCreateLangue(languageName);

                if (!wordingSportDao.exists(sportName, languageName) && !sportName.isEmpty()) {
                    WordingSport wordingSport = new WordingSport(sportName, langue, sport);
                    wordingSportDao.save(wordingSport);
                    sport.addWording(wordingSport);
                }
            }
        });
    }

    /**
     * Import epreuves from a CSV file
     *
     * @param path Path to the CSV file
     * @param headers Headers of the CSV file
     * @param limit Number of records to import
     * @see Epreuve
     * @see Langue
     * @see WordingEpreuve
     * @see EpreuveDao
     * @see LangueDao
     * @see WordingEpreuveDao
     * @see Connection
     */
    public static void epreuveFile(String path, String[] headers, int limit) {
        importFile(path, headers, limit, record -> {
            Epreuve epreuve = new Epreuve();
            epreuveDao.save(epreuve);

            for (String header : headers) {
                String epreuveName = record.get(header).trim();
                String languageName = header.split("_")[1]; // assuming the header format is "libelle_xx"

                Langue langue = getOrCreateLangue(languageName);

                if (!wordingEpreuveDao.exists(epreuveName, languageName) && !epreuveName.isEmpty()) {
                    WordingEpreuve wordingEpreuve = new WordingEpreuve(epreuveName, langue, epreuve);
                    wordingEpreuveDao.save(wordingEpreuve);
                    epreuve.addWording(wordingEpreuve);
                }
            }
        });
    }

    /**
     * Import organisations from a CSV file
     *
     * @param path Path to the CSV file
     * @param headers Headers of the CSV file
     * @param limit Number of records to import
     * @see Organisation
     * @see Langue
     * @see WordingOrganisation
     * @see OrganisationDao
     * @see LangueDao
     * @see WordingOrganisationDao
     * @see Connection
     */
    public static void organisationFile(String path, String[] headers, int limit) {
        importFile(path, headers, limit, record -> {
            Organisation organisation = new Organisation();
            organisation.setCioCode(getRecordValue(record, "cio_code"));
            organisation.setIsoCode(getRecordValue(record, "iso_code"));
            String obsoleteValue = record.get("obsolete");
            if (!obsoleteValue.isEmpty()){
                if (obsoleteValue.equals("O")) {
                    organisation.setObsolete(true);
                } else if (obsoleteValue.equals("N")) {
                    organisation.setObsolete(false);
                }
            }
            organisationDao.save(organisation);

            String[] headersToCheck = {"organisation_fr", "organisation_en"};
            for (String header : headersToCheck) {
                String organisationName = getRecordValue(record, header);
                String languageName = header.split("_")[1]; // assuming the header format is "libelle_xx"

                Langue langue = getOrCreateLangue(languageName);

                if (!wordingOrganisationDao.exists(organisationName,languageName) && organisationName != null) {
                    WordingOrganisation wordingOrganisation = new WordingOrganisation(organisationName, langue, organisation);
                    wordingOrganisationDao.save(wordingOrganisation);
                    organisation.addWording(wordingOrganisation);
                }
            }
        });
    }

    /**
     * Import events from a CSV file
     *
     * @param path Path to the CSV file
     * @param headers Headers of the CSV file
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
    public static void eventFile(String path, String[] headers, int limit) {
        importFile(path, headers, limit, record -> {
            Event event = new Event();
            event.setNom(getRecordValue(record, "event"));
            event.setSexe(getRecordValue(record, "sexe") == null ? null : Objects.requireNonNull(getRecordValue(record, "sexe")).charAt(0));
            event.setAge(getRecordValueAsInt(record, "age", 0));
            event.setTaille(getRecordValueAsInt(record, "taille", 0));
            event.setPoids(getRecordValueAsFloat(record, "poids", 0));
            event.setEquipe(getRecordValue(record, "equipe"));

            String cno = getRecordValue(record, "cno");
            event.setCno(cno);
            if (cno != null) {
                Organisation organisation = organisationDao.findByCioCode(cno);
                if (organisation != null) {
                    event.setOrganisation(organisation);
                }
            }

            String annee = getRecordValue(record, "annee");
            if (annee != null) {
                event.setAnnee(Year.of(Integer.parseInt(annee)));
            }
            event.setSaison(getRecordValue(record, "saison"));
            event.setVille(getRecordValue(record, "ville"));
            event.setChampion(getRecordValue(record, "champion"));
            event.setMedaille(getRecordValue(record, "medaille"));

            String sportName = getRecordValue(record, "sport");
            if (sportName != null) {
                WordingSport wordingSport = wordingSportDao.findBySportName(sportName);
                if (wordingSport != null) {
                    event.setSport(wordingSport.getSport());
                }
            }

            String epreuveName = getRecordValue(record, "event");
            WordingEpreuve wordingEpreuve = wordingEpreuveDao.findByEpreuveName(epreuveName);
            if (wordingEpreuve != null) {
                event.setEpreuve(wordingEpreuve.getEpreuve());
            }

            eventDao.save(event);
        });
    }
}
