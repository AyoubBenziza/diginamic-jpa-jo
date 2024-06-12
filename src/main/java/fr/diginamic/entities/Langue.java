package fr.diginamic.entities;

import fr.diginamic.entities.associatives.WordingEpreuve;
import fr.diginamic.entities.associatives.WordingOrganisation;
import fr.diginamic.entities.associatives.WordingSport;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "langue")
public class Langue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 3, nullable = false, unique = true)
    private Character name;

    @OneToMany(mappedBy = "langue")
    private Set<WordingEpreuve> wordingsEpreuve;

    @OneToMany(mappedBy = "langue")
    private Set<WordingSport> wordingSports;

    @OneToMany(mappedBy = "langue")
    private Set<WordingOrganisation> wordingOrganisations;

    public Langue() {
    }

    public Langue(Character name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public Character getName() {
        return name;
    }
}
