package fr.diginamic.entities.associatives;

import fr.diginamic.entities.Langue;
import fr.diginamic.entities.Organisation;
import jakarta.persistence.*;

/**
 * Represents a WordingOrganisation in the system.
 * A WordingOrganisation is a name of an Organisation in a specific language.
 * Table associatives between Organisation and Langue.
 *
 * @see Langue
 * @see Organisation
 *
 * @author AyoubBenziza
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "organisation_langue")
public class WordingOrganisation {
    /**
     * The unique identifier of the WordingOrganisation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the WordingOrganisation.
     */
    private String name;

    /**
     * The language of the WordingOrganisation.
     *
     * @see Langue
     */
    @ManyToOne
    private Langue langue;

    /**
     * The Organisation of the WordingOrganisation.
     *
     * @see Organisation
     */
    @ManyToOne
    private Organisation organisation;

    /**
     * Default constructor.
     */
    public WordingOrganisation() {}

    /**
     * Constructor with parameters.
     *
     * @param name name of the WordingOrganisation
     * @param langue language of the WordingOrganisation
     * @param organisation Organisation of the WordingOrganisation
     */
    public WordingOrganisation(String name, Langue langue, Organisation organisation) {
        this.name = name;
        this.langue = langue;
        this.organisation = organisation;
    }

    /**
     * Setter of the Organisation of the WordingOrganisation.
     *
     * @param name name of the WordingOrganisation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the name of the WordingOrganisation.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the Organisation of the WordingOrganisation.
     *
     * @param id id of the WordingOrganisation
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of the id of the WordingOrganisation.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of the Organisation of the WordingOrganisation.
     *
     * @param langue language of the WordingOrganisation
     * @see Langue
     */
    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    /**
     * Getter of the language of the WordingOrganisation.
     *
     * @return langue
     * @see Langue
     */
    public Langue getLangue() {
        return langue;
    }
}
