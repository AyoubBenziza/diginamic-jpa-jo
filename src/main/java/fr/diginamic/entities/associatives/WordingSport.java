package fr.diginamic.entities.associatives;

import fr.diginamic.entities.Langue;
import fr.diginamic.entities.Sport;
import jakarta.persistence.*;

/**
 * Represents a WordingSport in the system.
 * A WordingSport is a name of a Sport in a specific language.
 * Table associatives between Sport and Langue.
 *
 * @Author AyoubBenziza
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
     */
    @ManyToOne
    private Langue langue;

    /**
     * The sport of the WordingSport.
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
     * Sets the name of the WordingSport.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the name of the WordingSport.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the id of the WordingSport.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of the id of the WordingSport.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the language of the WordingSport.
     * @param langue
     */
    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    /**
     * Getter of the language of the WordingSport.
     * @return Langue
     */
    public Langue getLangue() {
        return langue;
    }

    /**
     * Sets the sport of the WordingSport.
     * @param sport
     */

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
