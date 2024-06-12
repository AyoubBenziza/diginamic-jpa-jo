package fr.diginamic.entities.associatives;

import fr.diginamic.entities.Langue;
import fr.diginamic.entities.Organisation;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "organisation_langue")
public class WordingOrganisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private Langue langue;

    @ManyToOne
    private Organisation organisation;

    public WordingOrganisation() {
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
