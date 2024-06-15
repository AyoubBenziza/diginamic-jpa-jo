package fr.diginamic.daos;

import fr.diginamic.entities.Event;

import java.util.HashSet;
import java.util.Set;

public class EventDao extends AbstractDao {
    private final Set<Event> events;

    public EventDao() {
        events = findAll();
    }

    public Set<Event> findAll() {
        return new HashSet<>(em.createQuery("SELECT e FROM Event e", Event.class).getResultList());
    }

    public void save(Event event) {
        events.add(event);
        em.persist(event);
    }
}
