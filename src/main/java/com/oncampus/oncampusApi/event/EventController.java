package com.oncampus.oncampusApi.event;

import com.oncampus.oncampusApi.event.converter.EventToEventDtoConverter;
import com.oncampus.oncampusApi.event.dto.EventDto;
import com.oncampus.oncampusApi.group.converter.GroupToGroupDtoConverter;
import com.oncampus.oncampusApi.system.Result;
import com.oncampus.oncampusApi.system.StatusCode;
import com.oncampus.oncampusApi.user.MyUserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final GroupToGroupDtoConverter groupToGroupDtoConverter;
    private final EventToEventDtoConverter eventToEventDtoConverter;

    @GetMapping
    public Result findAllEvents() {
        List<Event> events = eventService.findAll();
        List<EventDto> eventDtos = events.stream().map(eventToEventDtoConverter::convert).toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", eventDtos);
    }

    @GetMapping("/{eventId}")
    public Result findEventById(@PathVariable Integer eventId) {
        Event event = eventService.findById(eventId);
        EventDto eventDto = eventToEventDtoConverter.convert(event);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", eventDto);
    }

    @PostMapping
    public Result addEvent(@RequestBody Event event, @RequestParam Integer hostGroupId) {
        Event savedEvent = eventService.save(event, hostGroupId);
        EventDto eventDto = eventToEventDtoConverter.convert(savedEvent);
        return new Result(true, StatusCode.SUCCESS, "Add Success", eventDto);
    }

    @PutMapping("/{eventId}")
    public Result updateEvent(@PathVariable Integer eventId, @RequestBody Event update) {
        Event updated = eventService.update(eventId, update);
        EventDto eventDto = eventToEventDtoConverter.convert(updated);
        return new Result(true, StatusCode.SUCCESS, "Update Success", eventDto);
    }

    @DeleteMapping("/{eventId}")
    public Result deleteEvent(@PathVariable Integer eventId) {
        eventService.delete(eventId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    @PatchMapping("/{eventId}/rsvp")
    public Result rsvp(@PathVariable Integer eventId, @AuthenticationPrincipal MyUserPrincipal auth) {
        eventService.rsvp(eventId, auth.getUser());
        return new Result(true, StatusCode.SUCCESS, "rsvp success");
    }

    @PatchMapping("/{eventId}/cancel-rsvp")
    public Result cancelRsvp(@PathVariable Integer eventId, @AuthenticationPrincipal MyUserPrincipal auth) {
        eventService.cancelRsvp(eventId, auth.getUser());
        return new Result(true, StatusCode.SUCCESS, "cancel rsvp success");
    }

}
