package fr.diginamic.utils;

import fr.diginamic.daos.*;
import fr.diginamic.daos.associatives.WordingEpreuveDao;
import fr.diginamic.daos.associatives.WordingOrganisationDao;
import fr.diginamic.daos.associatives.WordingSportDao;
import fr.diginamic.entities.*;
import fr.diginamic.entities.associatives.WordingEpreuve;
import fr.diginamic.entities.associatives.WordingOrganisation;
import fr.diginamic.entities.associatives.WordingSport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Import {

    public static void sportFile(EntityManager em, String path, int limit) {
        try {
            Reader in = new FileReader(path);
            CSVFormat format = CSVFormat.Builder
                    .create(CSVFormat.EXCEL)
                    .setDelimiter(';')
                    .setHeader("libelle_en", "libelle_fr")
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = format.parse(in);

            SportDao sportDao = new SportDao(em);
            LangueDao langueDao = new LangueDao(em);
            WordingSportDao wordingSportDao = new WordingSportDao(em);

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

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
            transaction.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void epreuveFile(EntityManager em, String path, int limit) {
        try {
            Reader in = new FileReader(path);
            CSVFormat format = CSVFormat.Builder
                    .create(CSVFormat.EXCEL)
                    .setDelimiter(';')
                    .setHeader("event_en", "event_fr")
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = format.parse(in);

            EpreuveDao epreuveDao = new EpreuveDao(em);
            LangueDao langueDao = new LangueDao(em);
            WordingEpreuveDao wordingEpreuveDao = new WordingEpreuveDao(em);

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

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
            transaction.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void organisationFile(EntityManager em, String path, int limit) {
        try {
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.Builder
                    .create(CSVFormat.EXCEL)
                    .setDelimiter(';')
                    .setHeader("cio_code","name_fr","name_en","iso_code", "obsolete")
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(in);

            OrganisationDao organisationDao = new OrganisationDao(em);
            LangueDao langueDao = new LangueDao(em);
            WordingOrganisationDao wordingOrganisationDao = new WordingOrganisationDao(em);

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

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
            transaction.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eventFile(EntityManager em, String path, int limit) {
        try {
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.Builder
                    .create(CSVFormat.EXCEL)
                    .setDelimiter(';')
                    .setHeader("id","champion", "sexe", "age", "taille", "poids", "equipe", "cno", "games", "annee", "saison", "ville", "sport","nom", "medaille")
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(in);

            EventDao eventDao = new EventDao(em);
            OrganisationDao organisationDao = new OrganisationDao(em);
            WordingSportDao wordingSportDao = new WordingSportDao(em);

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            for(CSVRecord record : records) {
                if (limit > 0) {
                    Event event = new Event();
                    if (record.get("nom").isEmpty() || record.get("nom").equals("NA")){
                        event.setNom(null);
                    } else {
                        event.setNom(record.get("nom"));
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
//                    System.out.println("IDENTIFIER: " + record.get("id") + " NOM: " + event.getNom());
                    eventDao.save(event);
                    limit--;
                }
            }
            transaction.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
