package fr.diginamic.daos;

import fr.diginamic.entities.Event;

import java.util.HashSet;
import java.util.Set;

public class EventDao extends AbstractDao<Event> {
    private final Set<Event> events;

    public EventDao() {
        events = findAll();
    }

    @Override
    public Set<Event> findAll() {
        return new HashSet<>(em.createQuery("SELECT e FROM Event e", Event.class).getResultList());
    }

    @Override
    public Event findByPk(int id) {
        return events.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Event event) {
        events.add(event);
        em.persist(event);
    }
}
