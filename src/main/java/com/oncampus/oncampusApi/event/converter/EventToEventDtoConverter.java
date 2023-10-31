package com.oncampus.oncampusApi.event.converter;

import com.oncampus.oncampusApi.event.Event;
import com.oncampus.oncampusApi.event.dto.EventDto;
import com.oncampus.oncampusApi.group.converter.GroupToGroupDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventToEventDtoConverter implements Converter<Event, EventDto> {

    private final GroupToGroupDtoConverter groupToGroupDtoConverter;

    @Override
    public EventDto convert(Event source) {
        EventDto eventDto = new EventDto(
                source.getId(),
                source.getName(),
                source.getDescription(),
                source.getEventType(),
                source.getDateTime(),
                source.getCreatedDate(),
                groupToGroupDtoConverter.convert(source.getHostGroup()),
                source.getVenue(),
                source.getCapacity(),
                source.getAttendees(),
                source.getTags()

        );
        return eventDto;
    }
}
