package com.oncampus.oncampusApi.event;

import com.oncampus.oncampusApi.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Integer eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new ObjectNotFoundException("event", eventId));
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Integer eventId, Event update) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    event.setEventType(update.getEventType());
                    event.setCapacity(update.getCapacity());
                    event.setDescription(update.getDescription());
                    event.setDateTime(update.getDateTime());
                    event.setVenue(update.getVenue());
                    event.setTags(update.getTags());
                    event.setName(update.getName());
                    return eventRepository.save(event);
                }).orElseThrow(() -> new ObjectNotFoundException("event", eventId));
    }

    public void delete(Integer eventId) {
        eventRepository.findById(eventId).orElseThrow(() -> new ObjectNotFoundException("event", eventId));
        eventRepository.deleteById(eventId);
    }

}
