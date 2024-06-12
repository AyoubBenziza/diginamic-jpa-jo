package fr.diginamic.entities;

import fr.diginamic.entities.associatives.WordingEpreuve;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "epreuve")
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "epreuve")
    private Set<WordingEpreuve> wordingEpreuves;

    public Epreuve() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setWordings(Set<WordingEpreuve> wordingEpreuves) {
        this.wordingEpreuves = wordingEpreuves;
    }

    public Set<WordingEpreuve> getWordings() {
        return wordingEpreuves;
    }

    public void addWording(WordingEpreuve wordingEpreuve) {
        wordingEpreuves.add(wordingEpreuve);
    }

    public void removeWording(WordingEpreuve wordingEpreuve) {
        wordingEpreuves.remove(wordingEpreuve);
    }

    public void clearWordings() {
        wordingEpreuves.clear();
    }
}
