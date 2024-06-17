package fr.diginamic.daos;

import fr.diginamic.entities.Epreuve;
import fr.diginamic.entities.Event;
import fr.diginamic.entities.Sport;
import fr.diginamic.entities.associatives.WordingSport;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import javafx.util.Pair;

/**
 * DAO class to manage the Event entity
 *
 * @see Event
 * @see AbstractDao
 *
 * @author AyoubBenziza
 */
public class EventDao extends AbstractDao<Event> {
    /**
     * Set of Event objects
     *
     * @see Event
     */
    private final Set<Event> events;

    /**
     * Constructor
     * Initializes the events attribute
     */
    public EventDao() {
        events = findAll();
    }

    /**
     * Returns the set of Event objects
     *
     * @return Set of Event objects
     * @see Event
     */
    @Override
    public Set<Event> findAll() {
        return new HashSet<>(em.createQuery("SELECT e FROM Event e", Event.class).getResultList());
    }

    /**
     * Returns the Event object with the specified id
     *
     * @param id the id of the Event object
     * @return the Event object with the specified id
     * @see Event
     */
    @Override
    public Event findByPk(int id) {
        return events.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the medals ordered by year
     *
     * @return List of Pair objects containing the year and the medal
     */
    public List<Pair<Year, String>> getMedalsOrderByYear() {
        return events.stream()
                .filter(event -> event.getMedaille() != null)
                .sorted(Comparator.comparing(Event::getAnnee))
                .map(event -> new Pair<>(event.getAnnee(), event.getMedaille()))
                .collect(Collectors.toList());
    }

    /**
     * Return the medals grouped by sport
     *
     * @return Map of Set of WordingSport objects and List of String objects
     */
    public Map<Set<WordingSport>, List<String>> getMedalsGroupBySport(){
        return events.stream()
                .filter(event -> event.getMedaille() != null)
                .collect(Collectors.groupingBy(event -> event.getSport().getWordings(), Collectors.mapping(Event::getMedaille, Collectors.toList())));
    }

    /**
     * Returns the medals grouped by sport and epreuve
     *
     * @return Map of Pair objects containing the Sport and Epreuve objects and List of String objects
     */
    public Map<Pair<Sport, Epreuve>, List<String>> getMedalsGroupBySportAndEpreuve() {
        return events.stream()
                .filter(event -> event.getMedaille() != null)
                .collect(Collectors.groupingBy(event -> new Pair<>(event.getSport(), event.getEpreuve()),
                        Collectors.mapping(Event::getMedaille, Collectors.toList())));
    }

    /**
     * Saves the Event object in the database
     *
     * @param event the Event object to save
     * @see Event
     */
    @Override
    public void save(Event event) {
        events.add(event);
        em.persist(event);
    }
}
