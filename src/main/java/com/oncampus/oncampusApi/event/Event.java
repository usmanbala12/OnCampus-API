package com.oncampus.oncampusApi.event;

import com.oncampus.oncampusApi.group.Group;
import com.oncampus.oncampusApi.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private EventType eventType;
    private LocalDateTime dateTime;
    private Date createdDate;
    @ManyToOne
    private Group hostGroup;
    private String venue;
    private Integer capacity;
    @ManyToMany
    private List<User> attendees;
    @ManyToMany
    private Set<Tag> tags;

}
