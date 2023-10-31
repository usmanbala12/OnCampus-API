package com.oncampus.oncampusApi.user.converter;

import com.oncampus.oncampusApi.user.User;
import com.oncampus.oncampusApi.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setRoles(userDto.roles());
        user.setEmail(userDto.email());
        user.setType(userDto.type());
        return user;
    }
}
