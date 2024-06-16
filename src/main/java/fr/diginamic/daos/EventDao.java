package fr.diginamic.daos;

import fr.diginamic.entities.Event;

import java.util.HashSet;
import java.util.Set;

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
