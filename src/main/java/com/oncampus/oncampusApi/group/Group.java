package com.oncampus.oncampusApi.group;


import com.oncampus.oncampusApi.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "oncampus_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @ManyToOne
    private User createdBy;

    @ManyToMany
    private List<User> members;

}
