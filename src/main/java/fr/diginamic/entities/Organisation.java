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
 * @Author AyoubBenziza
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
    @Column(name = "iso_code", length = 10, nullable = true, unique = true)
    private String iso_code;

    /**
     * The obsolete status of the Organisation.
     */
    @Column(name = "obsolete", nullable = false)
    private boolean obsolete;

    /**
     * The set of wordings associated with this Organisation.
     * Each wording represents the name of the Organisation in a different language.
     */
    @OneToMany(mappedBy = "organisation")
    private Set<WordingOrganisation> wordingOrganisations = new HashSet<>();

    /**
     * The set of events associated with this Organisation.
     */
    @OneToMany(mappedBy = "organisation")
    private Set<Event> events;

    @OneToMany(mappedBy = "organisation")
    private Set<Epreuve> epreuves;

    /**
     * Default constructor.
     */
    public Organisation() {
    }

    /**
     * Sets the unique identifier of this Organisation.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the unique identifier of this Organisation.
     *
     * @return the unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the CIO code of this Organisation.
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
     * Sets the ISO code of this Organisation.
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
     * Sets the obsolete status of this Organisation.
     *
     * @param obsolete the obsolete status to set
     */
    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    /**
     * Sets the wordings associated with this Organisation.
     *
     * @param wordingOrganisations the wordings to set
     */
    public void setWordings(Set<WordingOrganisation> wordingOrganisations) {
        this.wordingOrganisations = wordingOrganisations;
    }

    /**
     * Getter for the wordings associated with this Organisation.
     *
     * @return the wordings
     */
    public Set<WordingOrganisation> getWordings() {
        return wordingOrganisations;
    }

    /**
     * Adds a wording to the set of wordings associated with this Organisation.
     *
     * @param wordingOrganisation the wording to add
     */
    public void addWording(WordingOrganisation wordingOrganisation) {
        wordingOrganisations.add(wordingOrganisation);
    }

    /**
     * Removes a wording from the set of wordings associated with this Organisation.
     *
     * @param wordingOrganisation the wording to remove
     */
    public void removeWording(WordingOrganisation wordingOrganisation) {
        wordingOrganisations.remove(wordingOrganisation);
    }

    /**
     * Clears the set of wordings associated with this Organisation.
     */
    public void clearWordings() {
        wordingOrganisations.clear();
    }

    /**
     * Sets the events associated with this Organisation.
     *
     * @param events the events to set
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    /**
     * Getter for the events associated with this Organisation.
     *
     * @return the events
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Adds an event to the set of events associated with this Organisation.
     *
     * @param event the event to add
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Removes an event from the set of events associated with this Organisation.
     *
     * @param event the event to remove
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }

    /**
     * Clears the set of events associated with this Organisation.
     */
    public void clearEvents() {
        events.clear();
    }

    /**
     * Sets the epreuves associated with this Organisation.
     *
     * @param epreuves the epreuves to set
     */
    public void setEpreuves(Set<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }

    /**
     * Getter for the epreuves associated with this Organisation.
     *
     * @return the epreuves
     */
    public Set<Epreuve> getEpreuves() {
        return epreuves;
    }

    /**
     * Adds an epreuve to the set of epreuves associated with this Organisation.
     *
     * @param epreuve the epreuve to add
     */
    public void addEpreuve(Epreuve epreuve) {
        epreuves.add(epreuve);
    }

    /**
     * Removes an epreuve from the set of epreuves associated with this Organisation.
     *
     * @param epreuve the epreuve to remove
     */
    public void removeEpreuve(Epreuve epreuve) {
        epreuves.remove(epreuve);
    }

    /**
     * Clears the set of epreuves associated with this Organisation.
     */
    public void clearEpreuves() {
        epreuves.clear();
    }
}
