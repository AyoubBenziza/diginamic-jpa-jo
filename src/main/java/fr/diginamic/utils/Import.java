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

import java.io.FileNotFoundException;
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
                    .setHeader("id","nom", "sexe", "age", "taille", "poids", "equipe", "cno", "games", "annee", "saison", "ville", "champion", "medaille")
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(in);

            EventDao eventDao = new EventDao(em);

            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            for(CSVRecord record : records) {
                if (limit > 0) {
                    String nom = record.get("nom").trim();
                    if (nom.isEmpty() || nom.equals("NA")) {
                        nom = null;
                    }
                    String sexe = record.get("sexe").trim();
                    if (sexe.isEmpty() || sexe.equals("NA")) {
                        sexe = null;
                    }
                    int age = 0;
                    if (!record.get("age").isEmpty() || !record.get("age").equals("NA")) {
                        age = Integer.parseInt(record.get("age").trim());
                    }
                    int taille = 0;
                    if (!record.get("taille").isEmpty() || !record.get("taille").equals("NA")) {
                        taille = Integer.parseInt(record.get("taille").trim());
                    }
                    int poids = 0;
                    if (!record.get("poids").isEmpty() || !record.get("poids").equals("NA")) {
                        poids = Integer.parseInt(record.get("poids").trim());
                    }
                    String equipe = record.get("equipe").trim();
                    if (equipe.isEmpty() || equipe.equals("NA")) {
                        equipe = null;
                    }
                    String cno = record.get("cno").trim();
                    if (cno.isEmpty() || cno.equals("NA")) {
                        cno = null;
                    }
                    int annee = 0;
                    if (!record.get("annee").isEmpty() || !record.get("annee").equals("NA")) {
                        annee = Integer.parseInt(record.get("annee").trim());
                    }
                    String saison = record.get("saison").trim();
                    if (saison.isEmpty() || saison.equals("NA")) {
                        saison = null;
                    }
                    String ville = record.get("ville").trim();
                    if (ville.isEmpty() || ville.equals("NA")) {
                        ville = null;
                    }
                    String champion = record.get("champion").trim();
                    if (champion.isEmpty() || champion.equals("NA")) {
                        champion = null;
                    }
                    String medaille = record.get("medaille").trim();
                    if (medaille.isEmpty() || medaille.equals("NA")) {
                        medaille = null;
                    }
                    System.out.println("IDENTIFIER: " + record.get("id") + " NOM: " + nom);
                    Event event = new Event(nom, sexe.charAt(0), age, taille, poids, equipe, cno, annee, saison, ville, champion, medaille);
                    eventDao.save(event);
                    limit--;
                }
            }
            transaction.commit();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
