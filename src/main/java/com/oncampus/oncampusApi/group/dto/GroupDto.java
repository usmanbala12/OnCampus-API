package com.oncampus.oncampusApi.group.dto;

import com.oncampus.oncampusApi.user.User;
import com.oncampus.oncampusApi.user.dto.UserDto;

import java.util.List;

public record GroupDto (Integer id, String name, String description, UserDto createdBy, List<User> members, Integer noOfEvents){
}
