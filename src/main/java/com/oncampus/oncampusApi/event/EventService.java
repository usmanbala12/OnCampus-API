package com.oncampus.oncampusApi.event;

import com.oncampus.oncampusApi.group.Group;
import com.oncampus.oncampusApi.group.GroupRepository;
import com.oncampus.oncampusApi.system.exception.ObjectNotFoundException;
import com.oncampus.oncampusApi.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final GroupRepository groupRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Integer eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new ObjectNotFoundException("event", eventId));
    }

    public Event save(Event event, Integer hostGroupId) {
        Group group = groupRepository.findById(hostGroupId).orElseThrow(() -> new ObjectNotFoundException("Group", hostGroupId));
        event.setHostGroup(group);
        Event saved = eventRepository.save(event);
        group.addEvent(event);
        return saved;
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
        Event toDelete = eventRepository.findById(eventId).orElseThrow(() -> new ObjectNotFoundException("event", eventId));
        toDelete.getHostGroup().removeEvent(toDelete);
        eventRepository.deleteById(eventId);
    }

    public void rsvp(Integer eventId, User user) {
        eventRepository.findById(eventId)
                .map(event -> {
                    event.addAttendee(user);
                    return null;
                }).orElseThrow(() -> new ObjectNotFoundException("Event", eventId));
    }

    public void cancelRsvp(Integer eventId, User user) {
        eventRepository.findById(eventId)
                .map(event -> {
                    event.removeAttendee(user);
                    return null;
                }).orElseThrow(() -> new ObjectNotFoundException("Event", eventId));
    }
}
