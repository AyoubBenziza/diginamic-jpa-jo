package fr.diginamic.entities;

import fr.diginamic.entities.associatives.WordingSport;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Sport in the system.
 * A Sport can have multiple wordings (names) depending on the language.
 * Links to WordingSport.
 *
 * @Author AyoubBenziza
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "sport")
public class Sport {
    /**
     * The unique identifier of the Sport.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The wordings of the Sport.
     */
    @OneToMany(mappedBy = "sport")
    private Set<WordingSport> wordingSports = new HashSet<>();

    @OneToMany(mappedBy = "sport")
    private Set<Event> events = new HashSet<>();

    /**
     * Default constructor.
     */
    public Sport() {
    }

    public Sport(Set<WordingSport> wordingSports) {
        this.wordingSports = wordingSports;
    }

    /**
     * Sets the id of the Sport.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of the id of the Sport.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the wordings of the Sport.
     *
     * @param wordingSports the wordings to set
     */
    public void setWordings(Set<WordingSport> wordingSports) {
        this.wordingSports = wordingSports;
    }

    /**
     * Getter of the wordings of the Sport.
     * @return wordingSports
     */
    public Set<WordingSport> getWordings() {
        return wordingSports;
    }

    /**
     * Adds a wording to the Sport.
     *
     * @param wordingSport the wording to add
     */
    public void addWording(WordingSport wordingSport) {
        wordingSports.add(wordingSport);
        wordingSport.setSport(this);
    }

    /**
     * Removes a wording from the Sport.
     *
     * @param wordingSport the wording to remove
     */
    public void removeWording(WordingSport wordingSport) {
        wordingSports.remove(wordingSport);
    }

    /**
     * Clears all the wordings of the Sport.
     */
    public void clearWordings() {
        wordingSports.clear();
    }

    /**
     * Getter of the events of the Sport.
     *
     * @return events
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Sets the events of the Sport.
     *
     * @param events the events to set
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    /**
     * Adds an event to the Sport.
     *
     * @param event the event to add
     */
    public void addEvent(Event event) {
        events.add(event);
        event.setSport(this);
    }

    /**
     * Removes an event from the Sport.
     *
     * @param event the event to remove
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }

    /**
     * Clears all the events of the Sport.
     */
    public void clearEvents() {
        events.clear();
    }
}
