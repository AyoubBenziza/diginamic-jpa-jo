package fr.diginamic.entities.associatives;

import fr.diginamic.entities.Langue;
import fr.diginamic.entities.Sport;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "sport_langue")
public class WordingSport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private Langue langue;

    @ManyToOne
    private Sport sport;

    public WordingSport() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Langue getLangue() {
        return langue;
    }
}
