package fr.diginamic.entities;

import fr.diginamic.entities.associatives.WordingSport;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "sport")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "sport")
    private Set<WordingSport> wordingSports;

    public Sport() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWordings(Set<WordingSport> wordingSports) {
        this.wordingSports = wordingSports;
    }

    public Set<WordingSport> getWordings() {
        return wordingSports;
    }

    public void addWording(WordingSport wordingSport) {
        wordingSports.add(wordingSport);
    }

    public void removeWording(WordingSport wordingSport) {
        wordingSports.remove(wordingSport);
    }

    public void clearWordings() {
        wordingSports.clear();
    }
}
