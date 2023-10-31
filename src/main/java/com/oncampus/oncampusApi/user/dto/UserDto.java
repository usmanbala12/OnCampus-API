package com.oncampus.oncampusApi.user.dto;

public record UserDto(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String type,
        String roles

) { }
