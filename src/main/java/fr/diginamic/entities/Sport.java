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
 * @see WordingSport
 * @see Event
 *
 * @author AyoubBenziza
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
     *
     * @see WordingSport
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

    /**
     * Constructor with the wordings of the Sport.
     *
     * @param wordingSports the wordings of the Sport
     * @see WordingSport
     */
    public Sport(Set<WordingSport> wordingSports) {
        this.wordingSports = wordingSports;
    }

    /**
     * Setter of the id of the Sport.
     *
     * @param id the id to set
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
     * Setter of the wordings of the Sport.
     *
     * @param wordingSports the wordings to set
     * @see WordingSport
     */
    public void setWordings(Set<WordingSport> wordingSports) {
        this.wordingSports = wordingSports;
    }

    /**
     * Getter of the wordings of the Sport.
     *
     * @return wordingSports
     * @see WordingSport
     */
    public Set<WordingSport> getWordings() {
        return wordingSports;
    }

    /**
     * Adds a wording to the Sport.
     *
     * @param wordingSport the wording to add
     * @see WordingSport
     */
    public void addWording(WordingSport wordingSport) {
        wordingSports.add(wordingSport);
        wordingSport.setSport(this);
    }

    /**
     * Removes a wording from the Sport.
     *
     * @param wordingSport the wording to remove
     * @see WordingSport
     */
    public void removeWording(WordingSport wordingSport) {
        wordingSports.remove(wordingSport);
    }

    /**
     * Clears all the wordings of the Sport.
     *
     * @see WordingSport
     */
    public void clearWordings() {
        wordingSports.clear();
    }

    /**
     * Getter of the events of the Sport.
     *
     * @return events
     * @see Event
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Setter of the events of the Sport.
     *
     * @param events the events to set
     * @see Event
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    /**
     * Adds an event to the Sport.
     *
     * @param event the event to add
     * @see Event
     */
    public void addEvent(Event event) {
        events.add(event);
        event.setSport(this);
    }

    /**
     * Removes an event from the Sport.
     *
     * @param event the event to remove
     * @see Event
     */
    public void removeEvent(Event event) {
        events.remove(event);
        event.setSport(null);
    }

    /**
     * Clears all the events of the Sport.
     *
     * @see Event
     */
    public void clearEvents() {
        events.clear();
    }

    /**
     * Returns a string representation of the Sport.
     *
     * @return a string representation of the Sport
     */
    @Override
    public String toString() {
        String names = wordingSports.stream()
                .map(WordingSport::getName)
                .reduce("", (acc, name) -> acc + name + ", ");
        return "Sport {id=" + id + ", names=[" + names + "]}";
    }
}
