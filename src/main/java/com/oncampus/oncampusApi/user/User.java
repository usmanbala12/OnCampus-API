package com.oncampus.oncampusApi.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "oncampus_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String type;
    private String role;
}
