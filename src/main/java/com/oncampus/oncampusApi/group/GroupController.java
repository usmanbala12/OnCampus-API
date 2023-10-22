package com.oncampus.oncampusApi.group;

import com.oncampus.oncampusApi.system.Result;
import com.oncampus.oncampusApi.system.StatusCode;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public Result findAllGroups() {
        List<Group> groups = groupService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", groups);
    }

    @GetMapping("/{groupId}")
    public Result findGroupById(@PathVariable Integer groupId) {
        Group group = groupService.findById(groupId);
        return new Result(true, StatusCode.SUCCESS, "Find One Sucess", group);
    }

    @PostMapping
    public Result addGroup(@RequestBody Group group) {
        Group savedGroup = groupService.save(group);
        return new Result(true, StatusCode.SUCCESS, "Add Group Success", savedGroup);
    }

    @PutMapping("/{groupId}")
    public Result updateGroup(@RequestBody Group update, @PathVariable Integer groupId) {
        Group updated = groupService.update(update, groupId);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updated);
    }

    @DeleteMapping("/{groupId}")
    public Result deleteGroup(@PathVariable Integer groupId) {
        groupService.delete(groupId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
