package fr.diginamic.entities;

import fr.diginamic.entities.associatives.WordingEpreuve;
import fr.diginamic.entities.associatives.WordingOrganisation;
import fr.diginamic.entities.associatives.WordingSport;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Represents a Langue (Language) in the system.
 * A Langue can have multiple wordings (names) depending on the language.
 * Links to WordingEpreuve.
 * Links to WordingSport.
 * Links to WordingOrganisation.
 *
 * @Author AyoubBenziza
 */
@Entity
@Table(name = "langue")
public class Langue {
    /**
     * The unique identifier of the Langue.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the Langue.
     */
    @Column(name = "name", length = 3, nullable = false, unique = true)
    private String name;

    /**
     * The set of wordings associated with this Langue.
     * Each wording represents the name of the Epreuve in a different language.
     */
    @OneToMany(mappedBy = "langue")
    private Set<WordingEpreuve> wordingsEpreuve;

    /**
     * The set of wordings associated with this Langue.
     * Each wording represents the name of the Sport in a different language.
     */
    @OneToMany(mappedBy = "langue")
    private Set<WordingSport> wordingSports;

    /**
     * The set of wordings associated with this Langue.
     * Each wording represents the name of the Organisation in a different language.
     */
    @OneToMany(mappedBy = "langue")
    private Set<WordingOrganisation> wordingOrganisations;

    /**
     * Default constructor.
     */
    public Langue() {
    }

    /**
     * Constructor with parameters.
     *
     * @param name the name of the Langue
     */
    public Langue(String name) {
        this.name = name;
    }

    /**
     * Sets the unique identifier of this Langue.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the unique identifier of this Langue.
     *
     * @return the unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the name of this Langue.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the name of this Langue.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
