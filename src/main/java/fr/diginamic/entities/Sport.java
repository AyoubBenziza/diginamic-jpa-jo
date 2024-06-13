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
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of the id of the Sport.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the wordings of the Sport.
     * @param wordingSports
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
     * @param wordingSport
     */
    public void addWording(WordingSport wordingSport) {
        wordingSports.add(wordingSport);
        wordingSport.setSport(this);
    }

    /**
     * Removes a wording from the Sport.
     * @param wordingSport
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
}
