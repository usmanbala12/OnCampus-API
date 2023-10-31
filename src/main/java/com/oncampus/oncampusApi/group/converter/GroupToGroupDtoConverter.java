package com.oncampus.oncampusApi.group.converter;

import com.oncampus.oncampusApi.group.Group;
import com.oncampus.oncampusApi.group.dto.GroupDto;
import com.oncampus.oncampusApi.user.converter.UserDtoToUserConverter;
import com.oncampus.oncampusApi.user.converter.UserToUserDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupToGroupDtoConverter implements Converter<Group, GroupDto> {
    private final UserToUserDtoConverter userToUserDtoConverter;
    @Override
    public GroupDto convert(Group source) {
        GroupDto groupDto = new GroupDto(
                source.getId(),
                source.getName(),
                source.getDescription(),
                userToUserDtoConverter.convert(source.getCreatedBy()),
                source.getMembers(),
                (Integer) source.getEvents().size()
        );
        return groupDto;
    }
}
