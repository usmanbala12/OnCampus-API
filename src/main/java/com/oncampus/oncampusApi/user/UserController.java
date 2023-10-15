package com.oncampus.oncampusApi.user;

import com.oncampus.oncampusApi.system.Result;
import com.oncampus.oncampusApi.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Result findAllUsers() {
        List<User> users = userService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", users);
    }

    @GetMapping("/{userId}")
    public Result findUserById(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", user);
    }

    @PostMapping
    public Result AddUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return new Result(true, StatusCode.SUCCESS, "Add Success", user);
    }

    @PutMapping("/{userId}")
    public Result updateUser(@PathVariable Integer userId, @RequestBody User user) {
        User updatedUser = userService.update(userId, user);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedUser);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
