package com.oncampus.oncampusApi.group;

import com.oncampus.oncampusApi.group.converter.GroupToGroupDtoConverter;
import com.oncampus.oncampusApi.group.dto.GroupDto;
import com.oncampus.oncampusApi.system.Result;
import com.oncampus.oncampusApi.system.StatusCode;
import com.oncampus.oncampusApi.user.MyUserPrincipal;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService groupService;
    private final GroupToGroupDtoConverter groupToGroupDtoConverter;

    @GetMapping
    public Result findAllGroups() {
        List<Group> groups = groupService.findAll();
        List<GroupDto> groupDtos = groups.stream().map(group -> groupToGroupDtoConverter.convert(group)).toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", groupDtos);
    }

    @GetMapping("/{groupId}")
    public Result findGroupById(@PathVariable Integer groupId) {
        Group group = groupService.findById(groupId);
        GroupDto groupDto = groupToGroupDtoConverter.convert(group);
        return new Result(true, StatusCode.SUCCESS, "Find One Sucess", groupDto);
    }

    @PostMapping
    public Result addGroup(@RequestBody Group group, @AuthenticationPrincipal MyUserPrincipal auth) {
        group.setCreatedBy(auth.getUser());
        Group savedGroup = groupService.save(group);
        GroupDto groupDto = groupToGroupDtoConverter.convert(savedGroup);
        return new Result(true, StatusCode.SUCCESS, "Add Group Success", groupDto);
    }

    @PutMapping("/{groupId}")
    public Result updateGroup(@RequestBody Group update, @PathVariable Integer groupId) {
        Group updated = groupService.update(update, groupId);
        GroupDto groupDto = groupToGroupDtoConverter.convert(updated);
        return new Result(true, StatusCode.SUCCESS, "Update Success", groupDto);
    }

    @DeleteMapping("/{groupId}")
    public Result deleteGroup(@PathVariable Integer groupId) {
        groupService.delete(groupId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    @PatchMapping("/{groupId}/join")
    public Result joinGroup(@PathVariable Integer groupId, @AuthenticationPrincipal MyUserPrincipal auth) {
        groupService.joinGroup(groupId, auth.getUser());
        return new Result(true, StatusCode.SUCCESS, "join group success");
    }

    @PatchMapping("/{groupId}/leave")
    public Result leaveGroup(@PathVariable Integer groupId, @AuthenticationPrincipal MyUserPrincipal auth) {
        groupService.leaveGroup(groupId, auth.getUser());
        return new Result(true, StatusCode.SUCCESS, "leave group success");
    }
}
