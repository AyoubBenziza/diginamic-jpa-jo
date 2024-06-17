package fr.diginamic.entities;

import fr.diginamic.entities.associatives.WordingOrganisation;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an Organisation in the system.
 * An Organisation can have multiple wordings (names) depending on the language.
 * Links to WordingOrganisation.
 * Links to Event.
 *
 * @see WordingOrganisation
 * @see Event
 *
 * @author AyoubBenziza
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "organisation")
public class Organisation {
    /**
     * The unique identifier of the Organisation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The CIO code of the Organisation.
     */
    @Column(name = "cio_code", length = 10, nullable = false)
    private String cio_code;

    /**
     * The ISO code of the Organisation.
     */
    @Column(name = "iso_code", length = 10, unique = true)
    private String iso_code;

    /**
     * The obsolete status of the Organisation.
     */
    @Column(name = "obsolete", nullable = false)
    private boolean obsolete;

    /**
     * The set of wordings associated with this Organisation.
     * Each wording represents the name of the Organisation in a different language.
     *
     * @see WordingOrganisation
     */
    @OneToMany(mappedBy = "organisation")
    private Set<WordingOrganisation> wordingOrganisations = new HashSet<>();

    /**
     * The set of events associated with this Organisation.
     *
     * @see Event
     */
    @OneToMany(mappedBy = "organisation")
    private Set<Event> events;

    /**
     * Default constructor.
     */
    public Organisation() {
    }

    /**
     * Setter for the unique identifier of this Organisation.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the unique identifier of this Organisation.
     *
     * @return the unique identifier of this Organisation
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the CIO code of this Organisation.
     *
     * @param cio_code the CIO code to set
     */
    public void setCioCode(String cio_code) {
        this.cio_code = cio_code;
    }

    /**
     * Getter for the CIO code of this Organisation.
     *
     * @return the CIO code
     */
    public String getCioCode() {
        return cio_code;
    }

    /**
     * Getter for the ISO code of this Organisation.
     *
     * @return the ISO code
     */
    public String getIsoCode() {
        return iso_code;
    }

    /**
     * Setter for the ISO code of this Organisation.
     *
     * @param iso_code the ISO code to set
     */
    public void setIsoCode(String iso_code) {
        this.iso_code = iso_code;
    }

    /**
     * Getter for the obsolete status of this Organisation.
     *
     * @return the obsolete status
     */
    public boolean isObsolete() {
        return obsolete;
    }

    /**
     * Setter for the obsolete status of this Organisation.
     *
     * @param obsolete the obsolete status to set
     */
    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    /**
     * Setter for the wordings associated with this Organisation.
     *
     * @param wordingOrganisations the wordings to set
     * @see WordingOrganisation
     */
    public void setWordings(Set<WordingOrganisation> wordingOrganisations) {
        this.wordingOrganisations = wordingOrganisations;
    }

    /**
     * Getter for the wordings associated with this Organisation.
     *
     * @return the wordings
     * @see WordingOrganisation
     */
    public Set<WordingOrganisation> getWordings() {
        return wordingOrganisations;
    }

    /**
     * Adds a wording to the set of wordings associated with this Organisation.
     *
     * @param wordingOrganisation the wording to add
     * @see WordingOrganisation
     */
    public void addWording(WordingOrganisation wordingOrganisation) {
        wordingOrganisations.add(wordingOrganisation);
    }

    /**
     * Removes a wording from the set of wordings associated with this Organisation.
     *
     * @param wordingOrganisation the wording to remove
     * @see WordingOrganisation
     */
    public void removeWording(WordingOrganisation wordingOrganisation) {
        wordingOrganisations.remove(wordingOrganisation);
    }

    /**
     * Clears the set of wordings associated with this Organisation.
     *
     * @see WordingOrganisation
     */
    public void clearWordings() {
        wordingOrganisations.clear();
    }

    /**
     * Setter for the events associated with this Organisation.
     *
     * @param events the events to set
     * @see Event
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    /**
     * Getter for the events associated with this Organisation.
     *
     * @return the events
     * @see Event
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Adds an event to the set of events associated with this Organisation.
     *
     * @param event the event to add
     * @see Event
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Removes an event from the set of events associated with this Organisation.
     *
     * @param event the event to remove
     * @see Event
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }

    /**
     * Clears the set of events associated with this Organisation.
     *
     * @see Event
     */
    public void clearEvents() {
        events.clear();
    }

    /**
     * Returns a string representation of the Organisation.
     *
     * @return a string representation of the Organisation
     */
    @Override
    public String toString() {
        String names = wordingOrganisations.stream()
                .map(WordingOrganisation::getName)
                .reduce("", (acc, name) -> acc + name + ", ");
        return "Organisation{" +
                "id=" + id +
                ", cio_code='" + cio_code + '\'' +
                ", iso_code='" + iso_code + '\'' +
                ", obsolete=" + obsolete +
                ", names=[" + names +
                "]}";
    }
}
