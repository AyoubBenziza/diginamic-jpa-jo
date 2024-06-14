package fr.diginamic.entities.associatives;

import fr.diginamic.entities.Epreuve;
import fr.diginamic.entities.Langue;
import jakarta.persistence.*;

/**
 * Represents a WordingEpreuve in the system.
 * A WordingEpreuve is a name of an Epreuve in a specific language.
 * Table associatives between Epreuve and Langue.
 *
 * @Author AyoubBenziza
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
     */
    @ManyToOne
    private Langue langue;

    /**
     * The Epreuve of the WordingEpreuve.
     */
    @ManyToOne
    private Epreuve epreuve;

    /**
     * Default constructor.
     */
    public WordingEpreuve() {}

    /**
     * Constructor with parameters.
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
     * Sets the name of the WordingEpreuve.
     * @param name name of the WordingEpreuve
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the name of the WordingEpreuve.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the id of the WordingEpreuve.
     * @param id id of the WordingEpreuve
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of the id of the WordingEpreuve.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the language of the WordingEpreuve.
     * @param langue
     */
    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    /**
     * Getter of the language of the WordingEpreuve.
     * @return langue
     */
    public Langue getLangue() {
        return langue;
    }
}
