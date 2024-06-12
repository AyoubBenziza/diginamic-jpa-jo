package fr.diginamic;

import fr.diginamic.entities.Sport;
import fr.diginamic.entities.associatives.WordingEpreuve;
import fr.diginamic.entities.associatives.WordingSport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jo");
        EntityManager em = emf.createEntityManager();

        // Fetch the sport entity from the database
        Sport sport = em.find(Sport.class, 1); // replace 1 with the id of the sport you want to fetch

        // Access the wordings property of the sport
        Set<WordingSport> wordingSports = sport.getWordings();

        // Print the name of the sport in each language
        for (WordingSport wordingSport : wordingSports) {
            System.out.println("Language: " + wordingSport.getLangue().getName() + ", Sport name: " + wordingSport.getName());
        }

        em.close();
        emf.close();
    }
}