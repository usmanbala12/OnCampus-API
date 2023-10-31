package com.oncampus.oncampusApi.system;

import com.oncampus.oncampusApi.event.Event;
import com.oncampus.oncampusApi.event.EventService;
import com.oncampus.oncampusApi.event.EventType;
import com.oncampus.oncampusApi.group.Group;
import com.oncampus.oncampusApi.group.GroupService;
import com.oncampus.oncampusApi.user.User;
import com.oncampus.oncampusApi.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class Config {

    @Bean
    CommandLineRunner loadData(UserService userService, GroupService groupService, EventService eventService) {
        return args -> {
            User user = new User();
            user.setFirstName("Hauwa");
            user.setLastName("Abdullahi");
            user.setPassword("usman");
            user.setType("local");
            user.setRoles("admin");
            user.setEmail("uu12052001@gmail.com");

            User savedUser = userService.save(user);

            User user2 = new User();
            user2.setFirstName("usman");
            user2.setLastName("bala");
            user2.setPassword("usman");
            user2.setType("local");
            user2.setRoles("user");
            user2.setEmail("usmanxp12@gmail.com");

            User savedUser2 = userService.save(user2);

            Group group = new Group();
            group.setName("AI Club BUK");
            group.setDescription("A group of AI aficionados");
            group.setCreatedBy(savedUser2);

            Group savedGroup =  groupService.save(group);

            Event event = new Event();
            event.setName("Info session");
            event.setDescription("Get to know all about AI Club BUK!");
            event.setDateTime(LocalDateTime.now());
            event.setVenue("Julius berger");
            event.setCapacity(40);
            event.setEventType(EventType.PHYSICAL);

            eventService.save(event, savedGroup.getId());

        };
    }
}
