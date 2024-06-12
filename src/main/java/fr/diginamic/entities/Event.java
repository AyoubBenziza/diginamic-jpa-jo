package fr.diginamic.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private Character sexe;

    private int age;

    private int taille;

    private int poids;

    private String equipe;

    private String cno;

    private int annee;

    private String saison;

    private String ville;

    private String champion;

    private String medaille;

    @ManyToOne
    private Organisation organisation;

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

    public Event() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    public Character getSexe() {
        return sexe;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getTaille() {
        return taille;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getPoids() {
        return poids;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCno() {
        return cno;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getAnnee() {
        return annee;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public String getSaison() {
        return saison;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getVille() {
        return ville;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public String getChampion() {
        return champion;
    }

    public void setMedaille(String medaille) {
        this.medaille = medaille;
    }

    public String getMedaille() {
        return medaille;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Organisation getOrganisation() {
        return organisation;
    }
}
