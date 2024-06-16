package fr.diginamic.entities.associatives;

import fr.diginamic.entities.Langue;
import fr.diginamic.entities.Sport;
import jakarta.persistence.*;

/**
 * Represents a WordingSport in the system.
 * A WordingSport is a name of a Sport in a specific language.
 * Table associatives between Sport and Langue.
 *
 * @see Langue
 * @see Sport
 *
 * @author AyoubBenziza
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "sport_langue")
public class WordingSport {
    /**
     * The unique identifier of the WordingSport.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the WordingSport.
     */
    private String name;

    /**
     * The language of the WordingSport.
     *
     * @see Langue
     */
    @ManyToOne
    private Langue langue;

    /**
     * The sport of the WordingSport.
     *
     * @see Sport
     */
    @ManyToOne
    private Sport sport;

    /**
     * Default constructor.
     */
    public WordingSport() {
    }

    public WordingSport(String name, Langue langue, Sport sport) {
        this.name = name;
        this.langue = langue;
        this.sport = sport;
    }

    /**
     * Setter of the name of the WordingSport.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the name of the WordingSport.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the id of the WordingSport.
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of the id of the WordingSport.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of the language of the WordingSport.
     *
     * @param langue the language to set
     * @see Langue
     */
    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    /**
     * Getter of the language of the WordingSport.
     *
     * @return Langue
     * @see Langue
     */
    public Langue getLangue() {
        return langue;
    }

    /**
     * Setter of the sport of the WordingSport.
     *
     * @param sport the sport to set
     * @see Sport
     */

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    /**
     * Getter of the sport of the WordingSport.
     *
     * @return Sport
     * @see Sport
     */
    public Sport getSport() {
        return sport;
    }

    /**
     * Returns a string representation of the WordingSport.
     *
     * @return a string representation of the WordingSport
     */
    @Override
    public String toString() {
        return name + "(" + langue.getName() +")";
    }
}
