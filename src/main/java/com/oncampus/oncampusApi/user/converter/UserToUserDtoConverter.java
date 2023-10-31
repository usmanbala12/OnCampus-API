package com.oncampus.oncampusApi.user.converter;

import com.oncampus.oncampusApi.user.User;
import com.oncampus.oncampusApi.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {
    public UserDto convert(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles(),
                user.getType()
        );

        return userDto;
    }
}
