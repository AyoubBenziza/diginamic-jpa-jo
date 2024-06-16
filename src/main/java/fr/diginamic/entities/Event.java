package fr.diginamic.entities;

import jakarta.persistence.*;

/**
 * Represents an Event in the system
 * Links to Organisation
 * Links to Sport
 * Links to Epreuve
 *
 * @see Sport
 * @see Epreuve
 * @see Organisation
 *
 * @author AyoubBenziza
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "event")
public class Event {
    /**
     * The unique identifier of the Event
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the Event
     */
    private String nom;

    /**
     * The name of the champion
     */
    private String champion;

    /**
     * The gender of the champion
     */
    private Character sexe;

    /**
     * The age of the champion
     */
    private int age;

    /**
     * The height of the champion
     */
    private int taille;

    /**
     * The weight of the champion
     */
    private float poids;

    /**
     * The team of the champion
     */
    private String equipe;

    /**
     * The country code of the champion
     */
    private String cno;

    /**
     * The year of the event
     */
    private int annee;

    /**
     * The season of the event
     */
    private String saison;

    /**
     * The city of the event
     */
    private String ville;

    /**
     * The medal of the champion (if any)
     */
    @Column(name = "medaille", nullable = true)
    private String medaille;

    /**
     * The organisation that organised the event
     *
     * @see Organisation
     */
    @ManyToOne
    private Organisation organisation;

    /**
     * The sport of the event
     *
     * @see Sport
     */
    @ManyToOne
    private Sport sport;

    /**
     * The epreuve of the event
     *
     * @see Epreuve
     */
    @ManyToOne
    private Epreuve epreuve;

    /**
     * Default constructor.
     */
    public Event() {}

    /**
     * Constructor with parameters.
     *
     * @param nom name of the event
     * @param sexe gender of the champion
     * @param age age of the champion
     * @param taille height of the champion
     * @param poids weight of the champion
     * @param equipe team of the champion
     * @param cno country code of the champion
     * @param annee year of the event
     * @param saison season of the event
     * @param ville city of the event
     * @param champion name of the champion
     * @param medaille medal of the champion (if any)
     */
    public Event(String nom, Character sexe, int age, int taille, int poids, String equipe, String cno, int annee, String saison, String ville, String champion, String medaille) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.equipe = equipe;
        this.cno = cno;
        this.annee = annee;
        this.saison = saison;
        this.ville = ville;
        this.champion = champion;
        this.medaille = medaille;
    }

    /**
     * Setter for the unique identifier of this Event.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the unique identifier of this Event.
     *
     * @return the unique identifier of this Event
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the name of this Event.
     *
     * @param nom the name to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter for the name of this Event.
     *
     * @return the name
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter for the gender of the champion.
     *
     * @param sexe the gender to set
     */
    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    /**
     * Getter for the gender of the champion.
     *
     * @return the gender
     */
    public Character getSexe() {
        return sexe;
    }

    /**
     * Setter for the age of the champion.
     *
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter for the age of the champion.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter for the height of the champion.
     *
     * @param taille the height to set
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Getter for the height of the champion.
     *
     * @return the height
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Setter for the weight of the champion.
     *
     * @param poids the weight to set
     */
    public void setPoids(float poids) {
        this.poids = poids;
    }

    /**
     * Getter for the weight of the champion.
     *
     * @return the weight
     */
    public float getPoids() {
        return poids;
    }

    /**
     * Setter for the team of the champion.
     *
     * @param equipe the team to set
     */
    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    /**
     * Getter for the team of the champion.
     *
     * @return the team
     */
    public String getEquipe() {
        return equipe;
    }

    /**
     * Setter for the country code of the champion.
     *
     * @param cno the country code to set
     */
    public void setCno(String cno) {
        this.cno = cno;
    }

    /**
     * Getter for the country code of the champion.
     *
     * @return the country code
     */
    public String getCno() {
        return cno;
    }

    /**
     * Setter for the year of the event.
     *
     * @param annee the year to set
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Getter for the year of the event.
     *
     * @return the year
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Setter for the season of the event.
     *
     * @param saison the season to set
     */
    public void setSaison(String saison) {
        this.saison = saison;
    }

    /**
     * Getter for the season of the event.
     *
     * @return the season
     */
    public String getSaison() {
        return saison;
    }

    /**
     * Setter for the city of the event.
     *
     * @param ville the city to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Getter for the city of the event.
     *
     * @return the city
     */
    public String getVille() {
        return ville;
    }

    /**
     * Setter for the name of the champion.
     *
     * @param champion the name to set
     */
    public void setChampion(String champion) {
        this.champion = champion;
    }

    /**
     * Getter for the name of the champion.
     *
     * @return the name
     */
    public String getChampion() {
        return champion;
    }

    /**
     * Setter for the medal of the champion.
     *
     * @param medaille the medal to set
     */
    public void setMedaille(String medaille) {
        this.medaille = medaille;
    }

    /**
     * Getter for the medal of the champion.
     *
     * @return the medal
     */
    public String getMedaille() {
        return medaille;
    }

    /**
     * Setter for the organisation that organised the event.
     *
     * @param organisation the organisation to set
     * @see Organisation
     */
    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    /**
     * Getter for the organisation that organised the event.
     *
     * @return the organisation
     * @see Organisation
     */
    public Organisation getOrganisation() {
        return organisation;
    }

    /**
     * Getter for the sport of the event.
     *
     * @return the sport
     * @see Sport
     */
    public Sport getSport() {
        return sport;
    }

    /**
     * Setter for the sport of the event.
     *
     * @param sport the sport to set
     * @see Sport
     */
    public void setSport(Sport sport) {
        this.sport = sport;
    }

    /**
     * Getter for the epreuve of the event.
     *
     * @return the epreuve
     * @see Epreuve
     */
    public Epreuve getEpreuve() {
        return epreuve;
    }

    /**
     * Setter for the epreuve of the event.
     *
     * @param epreuve the epreuve to set
     * @see Epreuve
     */
    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }
}
