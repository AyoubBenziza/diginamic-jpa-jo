package fr.diginamic.entities.associatives;

import fr.diginamic.entities.Epreuve;
import fr.diginamic.entities.Langue;
import jakarta.persistence.*;

/**
 * Represents a WordingEpreuve in the system.
 * A WordingEpreuve is a name of an Epreuve in a specific language.
 * Table associatives between Epreuve and Langue.
 *
 * @see Epreuve
 * @see Langue
 *
 * @author AyoubBenziza
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "epreuve_langue")
public class WordingEpreuve {
    /**
     * The unique identifier of the WordingEpreuve.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the WordingEpreuve.
     */
    private String name;

    /**
     * The language of the WordingEpreuve.
     *
     * @see Langue
     */
    @ManyToOne
    private Langue langue;

    /**
     * The Epreuve of the WordingEpreuve.
     *
     * @see Epreuve
     */
    @ManyToOne
    private Epreuve epreuve;

    /**
     * Default constructor.
     */
    public WordingEpreuve() {}

    /**
     * Constructor with parameters.
     *
     * @param name name of the WordingEpreuve
     * @param langue language of the WordingEpreuve
     * @param epreuve Epreuve of the WordingEpreuve
     */
    public WordingEpreuve(String name, Langue langue, Epreuve epreuve) {
        this.name = name;
        this.langue = langue;
        this.epreuve = epreuve;
    }

    /**
     * Setter the name of the WordingEpreuve.
     *
     * @param name name of the WordingEpreuve
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the name of the WordingEpreuve.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the id of the WordingEpreuve.
     *
     * @param id id of the WordingEpreuve
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of the id of the WordingEpreuve.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of the language of the WordingEpreuve.
     *
     * @param langue language of the WordingEpreuve
     * @see Langue
     */
    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    /**
     * Getter of the language of the WordingEpreuve.
     *
     * @return langue
     * @see Langue
     */
    public Langue getLangue() {
        return langue;
    }

    /**
     * Setter of the Epreuve of the WordingEpreuve.
     *
     * @param epreuve Epreuve of the WordingEpreuve
     * @see Epreuve
     */
    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    /**
     * Getter of the Epreuve of the WordingEpreuve.
     *
     * @return epreuve
     * @see Epreuve
     */
    public Epreuve getEpreuve() {
        return epreuve;
    }
}
