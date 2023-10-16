package com.oncampus.oncampusApi.event;

import com.oncampus.oncampusApi.system.Result;
import com.oncampus.oncampusApi.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public Result findAllEvents() {
        List<Event> events = eventService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", events);
    }

    @GetMapping("/{eventId}")
    public Result findEventById(@PathVariable Integer eventId) {
        Event event = eventService.findById(eventId);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", event);
    }

    @PostMapping
    public Result addEvent(@RequestBody Event event) {
        Event savedEvent = eventService.save(event);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedEvent);
    }

    @PutMapping("/{eventId}")
    public Result updateEvent(@PathVariable Integer eventId, @RequestBody Event update) {
        Event updated = eventService.update(eventId, update);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updated);
    }

    @DeleteMapping("/{eventId}")
    public Result deleteEvent(@PathVariable Integer eventId) {
        eventService.delete(eventId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
}
