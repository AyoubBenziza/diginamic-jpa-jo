package fr.diginamic.entities;

import fr.diginamic.entities.associatives.WordingEpreuve;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an Epreuve (Event) in the system.
 * An Epreuve can have multiple wordings (names) depending on the language.
 * Links to WordingEpreuve.
 * Links to Epreuve.
 *
 * @author AyoubBenziza
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "epreuve")
public class Epreuve {

    /**
     * The unique identifier of the Epreuve.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The set of wordings associated with this Epreuve.
     * Each wording represents the name of the Epreuve in a different language.
     */
    @OneToMany(mappedBy = "epreuve")
    private Set<WordingEpreuve> wordingEpreuves = new HashSet<>();

    /**
     * The organisation that organizes this Epreuve.
     */
    @OneToMany(mappedBy = "epreuve")
    private Set<Event> events = new HashSet<>();

    /**
     * Default constructor.
     */
    public Epreuve() {
    }

    /**
     * Sets the unique identifier of this Epreuve.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the unique identifier of this Epreuve.
     *
     * @return the unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the set of wordings associated with this Epreuve.
     *
     * @param wordingEpreuves the set of wordings to set
     */
    public void setWordings(Set<WordingEpreuve> wordingEpreuves) {
        this.wordingEpreuves = wordingEpreuves;
    }

    /**
     * Getter for the set of wordings associated with this Epreuve.
     *
     * @return the set of wordings
     */
    public Set<WordingEpreuve> getWordings() {
        return wordingEpreuves;
    }

    /**
     * Adds a wording to the set of wordings associated with this Epreuve.
     *
     * @param wordingEpreuve the wording to add
     */
    public void addWording(WordingEpreuve wordingEpreuve) {
        wordingEpreuves.add(wordingEpreuve);
    }

    /**
     * Removes a wording from the set of wordings associated with this Epreuve.
     *
     * @param wordingEpreuve the wording to remove
     */
    public void removeWording(WordingEpreuve wordingEpreuve) {
        wordingEpreuves.remove(wordingEpreuve);
    }

    /**
     * Clears the set of wordings associated with this Epreuve.
     */
    public void clearWordings() {
        wordingEpreuves.clear();
    }

    /**
     * Sets the set of organisations that organize this Epreuve.
     *
     * @param events the set of organisations to set
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    /**
     * Getter for the set of organisations that organize this Epreuve.
     *
     * @return the set of organisations
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Adds an organisation to the set of organisations that organize this Epreuve.
     *
     * @param event the organisation to add
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Removes an organisation from the set of organisations that organize this Epreuve.
     *
     * @param event the organisation to remove
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }

    /**
     * Clears the set of organisations that organize this Epreuve.
     */
    public void clearEvents() {
        events.clear();
    }
}
