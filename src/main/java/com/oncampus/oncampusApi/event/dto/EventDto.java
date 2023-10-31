package com.oncampus.oncampusApi.event.dto;

import com.oncampus.oncampusApi.event.EventType;
import com.oncampus.oncampusApi.event.Tag;
import com.oncampus.oncampusApi.group.dto.GroupDto;
import com.oncampus.oncampusApi.user.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

public record EventDto (
        Integer id,
        String name,
        String description,
        EventType eventType,
        LocalDateTime dateTime,
        Date createdDate,
        GroupDto hostGroup,
        String venue,
        Integer capacity,
        List<User> attendees,
        Set<Tag> tags

) {
}
