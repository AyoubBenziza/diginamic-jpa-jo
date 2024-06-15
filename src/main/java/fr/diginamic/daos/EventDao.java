package fr.diginamic.daos;

import fr.diginamic.entities.Event;

import java.util.HashSet;
import java.util.Set;

public class EventDao extends AbstractDao {
    private final Set<Event> events = new HashSet<>();

    public EventDao() {
        this.events.addAll(em.createQuery("SELECT e FROM Event e", Event.class).getResultList());
    }

    public boolean exists(String eventName) {
        return events.stream()
                .anyMatch(e -> e.getNom().equals(eventName));
    }

    public void save(Event event) {
        events.add(event);
        em.persist(event);
    }
}
