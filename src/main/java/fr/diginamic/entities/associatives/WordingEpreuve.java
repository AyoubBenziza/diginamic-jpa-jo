package fr.diginamic.entities.associatives;

import fr.diginamic.entities.Epreuve;
import fr.diginamic.entities.Langue;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "epreuve_langue")
public class WordingEpreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private Langue langue;

    @ManyToOne
    private Epreuve epreuve;

    public WordingEpreuve() {
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
