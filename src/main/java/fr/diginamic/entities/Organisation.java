package fr.diginamic.entities;

import fr.diginamic.entities.associatives.WordingOrganisation;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "organisation")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "organisation")
    private Set<WordingOrganisation> wordingOrganisations;

    @OneToMany(mappedBy = "organisation")
    private Set<Event> events;

    public Organisation() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWordings(Set<WordingOrganisation> wordingOrganisations) {
        this.wordingOrganisations = wordingOrganisations;
    }

    public Set<WordingOrganisation> getWordings() {
        return wordingOrganisations;
    }

    public void addWording(WordingOrganisation wordingOrganisation) {
        wordingOrganisations.add(wordingOrganisation);
    }

    public void removeWording(WordingOrganisation wordingOrganisation) {
        wordingOrganisations.remove(wordingOrganisation);
    }

    public void clearWordings() {
        wordingOrganisations.clear();
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void clearEvents() {
        events.clear();
    }
}
